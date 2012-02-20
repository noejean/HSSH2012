
package hssh.util.webcam;

import java.awt.image.BufferedImage;

/**
 *
 * @author Xavier
 */
public class WebcamVideo extends Thread
{
	private Webcam webcam;
	private Video video;

	/** nb of image per second */
	private int frameRate;

	/** video duration in seconds */
	private int duration;


	/* CONSTRUCTORS */

	public WebcamVideo(Webcam webcam, String videoPath, int duration, int framerate)
	{
		this.webcam = webcam;
		this.duration = duration;
		this.frameRate = framerate;
		this.video = new Video(videoPath, this.webcam.getImageWidth(), this.webcam.getImageHeight());
	}


	/* METHODS */

	public void startRecord() throws InterruptedException
	{
		this.start();
	}

	
	/* METHODS : Thread */

	@Override
	public void run()
	{
		for (int i=0; i<this.frameRate*this.duration; i++)
		{
			BufferedImage img = webcam.getImage();
			this.video.addImage(img);

			try {
				Thread.sleep((long) ((1. / this.frameRate) * 1000));
			} catch (InterruptedException ex) {}
		}
		this.video.close();
	}
}
