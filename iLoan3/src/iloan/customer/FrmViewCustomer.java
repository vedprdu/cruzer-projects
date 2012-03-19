package iloan.customer;

import iloan.kernel.Utilities;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author mrogers
 */
public class FrmViewCustomer extends javax.swing.JInternalFrame
{

    static final Logger logger = Logger.getLogger(FrmViewCustomer.class.getName());
    File selectedFile = null;
    File tempImage = null;
    String validationText = "";
    int age = 0;

    /**
     * Creates new form FrmAddCustomer
     */
    public FrmViewCustomer()
    {
        initComponents();
        populateLists();
    }

    private void populateLists()
    {
        tblCustomers.setModel(Customer.getCustomerTable());
    }

   
    private void loadCustomerInfo()
    {
        int custID = Integer.valueOf(tblCustomers.getValueAt(tblCustomers.getSelectedRow(), 0).toString());
        HashMap<String, Object> customer = Customer.getByID(custID);

        txtCustID.setText(customer.get("id").toString());
        txtSalutation.setText(customer.get("salutation").toString());
        txtFirstName.setText(customer.get("firstName").toString());
        txtLastName.setText(customer.get("lastName").toString());
        txtOtherName.setText(customer.get("otherName").toString());
        txtDOB.setText(Utilities.MDY_Formatter.format((Date) customer.get("dob")));
        txtGender.setText(customer.get("gender").toString());
        txtIDType.setText(customer.get("idType").toString());
        txtIDNum.setText(customer.get("idNum").toString());
        txtHomePhone.setText(customer.get("landline").toString());
        txtCellPhone.setText(customer.get("cellPhone").toString());
        txtHomeEmail.setText(customer.get("homeEmail").toString());
        txtHomeAddress.setText(customer.get("homeAddress").toString());
        txtOccupation.setText(customer.get("occupation").toString());
        txtWorkplace.setText(customer.get("workplace").toString());
        txtWorkPhone.setText(customer.get("workTelephone").toString());
        txtWorkEmail.setText(customer.get("workEmail").toString());
        txtWorkAddress.setText(customer.get("workAddress").toString());
        txtNotes.setText(customer.get("notes").toString());
        txtRating.setText(customer.get("rating").toString());
        txtRegistered.setText(customer.get("created").toString());
        txtModified.setText(customer.get("modified").toString());

        try
        {
            Blob blob = (Blob) customer.get("image");
            ImageIcon ii = new ImageIcon(blob.getBytes(1, (int) blob.length()));
            int height = (int) (jScrollPane3.getHeight() * .95);
            int width = (int) (jScrollPane3.getWidth() * .95);
            if (ii.getIconHeight() > height || ii.getIconWidth() > width)
            {
                Image img = ii.getImage();
                try
                {
                    double thumbRatio = (double) width / (double) height;
                    int imageWidth = img.getWidth(null);
                    int imageHeight = img.getHeight(null);
                    double aspectRatio = (double) imageWidth / (double) imageHeight;
                    if (thumbRatio < aspectRatio)
                    {
                        height = (int) (width / aspectRatio);
                    }
                    else
                    {
                        width = (int) (height * aspectRatio);
                    }
                    // Draw the scaled tempImage
                    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics2D = newImage.createGraphics();
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    graphics2D.drawImage(img, 0, 0, width, height, null);
                    ii = new ImageIcon(newImage);
                    tempImage = new File(System.getProperty("java.io.tmpdir") + "/image.jpg");
                    ImageIO.write(newImage, "JPG", tempImage);
                    //selectedFile = tempImage;
                }
                catch (IOException ex)
                {
                    String message = "An error occurred while trying to save the image.";
                    logger.log(Level.SEVERE, message, ex);
                }
            }
            lblImage.setIcon(ii);
        }
        catch (Exception e)
        {
            String message = "An error occurred while tring to load the customer's image.";
            logger.log(Level.SEVERE, message, e);
        }
        customerTabbedPane.setSelectedIndex(customerTabbedPane.getSelectedIndex() + 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customerTabbedPane = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        jYTableScrollPane1 = new de.javasoft.swing.JYTableScrollPane();
        tblCustomers = new de.javasoft.swing.JYTable();
        cmdCancel5 = new javax.swing.JButton();
        cmdNext5 = new javax.swing.JButton();
        generalPanel = new javax.swing.JPanel();
        lblFirstName = new javax.swing.JLabel();
        lblLastName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        lblSalutation = new javax.swing.JLabel();
        lblOtherName = new javax.swing.JLabel();
        txtOtherName = new javax.swing.JTextField();
        lblDOB = new javax.swing.JLabel();
        cmdCancel = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        lblGender = new javax.swing.JLabel();
        lblIDType = new javax.swing.JLabel();
        lblIDNum = new javax.swing.JLabel();
        txtIDNum = new javax.swing.JTextField();
        cmdResetForm = new javax.swing.JButton();
        lblCustID = new javax.swing.JLabel();
        txtCustID = new javax.swing.JTextField();
        lblRegistered = new javax.swing.JLabel();
        txtRegistered = new javax.swing.JTextField();
        lblModified = new javax.swing.JLabel();
        txtModified = new javax.swing.JTextField();
        lblRating = new javax.swing.JLabel();
        txtSalutation = new javax.swing.JTextField();
        txtDOB = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        txtIDType = new javax.swing.JTextField();
        txtRating = new javax.swing.JTextField();
        telephonePanel = new javax.swing.JPanel();
        lblHomePhone = new javax.swing.JLabel();
        txtHomePhone = new javax.swing.JTextField();
        lblCellPhone = new javax.swing.JLabel();
        txtCellPhone = new javax.swing.JTextField();
        lblHomeEmail = new javax.swing.JLabel();
        txtHomeEmail = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHomeAddress = new javax.swing.JTextArea();
        cmdNext1 = new javax.swing.JButton();
        cmdCancel1 = new javax.swing.JButton();
        addressPanel = new javax.swing.JPanel();
        lblOccupation = new javax.swing.JLabel();
        lblWorkplace = new javax.swing.JLabel();
        txtWorkplace = new javax.swing.JTextField();
        lblTelephone = new javax.swing.JLabel();
        txtWorkPhone = new javax.swing.JTextField();
        lblWorkEmail = new javax.swing.JLabel();
        txtWorkEmail = new javax.swing.JTextField();
        lblWorkAddress = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtWorkAddress = new javax.swing.JTextArea();
        cmdCancel2 = new javax.swing.JButton();
        cmdNext2 = new javax.swing.JButton();
        txtOccupation = new javax.swing.JTextField();
        notesPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        cmdNext3 = new javax.swing.JButton();
        cmdCancel3 = new javax.swing.JButton();
        lblNotes = new javax.swing.JLabel();
        imagePanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lblImage = new javax.swing.JLabel();
        cmdCancel4 = new javax.swing.JButton();
        lblPicture = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("View Customer");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/user_go.png"))); // NOI18N

        customerTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        customerTabbedPane.setToolTipText("");

        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "DOB", "Rating"
            }
        ));
        tblCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomersMouseClicked(evt);
            }
        });
        jYTableScrollPane1.setViewportView(tblCustomers);
        tblCustomers.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        cmdCancel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/cross.png"))); // NOI18N
        cmdCancel5.setText("Cancel");
        cmdCancel5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancel5ActionPerformed(evt);
            }
        });

        cmdNext5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/resultset_next.png"))); // NOI18N
        cmdNext5.setText("Next");
        cmdNext5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNext5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jYTableScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdNext5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel5)))
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jYTableScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel5)
                    .addComponent(cmdNext5))
                .addContainerGap())
        );

        customerTabbedPane.addTab(" Search", new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/find.png")), searchPanel); // NOI18N

        lblFirstName.setText("First Name:");

        lblLastName.setText("Last Name:");

        txtFirstName.setEditable(false);
        txtFirstName.setToolTipText("<html>The customer's first name.<br>\ne.g. \"John\"\n\n</html>");

        txtLastName.setEditable(false);
        txtLastName.setToolTipText("<html>The customer's last name.<br>\ne.g. \"Smith\"\n\n</html>");

        lblSalutation.setText("Salutation:");

        lblOtherName.setText("Other Name:");

        txtOtherName.setEditable(false);
        txtOtherName.setToolTipText("<html>Any other names the customer may have.<br>\ne.g. <ul>\n<li>Middle Names</li>\n<li>Aliases</li>\n\n</ul>\n\n</html>");

        lblDOB.setText("D.O.B.:");

        cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/cross.png"))); // NOI18N
        cmdCancel.setMnemonic('C');
        cmdCancel.setText("Cancel");
        cmdCancel.setToolTipText("Closes this window without saving any changes.");
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel(evt);
            }
        });

        cmdNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/resultset_next.png"))); // NOI18N
        cmdNext.setMnemonic('N');
        cmdNext.setText("Next");
        cmdNext.setToolTipText("Moves to the next tab where you can continue to enter information.");
        cmdNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next(evt);
            }
        });

        lblGender.setText("Gender:");

        lblIDType.setText("ID Type:");

        lblIDNum.setText("ID Number:");

        txtIDNum.setEditable(false);
        txtIDNum.setToolTipText("The ID number on the card stated above.");

        cmdResetForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/arrow_refresh.png"))); // NOI18N
        cmdResetForm.setText("Reset Form");
        cmdResetForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdResetFormActionPerformed(evt);
            }
        });

        lblCustID.setText("Cust. ID:");

        txtCustID.setEditable(false);

        lblRegistered.setText("Registered:");

        txtRegistered.setEditable(false);

        lblModified.setText("Modified:");

        txtModified.setEditable(false);

        lblRating.setText("Rating:");

        txtSalutation.setEditable(false);

        txtDOB.setEditable(false);

        txtGender.setEditable(false);

        txtIDType.setEditable(false);

        txtRating.setEditable(false);

        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addComponent(cmdResetForm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                        .addComponent(cmdNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel))
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName)
                            .addComponent(lblLastName)
                            .addComponent(lblSalutation)
                            .addComponent(lblOtherName)
                            .addComponent(lblDOB)
                            .addComponent(lblGender)
                            .addComponent(lblIDType)
                            .addComponent(lblIDNum)
                            .addComponent(lblCustID)
                            .addComponent(lblRegistered)
                            .addComponent(lblModified)
                            .addComponent(lblRating))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModified)
                            .addComponent(txtRegistered)
                            .addComponent(txtCustID)
                            .addComponent(txtIDNum)
                            .addComponent(txtOtherName)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSalutation, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDOB, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGender, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtIDType, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRating, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustID)
                    .addComponent(txtCustID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalutation)
                    .addComponent(txtSalutation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastName)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOtherName)
                    .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDOB)
                    .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDType)
                    .addComponent(txtIDType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDNum)
                    .addComponent(txtIDNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRating)
                    .addComponent(txtRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegistered)
                    .addComponent(txtRegistered, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModified)
                    .addComponent(txtModified, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel)
                    .addComponent(cmdNext)
                    .addComponent(cmdResetForm))
                .addContainerGap())
        );

        customerTabbedPane.addTab("General", new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/user.png")), generalPanel); // NOI18N

        lblHomePhone.setText("Landline:");

        txtHomePhone.setEditable(false);
        txtHomePhone.setToolTipText("The customer's house phone.\n");

        lblCellPhone.setText("Mobile:");

        txtCellPhone.setEditable(false);
        txtCellPhone.setToolTipText("The customer's cell phone.");

        lblHomeEmail.setText("Email:");

        txtHomeEmail.setEditable(false);
        txtHomeEmail.setToolTipText("The customer's personal email address.");

        lblAddress.setText("Address:");

        txtHomeAddress.setColumns(20);
        txtHomeAddress.setEditable(false);
        txtHomeAddress.setRows(5);
        txtHomeAddress.setToolTipText("The customer's home address.");
        jScrollPane1.setViewportView(txtHomeAddress);

        cmdNext1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/resultset_next.png"))); // NOI18N
        cmdNext1.setMnemonic('N');
        cmdNext1.setText("Next");
        cmdNext1.setToolTipText("Moves to the next tab where you can continue to enter information.");
        cmdNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNext1next(evt);
            }
        });

        cmdCancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/cross.png"))); // NOI18N
        cmdCancel1.setMnemonic('C');
        cmdCancel1.setText("Cancel");
        cmdCancel1.setToolTipText("Closes this window without saving any changes.");
        cmdCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancel1cancel(evt);
            }
        });

        javax.swing.GroupLayout telephonePanelLayout = new javax.swing.GroupLayout(telephonePanel);
        telephonePanel.setLayout(telephonePanelLayout);
        telephonePanelLayout.setHorizontalGroup(
            telephonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telephonePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(telephonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(telephonePanelLayout.createSequentialGroup()
                        .addGroup(telephonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHomePhone)
                            .addComponent(lblCellPhone)
                            .addComponent(lblHomeEmail)
                            .addComponent(lblAddress))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(telephonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                            .addComponent(txtHomeEmail)
                            .addComponent(txtCellPhone)
                            .addComponent(txtHomePhone)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, telephonePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdNext1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel1)))
                .addContainerGap())
        );
        telephonePanelLayout.setVerticalGroup(
            telephonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telephonePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(telephonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHomePhone)
                    .addComponent(txtHomePhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(telephonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCellPhone)
                    .addComponent(txtCellPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(telephonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHomeEmail)
                    .addComponent(txtHomeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(telephonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddress)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                .addGroup(telephonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel1)
                    .addComponent(cmdNext1))
                .addContainerGap())
        );

        customerTabbedPane.addTab("Contact Info", new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/house.png")), telephonePanel); // NOI18N

        lblOccupation.setText("Occupation:");

        lblWorkplace.setText("Workplace:");

        txtWorkplace.setEditable(false);
        txtWorkplace.setToolTipText("The customer's workplace.");

        lblTelephone.setText("Telephone:");

        txtWorkPhone.setEditable(false);
        txtWorkPhone.setToolTipText("The customer's work telephone number.");

        lblWorkEmail.setText("Email:");

        txtWorkEmail.setEditable(false);
        txtWorkEmail.setToolTipText("The customer's work email address.");

        lblWorkAddress.setText("Address:");

        txtWorkAddress.setColumns(20);
        txtWorkAddress.setEditable(false);
        txtWorkAddress.setRows(5);
        txtWorkAddress.setToolTipText("The address of the customer's workplace.");
        jScrollPane2.setViewportView(txtWorkAddress);

        cmdCancel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/cross.png"))); // NOI18N
        cmdCancel2.setMnemonic('C');
        cmdCancel2.setText("Cancel");
        cmdCancel2.setToolTipText("Closes this window without saving any changes.");
        cmdCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancel2cancel(evt);
            }
        });

        cmdNext2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/resultset_next.png"))); // NOI18N
        cmdNext2.setMnemonic('N');
        cmdNext2.setText("Next");
        cmdNext2.setToolTipText("Moves to the next tab where you can continue to enter information.");
        cmdNext2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNext2next(evt);
            }
        });

        txtOccupation.setEditable(false);

        javax.swing.GroupLayout addressPanelLayout = new javax.swing.GroupLayout(addressPanel);
        addressPanel.setLayout(addressPanelLayout);
        addressPanelLayout.setHorizontalGroup(
            addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addressPanelLayout.createSequentialGroup()
                        .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOccupation)
                            .addComponent(lblWorkplace)
                            .addComponent(lblTelephone)
                            .addComponent(lblWorkEmail)
                            .addComponent(lblWorkAddress))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                            .addComponent(txtWorkEmail)
                            .addComponent(txtWorkPhone)
                            .addComponent(txtWorkplace)
                            .addComponent(txtOccupation, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdNext2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel2)))
                .addContainerGap())
        );
        addressPanelLayout.setVerticalGroup(
            addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOccupation)
                    .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWorkplace)
                    .addComponent(txtWorkplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelephone)
                    .addComponent(txtWorkPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWorkEmail)
                    .addComponent(txtWorkEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWorkAddress)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel2)
                    .addComponent(cmdNext2))
                .addContainerGap())
        );

        customerTabbedPane.addTab("Occupation", new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/building.png")), addressPanel); // NOI18N

        txtNotes.setColumns(20);
        txtNotes.setEditable(false);
        txtNotes.setRows(5);
        txtNotes.setToolTipText("Any notes you may want to keep on the customer.");
        jScrollPane4.setViewportView(txtNotes);

        cmdNext3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/resultset_next.png"))); // NOI18N
        cmdNext3.setMnemonic('N');
        cmdNext3.setText("Next");
        cmdNext3.setToolTipText("Moves to the next tab where you can continue to enter information.");
        cmdNext3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNext3next(evt);
            }
        });

        cmdCancel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/cross.png"))); // NOI18N
        cmdCancel3.setMnemonic('C');
        cmdCancel3.setText("Cancel");
        cmdCancel3.setToolTipText("Closes this window without saving any changes.");
        cmdCancel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancel3cancel(evt);
            }
        });

        lblNotes.setText("Notes");

        javax.swing.GroupLayout notesPanelLayout = new javax.swing.GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, notesPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdNext3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel3))
                    .addGroup(notesPanelLayout.createSequentialGroup()
                        .addComponent(lblNotes)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        notesPanelLayout.setVerticalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNotes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel3)
                    .addComponent(cmdNext3))
                .addContainerGap())
        );

        customerTabbedPane.addTab("Notes", new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/note.png")), notesPanel); // NOI18N

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/no-image-selected.png"))); // NOI18N
        jScrollPane3.setViewportView(lblImage);

        cmdCancel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/cross.png"))); // NOI18N
        cmdCancel4.setMnemonic('C');
        cmdCancel4.setText("Cancel");
        cmdCancel4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancel4cancel(evt);
            }
        });

        lblPicture.setText("Picture:");

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdCancel4))
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addComponent(lblPicture)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPicture)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdCancel4)
                .addContainerGap())
        );

        customerTabbedPane.addTab("Image", new javax.swing.ImageIcon(getClass().getResource("/iloan/resources/picture.png")), imagePanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancel(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancel
    {
//GEN-HEADEREND:event_cancel
        Utilities.showCancelScreen(this);
    }//GEN-LAST:event_cancel

    private void next(java.awt.event.ActionEvent evt)//GEN-FIRST:event_next
    {
//GEN-HEADEREND:event_next
        customerTabbedPane.setSelectedIndex(customerTabbedPane.getSelectedIndex() + 1);
    }//GEN-LAST:event_next

    private void cmdNext1next(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdNext1next
    {
//GEN-HEADEREND:event_cmdNext1next
        customerTabbedPane.setSelectedIndex(customerTabbedPane.getSelectedIndex() + 1);
    }//GEN-LAST:event_cmdNext1next

    private void cmdCancel1cancel(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdCancel1cancel
    {
//GEN-HEADEREND:event_cmdCancel1cancel
        Utilities.showCancelScreen(this);
    }//GEN-LAST:event_cmdCancel1cancel

    private void cmdCancel2cancel(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdCancel2cancel
    {
//GEN-HEADEREND:event_cmdCancel2cancel
        Utilities.showCancelScreen(this);
    }//GEN-LAST:event_cmdCancel2cancel

    private void cmdNext2next(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdNext2next
    {
//GEN-HEADEREND:event_cmdNext2next
        customerTabbedPane.setSelectedIndex(customerTabbedPane.getSelectedIndex() + 1);
    }//GEN-LAST:event_cmdNext2next

    private void cmdNext3next(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdNext3next
    {
//GEN-HEADEREND:event_cmdNext3next
        customerTabbedPane.setSelectedIndex(customerTabbedPane.getSelectedIndex() + 1);
    }//GEN-LAST:event_cmdNext3next

    private void cmdCancel3cancel(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdCancel3cancel
    {
//GEN-HEADEREND:event_cmdCancel3cancel
        Utilities.showCancelScreen(this);
    }//GEN-LAST:event_cmdCancel3cancel

    private void cmdCancel4cancel(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdCancel4cancel
    {
//GEN-HEADEREND:event_cmdCancel4cancel
        Utilities.showCancelScreen(this);
    }//GEN-LAST:event_cmdCancel4cancel

    private void cmdResetFormActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdResetFormActionPerformed
    {
//GEN-HEADEREND:event_cmdResetFormActionPerformed
        remove(customerTabbedPane);
        initComponents();
        populateLists();
    }//GEN-LAST:event_cmdResetFormActionPerformed

    private void cmdCancel5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdCancel5ActionPerformed
    {
//GEN-HEADEREND:event_cmdCancel5ActionPerformed
        Utilities.showCancelScreen(this);
    }//GEN-LAST:event_cmdCancel5ActionPerformed

    private void tblCustomersMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblCustomersMouseClicked
    {
//GEN-HEADEREND:event_tblCustomersMouseClicked
        if (evt.getClickCount() >= 2 && tblCustomers.getSelectedRow() != -1)
        {
            loadCustomerInfo();
        }
    }//GEN-LAST:event_tblCustomersMouseClicked

    private void cmdNext5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdNext5ActionPerformed
    {
//GEN-HEADEREND:event_cmdNext5ActionPerformed
        if (tblCustomers.getSelectedRow() == -1)
        {
            String message = "Kindly select a customer before proceeding.";
            Utilities.showWarningMessage(rootPane, message);
        }
        else
        {
            loadCustomerInfo();
        }
    }//GEN-LAST:event_cmdNext5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addressPanel;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdCancel1;
    private javax.swing.JButton cmdCancel2;
    private javax.swing.JButton cmdCancel3;
    private javax.swing.JButton cmdCancel4;
    private javax.swing.JButton cmdCancel5;
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdNext1;
    private javax.swing.JButton cmdNext2;
    private javax.swing.JButton cmdNext3;
    private javax.swing.JButton cmdNext5;
    private javax.swing.JButton cmdResetForm;
    private javax.swing.JTabbedPane customerTabbedPane;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private de.javasoft.swing.JYTableScrollPane jYTableScrollPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCellPhone;
    private javax.swing.JLabel lblCustID;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHomeEmail;
    private javax.swing.JLabel lblHomePhone;
    private javax.swing.JLabel lblIDNum;
    private javax.swing.JLabel lblIDType;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblModified;
    private javax.swing.JLabel lblNotes;
    private javax.swing.JLabel lblOccupation;
    private javax.swing.JLabel lblOtherName;
    private javax.swing.JLabel lblPicture;
    private javax.swing.JLabel lblRating;
    private javax.swing.JLabel lblRegistered;
    private javax.swing.JLabel lblSalutation;
    private javax.swing.JLabel lblTelephone;
    private javax.swing.JLabel lblWorkAddress;
    private javax.swing.JLabel lblWorkEmail;
    private javax.swing.JLabel lblWorkplace;
    private javax.swing.JPanel notesPanel;
    private javax.swing.JPanel searchPanel;
    private de.javasoft.swing.JYTable tblCustomers;
    private javax.swing.JPanel telephonePanel;
    private javax.swing.JTextField txtCellPhone;
    private javax.swing.JTextField txtCustID;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextArea txtHomeAddress;
    private javax.swing.JTextField txtHomeEmail;
    private javax.swing.JTextField txtHomePhone;
    private javax.swing.JTextField txtIDNum;
    private javax.swing.JTextField txtIDType;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtModified;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtOccupation;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtRating;
    private javax.swing.JTextField txtRegistered;
    private javax.swing.JTextField txtSalutation;
    private javax.swing.JTextArea txtWorkAddress;
    private javax.swing.JTextField txtWorkEmail;
    private javax.swing.JTextField txtWorkPhone;
    private javax.swing.JTextField txtWorkplace;
    // End of variables declaration//GEN-END:variables
}
