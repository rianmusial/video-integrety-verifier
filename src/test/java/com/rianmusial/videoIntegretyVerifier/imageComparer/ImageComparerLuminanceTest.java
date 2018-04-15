package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.Color;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;
import com.rianmusial.videoIntegretyVerifier.util.ReflectionUtil;

public class ImageComparerLuminanceTest extends ImageComparerCommon {
	
	@Test
	public void luminanceWhiteCalculationTest() throws Exception {
		Color c = new Color(255, 255, 255);
		Method m = ReflectionUtil.getMethod("getLuminance", ImageComparer.class, Color.class);
		double d = (Double) m.invoke(imageComparer, c);
		Assert.assertEquals(1d, d, .0000001);
	}

	@Test
	public void luminanceBlackCalculationTest() throws Exception {
		Color c = new Color(0, 0, 0);
		Method m = ReflectionUtil.getMethod("getLuminance", ImageComparer.class, Color.class);
		double d = (Double) m.invoke(imageComparer, c);
		Assert.assertEquals(0d, d, .0000001);
	}
	
	@Test
	public void luminanceGreyCalculationTest() throws Exception {
		Color c = new Color(127, 127, 127);
		Method m = ReflectionUtil.getMethod("getLuminance", ImageComparer.class, Color.class);
		double d = (Double) m.invoke(imageComparer, c);
		Assert.assertEquals(0.49803921568627450980392156862745d, d, .0000001);
	}

}
