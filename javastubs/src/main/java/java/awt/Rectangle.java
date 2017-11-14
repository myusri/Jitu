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
public class Rectangle
{
    public int height;

    public int width;

    public int x;

    public int y;

    public Rectangle()
    {
    }

    public Rectangle(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setSize(Dimension d)
    {
        setSize(d.width, d.height);
    }

    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void setBounds(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setBounds(Rectangle r)
    {
        setBounds(r.x, r.y, r.width, r.height);
    }
}