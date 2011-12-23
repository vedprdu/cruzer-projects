package com.mauricerogers.addresbook.client;

import java.util.Date;

public class Person
{
	String firstName;
	String LastName;
	Date dob;
	String address;
	String notes;

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
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
		return LastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName)
	{
		LastName = lastName;
	}

	/**
	 * @return the dob
	 */
	public Date getDob()
	{
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return the notes
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes)
	{
		this.notes = notes;
	}

}
