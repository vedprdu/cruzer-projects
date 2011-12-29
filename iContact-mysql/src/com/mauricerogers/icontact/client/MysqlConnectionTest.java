package com.mauricerogers.icontact.client;

import java.io.Serializable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextArea;

@SuppressWarnings("serial")
public class MysqlConnectionTest extends Composite implements HasText, Serializable

{
	private DBCheckerServiceAsync dbCheckerServiceAsync = GWT.create(DBCheckerService.class);
	private static MysqlConnectionTestUiBinder uiBinder = GWT.create(MysqlConnectionTestUiBinder.class);

	interface MysqlConnectionTestUiBinder extends UiBinder<Widget, MysqlConnectionTest>
	{
	}

	public MysqlConnectionTest()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;
	@UiField
	TextArea txtOutput;

	public MysqlConnectionTest(String firstName)
	{
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e)
	{

		// Initialize the service proxy.
		if (dbCheckerServiceAsync == null)
		{
			dbCheckerServiceAsync = GWT.create(DBCheckerService.class);
		}

		// Set up the callback object.
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>()
		{

			@Override
			public void onFailure(Throwable caught)
			{
				String message = "An error occurred while trying to connect to the database.";
				Window.alert(message);
			}

			@Override
			public void onSuccess(Boolean result)
			{
				String message = "";
				if (result == true)
				{
					message = "Connected to app server.";
					txtOutput.setValue(txtOutput.getValue() + "\n" + message);
					message = "Trying to connect to database.";
					txtOutput.setValue(txtOutput.getValue() + "\n" + message);

					message = "Connection successful.";
					Window.alert(message);
				}
				else
				{
					message = "Could not connect to the database.";
					Window.alert(message);
				}
			}

		};

		// Make the call to the stock price service.
		// stockPriceSvc.getPrices(stocks.toArray(new String[0]), callback);
		dbCheckerServiceAsync.isConnectionSuccessful(callback);
	}

	public void setText(String text)
	{
		button.setText(text);
	}

	public String getText()
	{
		return button.getText();
	}

}
