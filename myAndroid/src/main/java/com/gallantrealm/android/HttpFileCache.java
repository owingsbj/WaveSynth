package com.gallantrealm.android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Context;

/**
 * Provides caching of files obtained via Https. Files are kept in the app internal file cache.
 */
public final class HttpFileCache {

	private static String getCacheFileName(String urlString) {
		return urlString.replace("/", "_").replace(":", "_").replace("%", "_");
	}

	/**
	 * Returns true if the resource for the url is in the cache (stale or not).
	 */
	public static boolean isFileCached(String urlString, Context context) throws IOException {
		String cacheFileName = getCacheFileName(urlString);
		File file = new File(context.getCacheDir(), cacheFileName);
		return file.exists();
	}

	/**
	 * Returns true if the cached file for the resource url has a newer version on the server.
	 */
	public static boolean isCacheStale(String urlString, Context context) throws IOException {
		boolean fileCached = false;
		String cacheFileName = getCacheFileName(urlString);
		File file = new File(context.getCacheDir(), cacheFileName);
		if (!file.exists()) {
			return false;
		}
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) (new URL(urlString)).openConnection();
		} catch (IOException e) {	// no access to server
			return false;			// assume not stale
		}
		long date = file.lastModified();
		connection.setIfModifiedSince(date);
		int response = connection.getResponseCode();
		if (response == HttpURLConnection.HTTP_NOT_FOUND) {
			return false;			// assume not stale
		}
		if (response == HttpURLConnection.HTTP_UNAVAILABLE) {
			return false;			// assume not stale
		}
		boolean stale = response != HttpURLConnection.HTTP_NOT_MODIFIED;   // stale if modified
		if (stale) {
			System.out.println("HttpFileCache.isCacheStale: found cache stale for " + urlString);
		}
		return stale;
	}

	/**
	 * Clears all files from the cache matching any url for the given prefix.
	 */
	public static void clearCache(String urlStringPrefix, Context context) throws IOException {
		String cacheFileName = getCacheFileName(urlStringPrefix);
		File[] cacheFiles = context.getCacheDir().listFiles();
		for (File cacheFile : cacheFiles) {
			if (cacheFile.getName().startsWith(cacheFileName)) {
				System.out.println("HttpFileCache.clearCache: deleting cache file " + cacheFile.getName());
				cacheFile.delete();
			}
		}
	}

	/**
	 * Returns either the cached resource for the given url or a request is made for the
	 * resource and it is cached and returned.
	 */
	public static File getFile(String urlString, Context context) throws IOException {
		String cacheFileName = getCacheFileName(urlString);
		File file = new File(context.getCacheDir(), cacheFileName);
		if (file.exists()) {
			if (file.length() == 0)
				return null;
			return file;
		}
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) (new URL(urlString)).openConnection();
		} catch (IOException e) {	// no access to server
			throw e;
		}
		int response = connection.getResponseCode();
		if (response == HttpURLConnection.HTTP_NOT_FOUND) {
			System.out.println("HttpFileCache.getFile: " + urlString + " is not found");
			// create a zero length cache file.  This signifies a non-existent resource
			OutputStream outputStream = new FileOutputStream(file);
			outputStream.close();
			return null;
		}
		if (response == HttpURLConnection.HTTP_UNAVAILABLE) {
			System.out.println("HttpFileCache.getFile: " + urlString + " is not available");
			return null;
		}
		System.out.println("HttpFileCache.getFile: Downloading and caching " + urlString);
		InputStream inputStream = connection.getInputStream();
		OutputStream outputStream = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int len = 0;
		while (len >= 0) {
			len = inputStream.read(buffer);
			if (len > 0) {
				outputStream.write(buffer, 0, len);
			}
		}
		outputStream.close();
		inputStream.close();
		return file;
	}

	/**
	 * Checks to make sure a cached file is most recent version and recaches if not, then returns
	 * that cached file.
	 */
	public static File getUpToDateFile(String urlString, Context context) throws IOException {
		if (isCacheStale(urlString, context)) {
			clearCache(urlString, context);
		}
		return getFile(urlString, context);
	}

}
