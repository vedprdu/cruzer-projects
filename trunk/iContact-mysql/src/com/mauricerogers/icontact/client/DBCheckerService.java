package com.mauricerogers.icontact.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dbChecker")
public interface DBCheckerService extends RemoteService
{
	public boolean isConnectionSuccessful();
}
