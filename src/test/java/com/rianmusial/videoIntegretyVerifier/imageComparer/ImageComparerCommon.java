package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;
import com.rianmusial.videoIntegretyVerifier.util.ResourceUtil;

public abstract class ImageComparerCommon {
	
	protected ImageComparer imageComparer = new ImageComparer();
	
	protected BufferedImage blackImage;
	protected BufferedImage whiteImage;
	protected BufferedImage greyImage;
	protected BufferedImage smallImage;
	
	protected static double NUMBER_OF_PIXELS_IN_720P = 921_600d;

	public ImageComparerCommon() {
		try {
			blackImage = getBufferedImageFromFile("image/black.png");
			whiteImage = getBufferedImageFromFile("image/white.png");
			greyImage  = getBufferedImageFromFile("image/grey.png");
			smallImage = getBufferedImageFromFile("image/small.png");
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private BufferedImage getBufferedImageFromFile(String filename) throws IOException {
		File f = ResourceUtil.getResource(filename);
		return ImageIO.read(f);
	}

}