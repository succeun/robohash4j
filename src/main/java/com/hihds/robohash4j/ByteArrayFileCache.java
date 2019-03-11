package com.hihds.robohash4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ByteArrayFileCache {
	private static final String ENC = "UTF8";
	private static final int MAX_FILES = 1000;
	
	private final File repo;
	private Map<String, File> files = new ConcurrentHashMap<String, File>();
	private File currentSubDir; 

	public ByteArrayFileCache(String repository) throws IOException {
		repo = new File(repository);
		if (!repo.exists()) {
			repo.mkdirs();
		}
		
		if (!repo.exists() && !repo.isDirectory() && !repo.canWrite()) {
			throw new IllegalStateException(
					"imgcache.repository needs to be an existing, writable directory: " + repo.getAbsolutePath());
		}
		
		scan();
	}
	
	private void scan() throws IOException {
		Files.walkFileTree(repo.toPath(), new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				File f = file.toFile();
				files.put(f.getName(), f);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				File d = dir.toFile();
				if (!d.getAbsolutePath().equals(repo.getAbsolutePath())) {
						currentSubDir = d;
				}
				return FileVisitResult.CONTINUE;
			}
		});
	}
	
	public boolean put(String name, byte[] b) throws IOException {
		File dir = location();
		File f = new File(dir, name);
		Path path = Files.write(f.toPath(), b, StandardOpenOption.CREATE_NEW);
		if (path != null) {
			File file = path.toFile();
			files.put(normalize(file), file);
			return true;
		}
		return false;
	}

	public byte[] get(String name) throws IOException {
		File file = files.get(name);
		if (file == null) {
			return null;
		}
		if (!file.exists()) {
			files.remove(normalize(file));
			return null;
		}
		
		return Files.readAllBytes(file.toPath());
	}
	
	public boolean remove(String name) {
		File file = files.get(name);
		if (file == null) {
			return false;
		}
		if (!file.exists()) {
			files.remove(normalize(file));
		}
		return file.delete();
	}
	
	private String normalize(File f) {
		String str = f.getName();
		/*try {
			str = URLEncoder.encode(str, ENC);
		} catch (UnsupportedEncodingException e) {
			throw new IOException("Encoding not supported", e);
		}*/
		return str;
	}

	private File location() throws IOException {
		if (currentSubDir == null || !currentSubDir.exists()) {
			currentSubDir = new File(repo, String.valueOf(System.currentTimeMillis()));
			currentSubDir.mkdirs();
		}
		
		if (currentSubDir.list().length >= MAX_FILES) {
			String name = String.valueOf(System.currentTimeMillis());
			currentSubDir = new File(repo, name);
			currentSubDir.mkdirs();
		}
		return currentSubDir;		
	}
}
