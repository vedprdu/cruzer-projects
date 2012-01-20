package icontact.contact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContactNumber implements Comparable<String>
{

    List<String[]> contactNos = new ArrayList<String[]>() {};

    public ContactNumber()
    {
        initData();
    }

    private void initData()
    {
        if (!contactNos.isEmpty())
        {
            contactNos.removeAll(contactNos);
        }

        contactNos.add(new String[]
                {
                    "Home", "home@home.com"
                });
        contactNos.add(new String[]
                {
                    "Work", "work@work.com"
                });
    }

    public List<String[]> revertDeletedContacts()
    {
        initData();
        return getContacts();
    }

    public void deleteAllContacts()
    {
        contactNos.clear();
    }

    public void addMails(Collection c)
    {
        contactNos.addAll(c);
    }

    public List<String[]> getContacts()
    {
        return contactNos;
    }

    public void deleteContacts(Object o)
    {
        contactNos.remove(o);
    }

    @Override
    public int compareTo(String o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
