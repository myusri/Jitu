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
public class Frame
    extends Window
{
    public static final int NORMAL = 0;

    public static final int ICONIFIED = 1;

    public static final int MAXIMIZED_HORIZ = 2;

    public static final int MAXIMIZED_VERT = 4;

    public static final int MAXIMIZED_BOTH = MAXIMIZED_VERT | MAXIMIZED_HORIZ;

    public Frame()
    {
        this(null);
    }

    public Frame(String title)
    {
		// TODO Auto-generated method stub
        super(null);
    }

    public int getExtendedState() {return -1;}

    public void setExtendedState(int state) {}
}
