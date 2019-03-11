package com.hihds.robohash4j.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hihds.robohash4j.ByteArrayFileCache;
import com.hihds.robohash4j.Robohash;
import com.hihds.robohash4j.Robohash.Background;
import com.hihds.robohash4j.Robohash.Format;
import com.hihds.robohash4j.Robohash.ImageSet;

import net.coobird.thumbnailator.Thumbnails;

public class ImageServlet extends HttpServlet {

	private ByteArrayFileCache cache = null;

	private static final long serialVersionUID = -8326892581368109283L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String dir = config.getInitParameter("cache-dir");
		dir = (dir == null || dir.length() == 0) ? "./cache" : dir;
		
		try {
			cache = new ByteArrayFileCache(dir);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String exts = "png"; 
		String path = request.getRequestURI();
		String filename = new File(path).getName();
		String name = filename;
		int p = filename.lastIndexOf('.');
		if (0 < p) {
			name = filename.substring(0, p);
			exts = filename.substring(p + 1);
		}		
		String setName = request.getParameter("set");
		String strSize = request.getParameter("size");
		
		int width = 300, height = 300;

		if (strSize != null && strSize.length() > 0) {
			Pattern ptr = Pattern.compile("([0-9]+)x([0-9]+)");
			Matcher matcher = ptr.matcher(strSize);
			if (matcher.find()) {
				width = Integer.parseInt(matcher.group(1));
				height = Integer.parseInt(matcher.group(2));
			}
		}
		
		String bgName = request.getParameter("bg");

		ImageSet imageSet = null;
		if ("set1".equals(setName)) {
			imageSet = ImageSet.ROBOHASH_SET1;
		} else if ("set2".equals(setName)) {
			imageSet = ImageSet.ROBOHASH_SET2;
		} else if ("set3".equals(setName)) {
			imageSet = ImageSet.ROBOHASH_SET3;
		} else if ("set4".equals(setName)) {
			imageSet = ImageSet.ROBOHASH_SET4;
		} else {
			imageSet = ImageSet.ROBOHASH_SET1;
		}
		
		Background background = null;
		if ("bg1".equals(bgName)) {
			background = Background.BG1;
		} else if ("bg2".equals(bgName)) {
			background = Background.BG2;
		}
		
		String key = filename + "-" + setName + "-" + strSize + "-" + bgName;
		byte[] thumbnailBytes = cache.get(key);
		
		if (thumbnailBytes == null) {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] pngBytes = Robohash.assemble(imageSet, background, name.getBytes(), Format.PNG);
			Thumbnails.of(new ByteArrayInputStream(pngBytes)).size(width, height).outputFormat(exts)
					.toOutputStream(bout);
			thumbnailBytes = bout.toByteArray();
			cache.put(key, thumbnailBytes);
		} else {
			System.out.println("cache: " + key);
		}
		
		response.setContentType("image/" + exts);
		response.setHeader("Cache-Control", "public,max-age=31536000");
		
		response.getOutputStream().write(thumbnailBytes);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}