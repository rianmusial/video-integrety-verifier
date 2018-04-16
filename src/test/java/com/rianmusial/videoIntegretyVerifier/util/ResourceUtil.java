package com.rianmusial.videoIntegretyVerifier.util;

import java.io.File;
import java.net.URL;

public abstract class ResourceUtil {

	public static File getResource(String filename) {
		URL url = ResourceUtil.class.getClassLoader().getResource(filename);
		return new File(url.getFile());
	}

}
