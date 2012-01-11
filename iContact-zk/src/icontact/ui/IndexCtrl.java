package icontact.ui;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;

public class IndexCtrl extends GenericForwardComposer
{

	private static final long serialVersionUID = 632539699781131834L;

	Button cmdHello;

	public void onClick$cmdHello(Event event)
	{
		try
		{
			Messagebox.show("You clicked me!");
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
