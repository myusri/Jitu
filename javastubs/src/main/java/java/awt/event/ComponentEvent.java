/*
 * Jitsi, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package java.awt.event;

import java.awt.*;

/**
 *
 * @author Lyubomir Marinov
 */
public class ComponentEvent
    extends AWTEvent
{
    public ComponentEvent(Object source, int id)
    {
        super(source, id);
    }

    public Component getComponent()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
