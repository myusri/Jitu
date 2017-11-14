package java.awt.image;

/**
 * @author Damian Minkov
 */
public class BufferedImage
    extends java.awt.Image
{
    public BufferedImage(int width,
                             int height,
                             int imageType) {}

    public WritableRaster getRaster() {return null;}

    @Override
    public int getWidth(ImageObserver observer)
    {
        return 0;
    }

    @Override
    public int getHeight(ImageObserver observer)
    {
        return 0;
    }

    public int getWidth() { return 0; }

    public int getHeight() {return 0;}
}
