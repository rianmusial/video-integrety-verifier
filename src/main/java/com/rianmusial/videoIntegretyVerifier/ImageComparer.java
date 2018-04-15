package com.rianmusial.videoIntegretyVerifier;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageComparer {
	
	private static final double RED_LUMINANCE = 0.2126d;
	private static final double GREEN_LUMINANCE = 0.7152d;
	private static final double BLUE_LUMINANCE = 0.0722d;
	
	private double getLuminanceAtPixel(BufferedImage image, int x, int y) {
		int binaryRGB = image.getRGB(x, y);
		Color c = new Color(binaryRGB);
		return getLuminance(c);
	}
	
	private double getLuminance(Color c) {
		double luminance = 0d;
		luminance += (	c.getRed()		/255d) 	* 	RED_LUMINANCE;
		luminance += (	c.getGreen()	/255d) 	* 	GREEN_LUMINANCE;
		luminance += (	c.getBlue()		/255d) 	* 	BLUE_LUMINANCE;
		return luminance;
	}
}
