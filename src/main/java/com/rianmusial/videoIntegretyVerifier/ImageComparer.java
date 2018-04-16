package com.rianmusial.videoIntegretyVerifier;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.stream.IntStream;

import com.rianmusial.videoIntegretyVerifier.exception.IllegalComparisonException;

public class ImageComparer {
	
	private static final double RED_LUMINANCE = 0.2126d;
	private static final double GREEN_LUMINANCE = 0.7152d;
	private static final double BLUE_LUMINANCE = 0.0722d;
	
	/**
	 * Calculates Peak Signal Noise Ratio between the provided images
	 */
	public double getPSNR(BufferedImage image1, BufferedImage image2) {
		verifyImageSizesAreTheSame(image1, image2);		
		double rSquared = Math.pow(1, 2);
		double mse = getMeanSquaredError(image1, image2);
		return 10D * Math.log10(rSquared/ mse);
	}
	
	private void verifyImageSizesAreTheSame(BufferedImage image1, BufferedImage image2) {
		int height1 = image1.getHeight();
		int height2 = image2.getHeight();
		int width1 = image1.getWidth();
		int width2 = image2.getWidth();
		if (height1 != height2 || width1 != width2)
			throw new IllegalComparisonException("Comparing images with different resolutions is not supported");
	}

	private double getMeanSquaredError(BufferedImage image1, BufferedImage image2) {
		double totalLuminanceDifference = getTotalLuminanceDifference(image1, image2);
		long totalPixels = image1.getHeight() * image1.getWidth();
		double meanError = totalLuminanceDifference / totalPixels;
		return Math.pow(meanError, 2);
	}
	
	private double getTotalLuminanceDifference(BufferedImage image1, BufferedImage image2) {
		DoubleAdder da = new DoubleAdder();
		IntStream.range(0, image1.getHeight()).parallel().forEach(y -> {
			IntStream.range(0, image1.getWidth()).parallel().forEach(x -> {
				double luminance1 = getLuminanceAtPixel(image1, x, y);
				double luminance2 = getLuminanceAtPixel(image2, x, y);
				da.add(Math.abs(luminance1 - luminance2));
			});
		});
		return da.doubleValue();
	}
	
	private double getLuminanceAtPixel(BufferedImage image, int x, int y) {
		int binaryRGB = image.getRGB(x, y);
		Color c = new Color(binaryRGB);
		return getLuminance(c);
	}
	
	private double getLuminance(Color c) {
		double luminance = 0d;
		luminance += (c.getRed()   /255d) * RED_LUMINANCE;
		luminance += (c.getGreen() /255d) * GREEN_LUMINANCE;
		luminance += (c.getBlue()  /255d) * BLUE_LUMINANCE;
		return luminance;
	}
	
}
