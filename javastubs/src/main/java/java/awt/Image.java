package java.awt;

import java.awt.image.*;

/**
 * @author Damian Minkov
 */
public abstract class Image
{
    public abstract int getWidth(ImageObserver observer);

    public abstract int getHeight(ImageObserver observer);
}
