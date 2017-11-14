package javax.swing.filechooser;

import android.os.*;

import java.io.*;

/**
 * @author Damian Minkov
 */
public class FileSystemView
    extends Object
{
    public static FileSystemView getFileSystemView()
    {
        return new FileSystemView();
    }

    public File getHomeDirectory()
    {
        return Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS);
    }
}
