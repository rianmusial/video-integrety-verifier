package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;

public abstract class ImageComparerCommon {
	
	protected ImageComparer imageComparer = new ImageComparer();

	protected BufferedImage getBufferedImageFromFile(String filename) throws IOException {
		URL resourceUrl = ImageComparerCommon.class.getClassLoader().getResource(filename);
		File f = new File(resourceUrl.getFile());
		BufferedImage bi = ImageIO.read(f);
		return bi;
	}

}