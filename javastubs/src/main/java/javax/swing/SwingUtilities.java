package javax.swing;

/**
 * @author Damian Minkov
 */
public class SwingUtilities
{
    public static boolean isEventDispatchThread()
    {
        return true;
    }

    public static void invokeLater(Runnable doRun)
    {
        new Thread(doRun).start();
    }
}
