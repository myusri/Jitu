/*
 * Jitsi, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package java.awt;

import java.awt.event.*;

/**
 *
 * @author Lyubomir Marinov
 * @author Damian Minkov
 * @author Pawel Domas
 */
public class Component
{
    private int height;

    private int prefHeight;

    private boolean prefSizeIsSet;

    private int prefWidth;

    private int width;

    public void addComponentListener(ComponentListener l) {}

    public void addHierarchyListener(HierarchyListener l) {}

    public Color getBackground()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public int getHeight()
    {
        return height;
    }

    public String getName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Container getParent()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(prefWidth, prefHeight);
    }

    public Dimension getSize()
    {
        return new Dimension(width, height);
    }

    public int getWidth()
    {
        return width;
    }

    public boolean isDisplayable()
    {
        return true;
    }

    public boolean isEnabled()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isPreferredSizeSet()
    {
        return prefSizeIsSet;
    }

    public boolean isVisible()
    {
        return true;
    }

    public void paint(Graphics g)
    {
        // TODO Auto-generated method stub
    }

    public void removeHierarchyListener(HierarchyListener l) {}

    public void repaint()
    {
        update(null);
    }

    public void setBackground(Color background) {}

    public void setEnabled(boolean enabled) {}

    public void setLocation(int x, int y) {}

    public void setMaximumSize(Dimension maximumSize) {}

    public void setName(String name) {}

    public void setPreferredSize(Dimension preferredSize)
    {
        if (preferredSize == null)
        {
            prefWidth = 0;
            prefHeight = 0;
            prefSizeIsSet = false;
        }
        else
        {
            prefWidth = preferredSize.width;
            prefHeight = preferredSize.height;
            prefSizeIsSet = true;
        }
    }

    public void setSize(Dimension size)
    {
        if (size == null)
            setSize(0, 0);
        else
            setSize(size.width, size.height);
    }

    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void setVisible(boolean visible) {}

    public void update(Graphics g)
    {
        paint(g);
    }
}
