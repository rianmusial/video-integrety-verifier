package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;

import org.junit.Test;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;
import com.rianmusial.videoIntegretyVerifier.util.ReflectionUtil;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class LuminanceDifferenceCalculationTest extends ImageComparerCommon {
	
	private static double NUMBER_OF_PIXELS_IN_720P = 921_600d;

	@Test
	public void luminanceDifferenceBetweenIdenticalImagesTest() throws Exception {
		Method m = ReflectionUtil.getMethod("getTotalLuminanceDifference", ImageComparer.class, BufferedImage.class, BufferedImage.class);
		BufferedImage bi = getBufferedImageFromFile("image/white.png");
		double totalLuminance = (Double) m.invoke(imageComparer, bi, bi);
		Assert.assertEquals(0d, totalLuminance, 0.0000001d);
	}
	
	@Test
	public void luminanceDifferenceBetweenBlackAndWhiteImagesTest() throws Exception {
		Method m = ReflectionUtil.getMethod("getTotalLuminanceDifference", ImageComparer.class, BufferedImage.class, BufferedImage.class);
		BufferedImage image1 = getBufferedImageFromFile("image/white.png");
		BufferedImage image2 = getBufferedImageFromFile("image/black.png");		
		double totalLuminance = (Double) m.invoke(imageComparer, image1, image2);
		Assert.assertEquals(NUMBER_OF_PIXELS_IN_720P, totalLuminance, 0.0000001d);
	}
	
	@Test
	public void luminanceDifferenceBetweenBlackAndGreyImagesTest() throws Exception {
		Method m = ReflectionUtil.getMethod("getTotalLuminanceDifference", ImageComparer.class, BufferedImage.class, BufferedImage.class);
		BufferedImage image1 = getBufferedImageFromFile("image/grey.png");
		BufferedImage image2 = getBufferedImageFromFile("image/black.png");		
		double totalLuminance = (Double) m.invoke(imageComparer, image1, image2);
		double expectedLuminanceDifferencePerPixel = 0.49803921568627450980392156862745d;
		Assert.assertEquals(expectedLuminanceDifferencePerPixel * NUMBER_OF_PIXELS_IN_720P, totalLuminance, 0.000000000001d * NUMBER_OF_PIXELS_IN_720P);
	}
	
	@Test
	public void luminanceDifferenceBetweenWhiteAndGreyImagesTest() throws Exception {
		Method m = ReflectionUtil.getMethod("getTotalLuminanceDifference", ImageComparer.class, BufferedImage.class, BufferedImage.class);
		BufferedImage image1 = getBufferedImageFromFile("image/white.png");
		BufferedImage image2 = getBufferedImageFromFile("image/grey.png");		
		double totalLuminance = (Double) m.invoke(imageComparer, image1, image2);
		double expectedLuminanceDifferencePerPixel = 0.50196078431372549019607843137255d;
		Assert.assertEquals(expectedLuminanceDifferencePerPixel * NUMBER_OF_PIXELS_IN_720P, totalLuminance, 0.000000000001d * NUMBER_OF_PIXELS_IN_720P);
	}

}
