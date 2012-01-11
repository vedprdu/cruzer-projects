package icontact.ui;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;

public class AddContactCtrl extends GenericForwardComposer
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1610162893647032853L;

	Textbox firstName;
	Textbox lastName;
	Textbox pwd1;
	Textbox pwd2;

	public void onChange$pwd2(Event event)
	{

		if (!pwd2.getValue().equals(pwd1.getValue()))
		{
			pwd2.setStyle("color:red");
		}
		else
		{
			pwd2.setStyle("color:black");
		}

	}

}
