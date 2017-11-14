package javax.swing;

import java.awt.*;

import java.net.*;

/**
 * @author Damian Minkov
 */
public class ImageIcon
    implements Icon
{
    int width;
    int height;

    public ImageIcon (URL location) {

    }

    public Image getImage() {return  null;}

    public void paintIcon(Component c, Graphics g, int x, int y)
    {}

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }
}
