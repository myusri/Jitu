package javax.swing;

import java.awt.*;

/**
 * @author Damian Minkov
 */
public interface Icon
{
    public void paintIcon(Component c, Graphics g, int x, int y);

    public int getIconWidth();

    public int getIconHeight();
}
