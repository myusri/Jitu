/*
 * Jitsi, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package java.awt;


import java.awt.image.*;

/**
 *
 * @author Lyubomir Marinov
 */
public abstract class Graphics
{
    protected Graphics()
    {
        // TODO Auto-generated method stub
    }

    public abstract void setColor(Color c);

    public abstract void fillRect(int x, int y, int width, int height);

    public abstract boolean drawImage(Image img,
                          int dx1, int dy1, int dx2, int dy2,
                          int sx1, int sy1, int sx2, int sy2,
                          ImageObserver observer);

    public abstract boolean drawImage(Image img, int x, int y,
                          Color bgcolor,
                          ImageObserver observer);

    public abstract boolean drawImage(Image img, int x, int y,
                          ImageObserver observer);

    public abstract boolean drawImage(Image img, int x, int y,
                          int width, int height,
                          ImageObserver observer);
}
