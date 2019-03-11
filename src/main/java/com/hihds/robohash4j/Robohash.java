/*
 * Copyright (c) 2014, Psiphon Inc.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

/*
 RoboHash.org

 The RoboHash images are available under the CC-BY-3.0 license.
 Set 1 artwork created by Zikri Kader
 Set 2 artwork created by Hrvoje Novakovic.
 Set 3 artwork created by Julian Peter Arias.

 The Python Code is available under the MIT/Expat license

 Copyright (c) 2011, Colin Davis

 Permission is hereby granted, free of charge, to any person obtaining a copy of this
 software and associated documentation files (the "Software"), to deal in the
 Software without restriction, including without limitation the rights to use, copy,
 modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 and to permit persons to whom the Software is furnished to do so, subject to the
 following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.hihds.robohash4j;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Unique avatars derived from identity fingerprints - to aid visual verification.
 *
 * Port of Robohash (http://robohash.org) to Java.
 */
public class Robohash {
	
	public enum Format {
		BMP("BMP"),
		JPG("JPG"),
		PNG("PNG"),
		WBMP("WBMP");
		
		private String name;
		Format(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
	}
	
	public enum ImageSet {
		PLOGGYHASH("/image/sets/ploggyhash"),
		ROBOHASH_SET1("/image/sets/robohash_set1"),
		ROBOHASH_SET2("/image/sets/robohash_set2"),
		ROBOHASH_SET3("/image/sets/robohash_set3"),
		ROBOHASH_SET4("/image/sets/robohash_set4"),
		ROBOHASH_ANY(null);
		
		private String path;
		ImageSet(String path) {
			if (path == null) {
				path = Utils.choice(new String[] {"/image/sets/robohash_set1", "/image/sets/robohash_set2", "/image/sets/robohash_set3", "/image/sets/robohash_set4"} );
			}
			this.path = path;
		}
		public String getPath() {
			return path;
		}
	}
	
	public enum Background {
		BG1("/image/backgrounds/bg1"),
		BG2("/image/backgrounds/bg2");
		
		private String path;
		Background(String path) {
			this.path = path;
		}
		public String getPath() {
			return path;
		}
	}

    private static final String CONFIG_FILENAME = "config.json";

    private static Map<ImageSet, JSONObject> setConfigs = new HashMap<ImageSet, JSONObject>();
    private static Map<Background, JSONObject> bgConfigs = new HashMap<Background, JSONObject>();
    
    private static Map<String, byte[]> partBytes = new HashMap<String, byte[]>();
    
    public static byte[] assemble(ImageSet imageSet, byte[] hashData) throws IOException {
    	return assemble(imageSet, null, hashData, Format.PNG);
    }
    
    public static byte[] assemble(ImageSet imageSet, byte[] hashData, Format format) throws IOException {
    	return assemble(imageSet, null, hashData, format);
    }

    public static byte[] assemble(ImageSet imageSet, Background bgSet, byte[] hashData, Format format) throws IOException {
        try {
            MessageDigest sha1 = null;
			try {
				sha1 = MessageDigest.getInstance("SHA-1");
			} catch (NoSuchAlgorithmException ignored) {
				//	
			}
            byte[] digest = sha1.digest(hashData);

            String key = Utils.formatFingerprint(digest);

            ByteBuffer byteBuffer = ByteBuffer.wrap(digest);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            // TODO: SecureRandom SHA1PRNG (but not LinuxSecureRandom)
            Random random = new Random(byteBuffer.getLong());
            
            JSONObject setConfig = setConfigs.get(imageSet);
            if (setConfig == null) {
                setConfig = new JSONObject(loadConfig(imageSet.getPath(), CONFIG_FILENAME));
                setConfigs.put(imageSet, setConfig);
            }

            int width = setConfig.getInt("width");
            int height = setConfig.getInt("height");

            JSONArray colors = setConfig.getJSONArray("colors");
            int val = random.nextInt(colors.length());
            JSONArray parts = colors.getJSONArray(val);

            final BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = res.createGraphics();
            
            if (bgSet != null) {
            	drawBackground(g, bgSet, width, height);
            }
            
            for (int i = 0; i < parts.length(); i++) {
            	JSONArray partChoices = parts.getJSONArray(i);
                String selection = partChoices.getString(random.nextInt(partChoices.length()));
                BufferedImage partImage = loadResourceToBitmap(imageSet.getPath(), selection);
                //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                g.drawImage(partImage, 0, 0, null);
            }
            
            ByteArrayOutputStream bout = null;
            byte[] result = null;
            try {
            	bout = new ByteArrayOutputStream();
            	ImageIO.write(res, format.getName(), bout);
            	result = bout.toByteArray();
            } finally {
            	g.dispose();
            }
            
            return result;
            
        } catch (IOException e) {
            throw e;
        } catch (JSONException e) {
            throw e;
        }
    }

	private static void drawBackground(Graphics2D g, Background bgSet, int width, int height) throws IOException {
		Random random = ThreadLocalRandom.current();
		
		JSONObject bgConfig = bgConfigs.get(bgSet);
		if (bgConfig == null) {
		    bgConfig = new JSONObject(loadConfig(bgSet.getPath(), CONFIG_FILENAME));
		    bgConfigs.put(bgSet, bgConfig);
		}

		JSONArray colors = bgConfig.getJSONArray("list");
		int val = random.nextInt(colors.length());
        String selection = colors.getString(val);
        BufferedImage partImage = loadResourceToBitmap(bgSet.getPath(), selection);
		g.drawImage(partImage, 0, 0, width, height, null);
	}
    
    private static String loadConfig(String path, String resName) throws IOException {
        InputStream in = null;
        try {
        	
        	in = Robohash.class.getResourceAsStream(path + "/" + resName);
            return Utils.readInputStreamToString(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    private static BufferedImage loadResourceToBitmap(String path, String resName) throws IOException {
    	String s = path + "/" + resName;
    	byte[] b = partBytes.get(s);
    	if (b == null) {
    		b = Utils.readInputStreamToBytes(Robohash.class.getResourceAsStream(s));
    		partBytes.put(s, b);
    	}
        return ImageIO.read(new ByteArrayInputStream(b));
    }
    
    public static void main(String[] args) throws Exception {
    	byte[] pngImage = assemble(ImageSet.ROBOHASH_SET1, "silver@hihds.com".getBytes());
    	Files.write(Paths.get("test.png"), pngImage);
    }
}
