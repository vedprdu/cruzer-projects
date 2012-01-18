package icontact.contact;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

public class DynamicRowRenderer implements RowRenderer
{

    @Override
    public void render(final Row row, final java.lang.Object data)
    {
        String[] ary = (String[]) data;
        row.appendChild(new Image("/images/email.png"));
        new Label(ary[0]).setParent(row);
        new Label(ary[1]).setParent(row);
        final Button cmdDelete = new Button();
        cmdDelete.addEventListener(Events.ON_CLICK, new EventListener()
        {

            @Override
            public void onEvent(Event event) throws Exception
            {
                // get temp data from session (only in demo)
                ContactNumber contactData = (ContactNumber) Executions.getCurrent().getSession().getAttribute(ContactNumber.class.getName());
                contactData.deleteContacts(data);
                row.getGrid().setModel(new ListModelList(contactData.getContacts()));
            }
        });
        cmdDelete.setImage("/images/delete.png");
        row.appendChild(cmdDelete);
    }
}