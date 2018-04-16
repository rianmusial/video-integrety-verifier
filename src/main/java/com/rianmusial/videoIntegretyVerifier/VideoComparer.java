package com.rianmusial.videoIntegretyVerifier;

import static org.bytedeco.javacpp.avutil.AV_LOG_PANIC;
import static org.bytedeco.javacpp.avutil.av_log_set_level;

import java.io.File;

import org.bytedeco.javacv.FFmpegFrameGrabber;

public class VideoComparer {

	private FFmpegFrameGrabber getFrameGrabber(File f) {
		av_log_set_level(AV_LOG_PANIC);
		FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(f);
		try {
			grabber.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return grabber;
	}

}
