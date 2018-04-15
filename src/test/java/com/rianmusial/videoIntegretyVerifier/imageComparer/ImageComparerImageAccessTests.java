package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;
import com.rianmusial.videoIntegretyVerifier.util.ReflectionUtil;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ImageComparerImageAccessTests {
	
	private ImageComparer imageComparer = new ImageComparer();

	@Test
	public void imageLuminanceColorCalculationWhiteTest() throws Exception {
		BufferedImage bi = getBufferedImageFromFile("image/white.png");
		int x = ThreadLocalRandom.current().nextInt(bi.getWidth());
		int y = ThreadLocalRandom.current().nextInt(bi.getHeight());
		Method m = ReflectionUtil.getMethod("getLuminanceAtPixel", ImageComparer.class, BufferedImage.class, int.class, int.class);
		double luminance = (Double) m.invoke(imageComparer, bi, x, y);
		Assert.assertEquals(1d, luminance, 0.0000001);
	}
	
	@Test
	public void imageLuminanceColorCalculationBlackTest() throws Exception {
		BufferedImage bi = getBufferedImageFromFile("image/black.png");
		int x = ThreadLocalRandom.current().nextInt(bi.getWidth());
		int y = ThreadLocalRandom.current().nextInt(bi.getHeight());
		Method m = ReflectionUtil.getMethod("getLuminanceAtPixel", ImageComparer.class, BufferedImage.class, int.class, int.class);
		double luminance = (Double) m.invoke(imageComparer, bi, x, y);
		Assert.assertEquals(0d, luminance, 0.0000001);
	}
	
	@Test
	public void imageLuminanceColorCalculationGreyTest() throws Exception {
		BufferedImage bi = getBufferedImageFromFile("image/grey.png");
		int x = ThreadLocalRandom.current().nextInt(bi.getWidth());
		int y = ThreadLocalRandom.current().nextInt(bi.getHeight());
		Method m = ReflectionUtil.getMethod("getLuminanceAtPixel", ImageComparer.class, BufferedImage.class, int.class, int.class);
		double luminance = (Double) m.invoke(imageComparer, bi, x, y);
		Assert.assertEquals(0.49803921568627450980392156862745d, luminance, 0.0000001);
	}

	private BufferedImage getBufferedImageFromFile(String s) throws IOException {
		URL resourceUrl = ImageComparerImageAccessTests.class.getClassLoader().getResource(s);
		File f = new File(resourceUrl.getFile());
		BufferedImage bi = ImageIO.read(f);
		return bi;
	}

}
