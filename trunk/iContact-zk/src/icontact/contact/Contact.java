package icontact.contact;

import java.util.ArrayList;
import java.util.Date;

public class Contact
{

	String firstName;
	String lastName;
	String otherName;
	Date dob;
	ArrayList<Address> address;
	ArrayList<ContactNumber> contactNumbers;
	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	/**
	 * @return the otherName
	 */
	public String getOtherName()
	{
		return otherName;
	}
	/**
	 * @param otherName the otherName to set
	 */
	public void setOtherName(String otherName)
	{
		this.otherName = otherName;
	}
	/**
	 * @return the dob
	 */
	public Date getDob()
	{
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob)
	{
		this.dob = dob;
	}
	/**
	 * @return the address
	 */
	public ArrayList<Address> getAddress()
	{
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(ArrayList<Address> address)
	{
		this.address = address;
	}
	/**
	 * @return the contactNumbers
	 */
	public ArrayList<ContactNumber> getContactNumbers()
	{
		return contactNumbers;
	}
	/**
	 * @param contactNumbers the contactNumbers to set
	 */
	public void setContactNumbers(ArrayList<ContactNumber> contactNumbers)
	{
		this.contactNumbers = contactNumbers;
	}
	
	
	
}
