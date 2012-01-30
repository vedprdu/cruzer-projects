/************************************************************************/
/* iLoan - Quick Loan Management System                                 */
/* ===========================                                          */
/*                                                                      */
/* Copyright (c) 2011 by Maurice Rogers                                 */
/* http://www.mauricerogers.com                                         */
/************************************************************************/
/*
 * ILoanView.java
 */
package iloan;

import iloan.pawn.FrmNewPawn;
import iloan.customer.FrmEditCustomer;
import iloan.kernel.LookAndFeel;
import iloan.customer.FrmNewCustomer;
import iloan.customer.FrmViewCustomer;
import iloan.kernel.Environment;
import iloan.security.FrmLogin;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 * The application's main frame.
 */
public class ILoanView extends FrameView
{

    FrmNewCustomer frmNewCustomer = null;
    FrmEditCustomer frmEditCustomer = null;
    FrmViewCustomer frmViewCustomer = null;
    FrmNewPawn frmNewPawn = null;
    ResourceMap resourceMap = getResourceMap();

    public ILoanView(SingleFrameApplication app)
    {
        super(app);

        //Set the frame icon.
        getFrame().setIconImage(resourceMap.getImageIcon("Application.Icon").getImage());

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc

        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++)
        {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {

            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName))
                {
                    if (!busyIconTimer.isRunning())
                    {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                }
                else if ("done".equals(propertyName))
                {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                }
                else if ("message".equals(propertyName))
                {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                }
                else if ("progress".equals(propertyName))
                {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

        showLoginScreen();
    }

    @Action
    public void showAboutBox()
    {
        if (aboutBox == null)
        {
            JFrame mainFrame = ILoanApp.getApplication().getMainFrame();
            aboutBox = new ILoanAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ILoanApp.getApplication().show(aboutBox);
    }

    private void showLoginScreen()
    {
        FrmLogin frmLogin = new FrmLogin(this.getFrame(), true);
        frmLogin.setLocationRelativeTo(this.getFrame());
        frmLogin.setVisible(true);
    }

    /**
     * This method is used to load the add new customer window.
     */
    @Action
    public void newCustomer()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("New Customer");
        if (AlreadyLoaded == false)
        {
            frmNewCustomer = new FrmNewCustomer();
            desktopPane.add(frmNewCustomer);

            //Load the Form
            frmNewCustomer.setVisible(true);
            frmNewCustomer.show();
            try
            {
                frmNewCustomer.setIcon(false);
                frmNewCustomer.setSelected(true);
            }
            catch (Exception e)
            {
                String message = "An error occurred while loading the new customer form.";
                Logger.getLogger(ILoanView.class.getName()).log(Level.SEVERE, message, e);
            }
        }
    }//end newCustomer()

    /**
     * This method is used to load the add new customer window.
     */
    @Action
    public void showEditCustomer()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Customer");
        if (AlreadyLoaded == false)
        {
            frmEditCustomer = new FrmEditCustomer();
            desktopPane.add(frmEditCustomer);

            //Load the Form
            frmEditCustomer.setVisible(true);
            frmEditCustomer.show();
            try
            {
                frmEditCustomer.setIcon(false);
                frmEditCustomer.setSelected(true);
            }
            catch (Exception e)
            {
                String message = "An error occurred while loading the edit customer form.";
                Logger.getLogger(ILoanView.class.getName()).log(Level.SEVERE, message, e);
            }
        }
    }//end showEditCustomer()

    /**
     * This method is used to load the add new customer window.
     */
    @Action
    public void showViewCustomer()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("View Customer");
        if (AlreadyLoaded == false)
        {
            frmViewCustomer = new FrmViewCustomer();
            desktopPane.add(frmViewCustomer);

            //Load the Form
            frmViewCustomer.setVisible(true);
            frmViewCustomer.show();
            try
            {
                frmViewCustomer.setIcon(false);
                frmViewCustomer.setSelected(true);
            }
            catch (Exception e)
            {
                String message = "An error occurred while loading the view customer form.";
                Logger.getLogger(ILoanView.class.getName()).log(Level.SEVERE, message, e);
            }
        }
    }//end showViewCustomer()

    /**
     * This method is used to load the add new pawn window.
     */
    @Action
    public void showNewPawn()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Pawn Item");
        if (AlreadyLoaded == false)
        {
            frmNewPawn = new FrmNewPawn();
            desktopPane.add(frmNewPawn);

            //Load the Form
            frmNewPawn.setVisible(true);
            frmNewPawn.show();
            try
            {
                frmNewPawn.setIcon(false);
                frmNewPawn.setSelected(true);
            }
            catch (Exception e)
            {
                String message = "An error occurred while loading the new pawn form.";
                Logger.getLogger(ILoanView.class.getName()).log(Level.SEVERE, message, e);
            }
        }
    }//end showNewPawn()

    /**
     * This function checks if a specified form is already displayed.
     * It accepts the window title in the form of a string and checks if
     * it is already loaded onto the desktop pane.  It then returns a boolean
     * depending on the result of the test.
     *
     * @param FormTitle
     * @return True if a loaded frame contains the specified string in title.
     *         False if no frames contains the specified string.
     */
    protected boolean isLoaded(String FormTitle)
    {
        JInternalFrame Form[] = desktopPane.getAllFrames();
        for (int i = 0; i < Form.length; i++)
        {
            if (Form[i].getTitle().equalsIgnoreCase(FormTitle))
            {
                Form[i].show();
                try
                {
                    Form[i].setIcon(false);
                    Form[i].setSelected(true);
                }
                catch (Exception e)
                {
                    String message = "An error occurred while loading the form - " + Form[i].getTitle();
                    Logger.getLogger(ILoanView.class.getName()).log(Level.SEVERE, message, e);
                }
                return true;
            }
        }
        return false;
    }//end isLoaded(String FormTitle)

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        customerMenu = new javax.swing.JMenu();
        newCustomer = new javax.swing.JMenuItem();
        editCustomer = new javax.swing.JMenuItem();
        viewCustomer = new javax.swing.JMenuItem();
        pawnMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        loanMenu = new javax.swing.JMenu();
        paymentMenu = new javax.swing.JMenu();
        salesMenu = new javax.swing.JMenu();
        reportsMenu = new javax.swing.JMenu();
        manageMenu = new javax.swing.JMenu();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        desktopPane.setName("desktopPane"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(iloan.ILoanApp.class).getContext().getResourceMap(ILoanView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(iloan.ILoanApp.class).getContext().getActionMap(ILoanView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        customerMenu.setText(resourceMap.getString("customerMenu.text")); // NOI18N
        customerMenu.setName("customerMenu"); // NOI18N

        newCustomer.setAction(actionMap.get("newCustomer")); // NOI18N
        newCustomer.setIcon(resourceMap.getIcon("newCustomer.icon")); // NOI18N
        newCustomer.setText(resourceMap.getString("newCustomer.text")); // NOI18N
        newCustomer.setName("newCustomer"); // NOI18N
        customerMenu.add(newCustomer);

        editCustomer.setAction(actionMap.get("showEditCustomer")); // NOI18N
        editCustomer.setIcon(resourceMap.getIcon("editCustomer.icon")); // NOI18N
        editCustomer.setText(resourceMap.getString("editCustomer.text")); // NOI18N
        editCustomer.setName("editCustomer"); // NOI18N
        customerMenu.add(editCustomer);

        viewCustomer.setAction(actionMap.get("showViewCustomer")); // NOI18N
        viewCustomer.setIcon(resourceMap.getIcon("viewCustomer.icon")); // NOI18N
        viewCustomer.setText(resourceMap.getString("viewCustomer.text")); // NOI18N
        viewCustomer.setName("viewCustomer"); // NOI18N
        customerMenu.add(viewCustomer);

        menuBar.add(customerMenu);

        pawnMenu.setText(resourceMap.getString("pawnMenu.text")); // NOI18N
        pawnMenu.setName("pawnMenu"); // NOI18N

        jMenuItem1.setAction(actionMap.get("showNewPawn")); // NOI18N
        jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        pawnMenu.add(jMenuItem1);

        menuBar.add(pawnMenu);

        loanMenu.setText(resourceMap.getString("loanMenu.text")); // NOI18N
        loanMenu.setName("loanMenu"); // NOI18N
        menuBar.add(loanMenu);

        paymentMenu.setText(resourceMap.getString("paymentMenu.text")); // NOI18N
        paymentMenu.setName("paymentMenu"); // NOI18N
        menuBar.add(paymentMenu);

        salesMenu.setText(resourceMap.getString("salesMenu.text")); // NOI18N
        salesMenu.setName("salesMenu"); // NOI18N
        menuBar.add(salesMenu);

        reportsMenu.setText(resourceMap.getString("reportsMenu.text")); // NOI18N
        reportsMenu.setName("reportsMenu"); // NOI18N
        menuBar.add(reportsMenu);

        manageMenu.setText(resourceMap.getString("manageMenu.text")); // NOI18N
        manageMenu.setName("manageMenu"); // NOI18N
        menuBar.add(manageMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu customerMenu;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem editCustomer;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu loanMenu;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenu manageMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newCustomer;
    private javax.swing.JMenu pawnMenu;
    private javax.swing.JMenu paymentMenu;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JMenu reportsMenu;
    private javax.swing.JMenu salesMenu;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JMenuItem viewCustomer;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
