/*
 * Jitsi, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package java.awt;

/**
 *
 * @author Lyubomir Marinov
 */
public class Dimension
{
    private static final long serialVersionUID = 0L;

    public int height;

    public int width;

    public Dimension()
    {
    }

    public Dimension(Dimension d)
    {
        this(d.width, d.height);
    }

    public Dimension(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        else if (obj instanceof Dimension)
        {
            Dimension dim = (Dimension) obj;

            return ((width == dim.width) && (height == dim.height));
        }
        else
            return false;        
    }

    public double getHeight()
    {
        return height;
    }

    public double getWidth()
    {
        return width;
    }

    @Override
    public int hashCode()
    {
        return ((width << 16) | (height >> 16));
    }

    @Override
    public String toString() {
        return "(w:" + width + ", h:" + height + ")";
    }
}
