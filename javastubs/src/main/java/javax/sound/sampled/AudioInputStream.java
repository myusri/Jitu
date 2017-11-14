package javax.sound.sampled;

import java.io.*;

/**
 * @author Damian Minkov
 */
public class AudioInputStream
    extends InputStream
{
    @Override
    public int read()
        throws IOException
    {
        return 0;
    }

    public AudioFormat getFormat()
    {
        return null;
    }

    public int available()
        throws IOException
    {
        return 0;
    }
}
