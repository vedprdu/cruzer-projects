/************************************************************************/
/* iLoan - Quick Loan Management System                                 */
/* ===========================                                          */
/*                                                                      */
/* Copyright (c) 2011 by Maurice Rogers                                 */
/* http://www.mauricerogers.com                                         */
/************************************************************************/

/*
 * ILoanApp.java
 */
package iloan;

import iloan.kernel.Environment;
import iloan.kernel.LookAndFeel;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class ILoanApp extends SingleFrameApplication
{

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup()
    {
        //Initialize the environment
        Environment environment = new Environment();

        //Set the look and feel of the application.
        LookAndFeel lookAndFeel = new LookAndFeel();

        show(new ILoanView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     * @param root 
     */
    @Override
    protected void configureWindow(java.awt.Window root)
    {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ILoanApp
     */
    public static ILoanApp getApplication()
    {
        return Application.getInstance(ILoanApp.class);
    }

    /**
     * Main method launching the application.
     * @param args
     */
    public static void main(String[] args)
    {
        launch(ILoanApp.class, args);
    }
}
