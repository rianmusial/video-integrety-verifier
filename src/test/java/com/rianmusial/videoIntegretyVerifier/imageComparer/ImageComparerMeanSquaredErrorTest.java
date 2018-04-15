package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;
import com.rianmusial.videoIntegretyVerifier.util.ReflectionUtil;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ImageComparerMeanSquaredErrorTest extends ImageComparerCommon {

	private Method method; 
	
	@Before
	public void setup() throws NoSuchMethodException {
		method = ReflectionUtil.getMethod("getMeanSquaredError", ImageComparer.class, BufferedImage.class, BufferedImage.class);
	}
	
	@Test
	public void calculationOfMeanSquaredErrorForIdenticalImage() throws Exception {
		double d = (Double) method.invoke(imageComparer, whiteImage, whiteImage);
		Assert.assertEquals(0D, d, 0.000000001d);
	}
	
	@Test
	public void calculationOfMeanSquaredErrorForBlackAndWhiteImages() throws Exception {
		double d = (Double) method.invoke(imageComparer, whiteImage, blackImage);
		Assert.assertEquals(1d, d, 0.000000001d);
	}
	
	@Test
	public void calculationOfMeanSquaredErrorForWhiteAndGreyImages() throws Exception {
		double d = (Double) method.invoke(imageComparer, whiteImage, greyImage);
		double expected = Math.pow(0.49803921568627450980392156862745d, 2);
		Assert.assertEquals(expected, d, 0.01d);
	}
	
	@Test
	public void calculationOfMeanSquaredErrorForBlackAndGreyImages() throws Exception {
		double d = (Double) method.invoke(imageComparer, greyImage, blackImage);
		double expected = Math.pow(0.50196078431372549019607843137255d, 2);
		Assert.assertEquals(expected, d, 0.01d);
	}

}
