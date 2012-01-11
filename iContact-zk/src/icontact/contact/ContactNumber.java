package icontact.contact;

import java.util.UUID;

public class ContactNumber
{
	String id = UUID.randomUUID().toString();
	String title;
	String number;

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the number
	 */
	public String getNumber()
	{
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number)
	{
		this.number = number;
	}

}
