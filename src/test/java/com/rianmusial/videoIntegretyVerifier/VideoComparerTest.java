package com.rianmusial.videoIntegretyVerifier;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.Before;
import org.junit.Test;

import com.rianmusial.videoIntegretyVerifier.util.ReflectionUtil;
import com.rianmusial.videoIntegretyVerifier.util.ResourceUtil;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class VideoComparerTest {
	
	private ImageComparer imageComparer = new ImageComparer();
	private VideoComparer videoComparer = new VideoComparer();
	private Java2DFrameConverter converter = new Java2DFrameConverter();
	
	private Method method;
	private File blackMp4;
	private File blackMkv;
	
	@Before
	public void setup() throws NoSuchMethodException {
		blackMp4 = ResourceUtil.getResource("video/black.mp4");
		blackMp4 = ResourceUtil.getResource("video/black.mkv");
		method = ReflectionUtil.getMethod("getFrameGrabber", VideoComparer.class, File.class);
	}

	@Test
	public void ValidateCorrectImageIsGrabbedFromGrabber() throws Exception {
		BufferedImage image = getFirstFrame(blackMp4);
		BufferedImage blackImage = getSolidImageByColor(0);
		double psnr = imageComparer.getPSNR(image, blackImage);
		Assert.assertEquals(Double.POSITIVE_INFINITY, psnr, 0.0000001D);
	}

	private BufferedImage getFirstFrame(File file) throws Exception {
		FFmpegFrameGrabber grabber = (FFmpegFrameGrabber) method.invoke(videoComparer, file);
		Frame frame = grabber.grabImage();
		BufferedImage image = converter.getBufferedImage(frame);
		return image;
	}

	private BufferedImage getSolidImageByColor(int i) {
		int width = 1920;
		int height = 1080;
		Color c = new Color(i, i, i);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		IntStream.range(0, width).parallel().forEach(x -> {
			IntStream.range(0, height).parallel().forEach(y -> {
				image.setRGB(x, y, c.getRGB());
			});
		});
		return image;
	}
	
	@Test
	public void ValidateOutputForIdenticalVideos() throws Exception {
		Assert.fail("");
	}

}
