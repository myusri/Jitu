/*
 * Jitsi, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package java.awt.event;

/**
 *
 * @author Lyubomir Marinov
 */
public class ActionEvent
    extends AWTEvent
{
    public ActionEvent(Object source, int id, String command)
    {
        super(source, id);
    }
}
