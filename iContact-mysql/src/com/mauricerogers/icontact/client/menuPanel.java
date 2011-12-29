/**
 * 
 */
package com.mauricerogers.icontact.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author m.rogers
 * 
 */
public class menuPanel extends Composite implements HasText
{

	private static menuPanelUiBinder uiBinder = GWT.create(menuPanelUiBinder.class);

	interface menuPanelUiBinder extends UiBinder<Widget, menuPanel>
	{
	}

	/**
	 * Because this class has a default constructor, it can be used as a binder
	 * template. In other words, it can be used in other *.ui.xml files as
	 * follows: <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 * xmlns:g="urn:import:**user's package**">
	 * <g:**UserClassName**>Hello!</g:**UserClassName> </ui:UiBinder> Note that
	 * depending on the widget that is used, it may be necessary to implement
	 * HasHTML instead of HasText.
	 */
	public menuPanel()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	public menuPanel(String firstName)
	{
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public void setText(String text)
	{

	}

	/**
	 * Gets invoked when the default constructor is called and a string is
	 * provided in the ui.xml file.
	 */
	@Override
	public String getText()
	{
		return "";
	}

}
