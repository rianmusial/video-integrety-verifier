package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;
import com.rianmusial.videoIntegretyVerifier.util.ReflectionUtil;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ImageComparerImageAccessTest extends ImageComparerCommon {
	
	private Method method;
	
	@Before
	public void setup() throws NoSuchMethodException {
		method = ReflectionUtil.getMethod("getLuminanceAtPixel", ImageComparer.class, BufferedImage.class, int.class, int.class);
	}

	@Test
	public void imageLuminanceColorCalculationWhiteTest() throws Exception {
		int x = ThreadLocalRandom.current().nextInt(whiteImage.getWidth());
		int y = ThreadLocalRandom.current().nextInt(whiteImage.getHeight());
		double luminance = (Double) method.invoke(imageComparer, whiteImage, x, y);
		Assert.assertEquals(1d, luminance, 0.0000001);
	}
	
	@Test
	public void imageLuminanceColorCalculationBlackTest() throws Exception {
		int x = ThreadLocalRandom.current().nextInt(blackImage.getWidth());
		int y = ThreadLocalRandom.current().nextInt(blackImage.getHeight());
		double luminance = (Double) method.invoke(imageComparer, blackImage, x, y);
		Assert.assertEquals(0d, luminance, 0.0000001);
	}
	
	@Test
	public void imageLuminanceColorCalculationGreyTest() throws Exception {
		int x = ThreadLocalRandom.current().nextInt(greyImage.getWidth());
		int y = ThreadLocalRandom.current().nextInt(greyImage.getHeight());
		double luminance = (Double) method.invoke(imageComparer, greyImage, x, y);
		Assert.assertEquals(0.49803921568627450980392156862745d, luminance, 0.0000001);
	}

}
