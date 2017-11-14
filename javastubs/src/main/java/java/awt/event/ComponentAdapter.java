package java.awt.event;

/**
 * @author Damian Minkov
 */
public abstract class ComponentAdapter
    implements ComponentListener
{
    public void componentResized(ComponentEvent e) {}

    public void componentMoved(ComponentEvent e) {}

    public void componentShown(ComponentEvent e) {}

    public void componentHidden(ComponentEvent e) {}
}
