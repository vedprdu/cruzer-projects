package icontact.contact;

import java.util.UUID;

public class Address
{
	String id = UUID.randomUUID().toString();
	String title;
	String line1;
	String line2;

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
	 * @return the line1
	 */
	public String getLine1()
	{
		return line1;
	}

	/**
	 * @param line1
	 *            the line1 to set
	 */
	public void setLine1(String line1)
	{
		this.line1 = line1;
	}

	/**
	 * @return the line2
	 */
	public String getLine2()
	{
		return line2;
	}

	/**
	 * @param line2
	 *            the line2 to set
	 */
	public void setLine2(String line2)
	{
		this.line2 = line2;
	}

}
