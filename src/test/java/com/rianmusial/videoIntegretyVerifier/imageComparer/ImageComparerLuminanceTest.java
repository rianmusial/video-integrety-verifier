package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.Color;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;

public class ImageComparerLuminanceTest {
	
	@Test
	public void luminanceWhiteCalculationTest() throws Exception {
		ImageComparer ic = new ImageComparer();
		Color c = new Color(255, 255, 255);
		Method m = getMethodGetLuminance();
		double d = (Double) m.invoke(ic, c);
		Assert.assertEquals(1d, d, .0000001);
	}

	private Method getMethodGetLuminance() throws NoSuchMethodException {
		Method m = ImageComparer.class.getDeclaredMethod("getLuminance", Color.class);
		m.setAccessible(true);
		return m;
	}
	
	@Test
	public void luminanceBlackCalculationTest() throws Exception {
		ImageComparer ic = new ImageComparer();
		Color c = new Color(0, 0, 0);
		Method m = getMethodGetLuminance();
		double d = (Double) m.invoke(ic, c);
		Assert.assertEquals(0d, d, .0000001);
	}
	
	@Test
	public void luminanceGreyCalculationTest() throws Exception {
		ImageComparer ic = new ImageComparer();
		Color c = new Color(127, 127, 127);
		Method m = getMethodGetLuminance();
		double d = (Double) m.invoke(ic, c);
		Assert.assertEquals(0.49803921568627450980392156862745d, d, .0000001);
	}

}
