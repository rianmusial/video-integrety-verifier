package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;

public abstract class ImageComparerCommon {
	
	protected ImageComparer imageComparer = new ImageComparer();
	
	protected BufferedImage blackImage;
	protected BufferedImage whiteImage;
	protected BufferedImage greyImage;

	public ImageComparerCommon() {
		try {
			blackImage = getBufferedImageFromFile("image/black.png");
			whiteImage = getBufferedImageFromFile("image/white.png");
			greyImage  = getBufferedImageFromFile("image/grey.png");
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private BufferedImage getBufferedImageFromFile(String filename) throws IOException {
		URL resourceUrl = ImageComparerCommon.class.getClassLoader().getResource(filename);
		File f = new File(resourceUrl.getFile());
		BufferedImage bi = ImageIO.read(f);
		return bi;
	}

}