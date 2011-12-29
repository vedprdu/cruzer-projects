package com.mauricerogers.icontact.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IContact_mysql implements EntryPoint
{

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		RootPanel menuPanel = RootPanel.get("menuArea");
		menuPanel.setSize("100%", "100%");
		menuPanel.getElement().getStyle().setPosition(Position.RELATIVE);
		menuPanel.setStyleName("grid_3");
		Widget menu = new menuPanel();
		menuPanel.add(menu);
		
		RootPanel appPanel = RootPanel.get("appArea");
		appPanel.setSize("100%", "100%");
		appPanel.getElement().getStyle().setPosition(Position.RELATIVE);
		appPanel.setStyleName("grid_9");
		Widget mysqlTest = new MysqlConnectionTest();
		appPanel.add(mysqlTest);
	}
}
