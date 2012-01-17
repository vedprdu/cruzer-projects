package icontact.ui;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;
import org.zkoss.zul.Menuitem;

public class MenuCtrl extends GenericForwardComposer
{
	private static final long serialVersionUID = -7822990150630855059L;
	Menuitem viewContacts;
	Menuitem addContacts;
	Menuitem editContact;
	Menuitem removeContact;
	Menuitem settings;
	Include app;

	public void onClick$viewContacts(Event event)
	{
		app.setSrc("/resources/ViewContacts.zul");
		app.setHeight("100%");
	}

	public void onClick$addContacts(Event event)
	{
		app.setSrc("/resources/addContact.zul");
	}

}
