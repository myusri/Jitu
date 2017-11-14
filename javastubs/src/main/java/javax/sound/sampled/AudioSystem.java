package javax.sound.sampled;

import java.io.*;
import java.net.*;

/**
 * @author Damian Minkov
 */
public class AudioSystem
{
    public static AudioInputStream getAudioInputStream(URL url)
        throws UnsupportedAudioFileException, IOException
    {
        return null;
    }

    public static AudioFormat[] getTargetFormats(AudioFormat.Encoding targetEncoding, AudioFormat sourceFormat)
    {
        return new AudioFormat[0];
    }
}
