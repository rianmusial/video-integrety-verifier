package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;

import org.junit.Test;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;
import com.rianmusial.videoIntegretyVerifier.util.ReflectionUtil;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ImageComparerMeanSquaredErrorTest extends ImageComparerCommon {

	@Test
	public void calculationOfMeanSquaredErrorForIdenticalImage() throws Exception {
		Method m = ReflectionUtil.getMethod("getMeanSquaredError", ImageComparer.class, BufferedImage.class, BufferedImage.class);
		double d = (Double) m.invoke(imageComparer, whiteImage, whiteImage);
		Assert.assertEquals(0D, d, 0.000000001d);
	}
	
	

}
