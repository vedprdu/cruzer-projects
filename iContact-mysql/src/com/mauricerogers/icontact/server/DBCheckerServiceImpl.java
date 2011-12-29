package com.mauricerogers.icontact.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mauricerogers.icontact.client.DBCheckerService;

@SuppressWarnings("serial")
public class DBCheckerServiceImpl extends RemoteServiceServlet implements DBCheckerService
{

	@Override
	public boolean isConnectionSuccessful()
	{
		ConnectionTest.createConnection();

		if (ConnectionTest.getConnection() != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
