package com.mauricerogers.addresbook.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AddressBook implements EntryPoint
{
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad()
	{
		RootPanel rootPanel  = RootPanel.get("appArea");
				
		DockPanel dockPanel = new DockPanel();
		rootPanel.add(dockPanel, 10, 10);
		dockPanel.setSize("649px", "578px");
		
		SimplePanel simplePanel = new SimplePanel();
		dockPanel.add(simplePanel, DockPanel.NORTH);
		
		Image image = new Image("addressbook/Logo.png");
		simplePanel.setWidget(image);
		image.setSize("402px", "140px");
		
		SimplePanel simplePanel_1 = new SimplePanel();
		dockPanel.add(simplePanel_1, DockPanel.WEST);
		simplePanel_1.setWidth("162px");
		
		Tree tree = new Tree();
		simplePanel_1.setWidget(tree);
		tree.setSize("100%", "100%");
		
		TreeItem treeItem = tree.addItem(new Label("Menu"));
		
		treeItem.setState(true);
		
		SimplePanel simplePanel_2 = new SimplePanel();
		dockPanel.add(simplePanel_2, DockPanel.CENTER);
		
		Grid grid = new Grid(6, 2);
		simplePanel_2.setWidget(grid);
		grid.setSize("100%", "100%");
		
		Label lblNewLabel = new Label("First Name");
		grid.setWidget(0, 0, lblNewLabel);
		
		TextBox textBox = new TextBox();
		grid.setWidget(0, 1, textBox);
		textBox.setWidth("100%");
		
		Label lblLastName = new Label("Last Name");
		grid.setWidget(1, 0, lblLastName);
		
		TextBox textBox_1 = new TextBox();
		grid.setWidget(1, 1, textBox_1);
		textBox_1.setWidth("100%");
		
		Label lblNewLabel_1 = new Label("DOB");
		grid.setWidget(2, 0, lblNewLabel_1);
		
		DateBox dateBox = new DateBox();
		dateBox.setFormat(new DefaultFormat(DateTimeFormat.getMediumDateFormat()));
		grid.setWidget(2, 1, dateBox);
		dateBox.setWidth("100%");
		
		Label lblAddress = new Label("Address");
		grid.setWidget(3, 0, lblAddress);
		grid.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
		
		TextArea textArea = new TextArea();
		textArea.setVisibleLines(4);
		grid.setWidget(3, 1, textArea);
		textArea.setWidth("100%");
		
		Label lblNotes = new Label(" Notes");
		grid.setWidget(4, 0, lblNotes);
		grid.getCellFormatter().setVerticalAlignment(4, 0, HasVerticalAlignment.ALIGN_TOP);
		
		RichTextArea richTextArea_1 = new RichTextArea();
		grid.setWidget(4, 1, richTextArea_1);
		richTextArea_1.setWidth("100%");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		grid.setWidget(5, 1, horizontalPanel);
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("Save");
		horizontalPanel.add(btnNewButton);
		
		Button btnCancel = new Button("Cancel");
		horizontalPanel.add(btnCancel);
		
		SimplePanel simplePanel_3 = new SimplePanel();
		dockPanel.add(simplePanel_3, DockPanel.SOUTH);

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler
		{
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			@Override
			public void onClick(ClickEvent event)
			{
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			@Override
			public void onKeyUp(KeyUpEvent event)
			{
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				{
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer()
			{

			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
	}
}
