
package hssh.util.webcam;


import au.edu.jcu.v4l4j.FrameGrabber;
import au.edu.jcu.v4l4j.VideoDevice;
import au.edu.jcu.v4l4j.VideoFrame;
import au.edu.jcu.v4l4j.exceptions.V4L4JException;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xavier
 */
public class Webcam
{
	private String devicePath;
	private VideoDevice videodevice;
	private FrameGrabber framegrabber;

	private int imageWidth;
	private int imageHeight;
	private int imageQuality;


	/* CONSTRUCTORS */

	public Webcam(String devicePath)
	{
		this.devicePath = devicePath;

		try {
			this.videodevice = new VideoDevice(devicePath);
		} catch (V4L4JException ex) {
			System.err.println("Webcam not found on "+devicePath);
		}
	}


	/* ACCESSORS */

	public int getImageWidth() { return this.imageWidth; }
	public int getImageHeight() { return this.imageHeight; }
	public int getImageQuality() { return this.imageQuality; }

	
	/* METHODS */

	public void init(int width, int height, int quality)
	{
		int  input = 1;
		
		this.imageWidth = width;
		this.imageHeight = height;
		this.imageQuality = quality;

		try {
			this.framegrabber = this.videodevice.getJPEGFrameGrabber(width, height, input, 0, quality);
		} catch (V4L4JException ex) {
			System.err.println("Creation of a JPEG frame grabber failed on "+this.devicePath);
		}
	}


	public void start()
	{
		try {
			this.framegrabber.startCapture();
		} catch (V4L4JException ex) {
			Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	public void stop()
	{
		this.videodevice.release();
		this.framegrabber.stopCapture();
	}

	
	public BufferedImage getImage()
	{
		BufferedImage img;
		VideoFrame vf = null;
		try {
			vf = this.framegrabber.getVideoFrame();
		} catch (V4L4JException ex) {
			Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
		}
		img = vf.getBufferedImage();
		vf.recycle();
		return img;
	}
}

