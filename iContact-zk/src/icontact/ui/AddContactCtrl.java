package icontact.ui;

import icontact.contact.ContactNumber;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
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
    Grid emailGrid;

  
    public void onClick$cmdEmailAdd(Event event)
    {
        String title = emailTitle.getValue();
        String email = emailAddress.getValue();
        List newEmail = new ArrayList<String[]>();
        newEmail.add(new String[]
                {
                    title, email
                });

        // get temp data from session (only in demo)
        ContactNumber contactData = (ContactNumber) Executions.getCurrent().getSession().getAttribute(ContactNumber.class.getName());
        contactData.addMails(newEmail);
        emailGrid.setModel(new ListModelList(contactData.getContacts()));
    }

}
