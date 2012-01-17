package icontact.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class AddContactCtrl extends GenericForwardComposer
{

    /**
     * 
     */
    private static final long serialVersionUID = -1610162893647032853L;
    Textbox firstName;
    Textbox lastName;
    Datebox dob;
    Textbox emailAddress;
    Textbox emailTitle;
    Button cmdEmailAdd;
    Button cmdEmailRemove;

    public void onClick$cmdEmailAdd(Event event)
    {
        try
        {
            Messagebox.show("Add Email Clicked");
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(AddContactCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onClick$cmdEmailRemove(Event event)
    {
        try
        {
            Messagebox.show("Remove Email Clicked");
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(AddContactCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
