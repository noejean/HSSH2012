
package hssh.util.webcam;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Xavier
 */
public class Video
{
	private String path;
	private IMediaWriter writer;
	private long startTime;


	/* CONSTRUCTORS */

	public Video(String path, int width, int height)
	{
		this.path = path;
		this.writer = ToolFactory.makeWriter(this.path);
		this.writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, width, height);
		this.startTime = System.nanoTime();
	}


	/* ACCESSORS */




	/* METHODS */


	public void addImage(BufferedImage img)
	{
		//if (this.startTime == 0)
		//	this.startTime = System.nanoTime();
		
		BufferedImage img2 = convertToType(img, BufferedImage.TYPE_3BYTE_BGR);
		this.writer.encodeVideo(0, img2, System.nanoTime()-startTime, TimeUnit.NANOSECONDS);
	}


	public void close()
	{
		this.writer.close();
	}

	
	private static BufferedImage convertToType(BufferedImage sourceImage, int targetType)
	{
		BufferedImage image;

		// if the source image is already the target type, return the source image
		if (sourceImage.getType() == targetType)
			image = sourceImage;

		// otherwise create a new image of the target type and draw the new image
		else {
			image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(),
					targetType);
			image.getGraphics().drawImage(sourceImage, 0, 0, null);
		}

		return image;
	}
}

