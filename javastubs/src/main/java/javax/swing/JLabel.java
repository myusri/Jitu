package javax.swing;

/**
 * @author Damian Minkov
 */
public class JLabel
    extends JComponent
{
    private String text;

    public JLabel(String text)
    {
        this.text = text;
    }

    public void setHorizontalAlignment(int alignment) {}

    public void setVerticalAlignment(int alignment) {}

    public String getText() {return this.text;}
}
