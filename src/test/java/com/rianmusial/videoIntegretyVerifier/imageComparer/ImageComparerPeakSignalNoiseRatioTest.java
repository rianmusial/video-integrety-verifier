package com.rianmusial.videoIntegretyVerifier.imageComparer;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;
import com.rianmusial.videoIntegretyVerifier.exception.IllegalComparisonException;
import com.rianmusial.videoIntegretyVerifier.util.ReflectionUtil;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ImageComparerPeakSignalNoiseRatioTest extends ImageComparerCommon {
	
	private Method meanErrorSquared;
	
	@Before
	public void setup() throws NoSuchMethodException {
		meanErrorSquared = ReflectionUtil.getMethod("getMeanSquaredError", ImageComparer.class, BufferedImage.class, BufferedImage.class);
	}

	@Test
	public void CalculatePeakSignalNoiseRatioForIdenticalFiles() {
		double psnr = imageComparer.getPSNR(whiteImage, whiteImage);
		Assert.assertEquals(Double.POSITIVE_INFINITY, psnr, 0.00000001d);
	}
	
	@Test
	public void CalculatePeakSignalNoiseRatioForBlackAndWhiteImages() {
		double psnr = imageComparer.getPSNR(whiteImage, blackImage);
		Assert.assertEquals(0d, psnr, 0.00001d);
	}
	
	@Test
	public void CalculatePeakSignalNoiseRatioForWhiteAndGreyImages() throws Exception {
		double psnr = imageComparer.getPSNR(whiteImage, greyImage);
		double mse = (double) meanErrorSquared.invoke(imageComparer, whiteImage, greyImage);
		double expectedPSNR = 10D * Math.log10(1 / mse);
		Assert.assertEquals(expectedPSNR, psnr, 0.00000001d);
	}
	
	@Test
	public void CalculatePeakSignalNoiseRatioForBlackAndGreyImages() throws Exception {
		double psnr = imageComparer.getPSNR(blackImage, greyImage);
		double mse = (double) meanErrorSquared.invoke(imageComparer, blackImage, greyImage);
		double expectedPSNR = 10D * Math.log10(1 / mse);
		Assert.assertEquals(expectedPSNR, psnr, 0.00000001d);
	}
	
	@Test(expected = IllegalComparisonException.class)
	public void PreventComparisonOfFilesWithDifferentResolutions() throws Exception {
		imageComparer.getPSNR(whiteImage, smallImage);
	}

}
