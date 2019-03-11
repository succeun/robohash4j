/*
 * Copyright (c) 2013, Psiphon Inc.
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

package com.hihds.robohash4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility functions
 */
public class Utils {

    public static void writeStringToFile(String data, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } finally {
            fileOutputStream.close();
        }
    }

    public static String readFileToString(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return readInputStreamToString(fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }

    public static int readFileToInt(File file) throws IOException {
        try {
            return Integer.parseInt(Utils.readFileToString(file).trim());
        } catch (NumberFormatException e) {
            throw new IOException(e);
        }
    }

    public static String readInputStreamToString(InputStream inputStream) throws IOException {
        return new String(readInputStreamToBytes(inputStream), "UTF-8");
    }

    public static byte[] readFileToBytes(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return readInputStreamToBytes(fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }

    public static byte[] readInputStreamToBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int readCount;
        byte[] buffer = new byte[16384];
        while ((readCount = inputStream.read(buffer, 0, buffer.length)) != -1) {
            outputStream.write(buffer, 0, readCount);
        }
        outputStream.flush();
        return outputStream.toByteArray();
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        try {
            byte[] buffer = new byte[16384];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0 , length);
            }
        } finally {
            inputStream.close();
            outputStream.close();
        }
    }

    public static class NullOutputStream extends OutputStream {
        @Override
        public void write(int arg0) throws IOException {
        }

        @Override
        public void write(byte[] b) throws IOException {
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
        }
    }

    public static void discardStream(InputStream inputStream) throws IOException {
        copyStream(inputStream, new NullOutputStream());
    }

	public static String formatFingerprint(byte[] fingerprintBytes) {
        // Adapted from: http://stackoverflow.com/questions/332079/in-java-how-do-i-convert-a-byte-array-to-a-string-of-hex-digits-while-keeping-l
        char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        char[] chars = new char[fingerprintBytes.length * 3 - 1];
        for (int i = 0; i < fingerprintBytes.length; i++)  {
            chars[i*3] = hexArray[(fingerprintBytes[i] & 0xFF)/16];
            chars[i*3 + 1] = hexArray[(fingerprintBytes[i] & 0xFF)%16];
            if (i < fingerprintBytes.length - 1) {
                chars[i*3 + 2] = ':';
            }
        }
        return new String(chars);
    }
	
	public static <T> T choice(T[] array) {
		// If running on Java 6 or older, use `new Random()` on RHS here
		Random random = ThreadLocalRandom.current();
		int num = random.nextInt(array.length); // 0 ~ chars.length - 1
		return array[num];
	}
	
	public static <T> void shuffle(T[] array) {
		// If running on Java 6 or older, use `new Random()` on RHS here
		Random random = ThreadLocalRandom.current();
		for (int i = array.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			// Simple swap
			T a = array[index];
			array[index] = array[i];
			array[i] = a;
		}
	}
	
	public static String getRandomString(int max) {
		byte[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".getBytes();
		Random random = ThreadLocalRandom.current();
		
		byte[] vals = new byte[max];
		for (int i = 0; i < max; i++) {
	    	int num = random.nextInt(chars.length); // 0 ~ chars.length - 1
	    	vals[i] = chars[num];
		}

		return new String(vals);
	}
}
