package icontact.kernel;

import java.awt.Component;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author mrogers
 */
public class Utilities
{

    public static DateFormat YMD_Formatter = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat MDY_Formatter = new SimpleDateFormat("MMM d, yyyy");

    /**
     *
     * @param content
     * @return Returns the parameter surrounded by single quotes.
     */
    public static String quotate(String content)
    {
        return "'" + content + "'";
    }

    /**
     *
     * @param content
     * @return Returns the parameter surrounded by percent signs.
     */
    public static String percent(String content)
    {
        return "%" + content + "%";
    }

    public static String formatAsMoney(double value)
    {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        String total = df.format(value);
        return total;
    }

    public static String roundDouble(double value)
    {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String total = df.format(value);
        return total;
    }

    public static int showConfirmDialog(String message) 
    {
        try
        {
            return Messagebox.show(message, "iContact", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static int showYNCConfirmDialog(String message) 
    {
        try
        {
            return Messagebox.show(message, "iContact", Messagebox.YES | Messagebox.NO | Messagebox.CANCEL, Messagebox.QUESTION);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static void showInfoMessage(String message) 
    {
        try
        {
            Messagebox.show(message, "iContact", Messagebox.OK, Messagebox.INFORMATION);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showErrorMessage(String message) 
    {
        try
        {
            Messagebox.show(message, "iContact", Messagebox.OK, Messagebox.ERROR);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showWarningMessage(String message) 
    {
        try
        {
            Messagebox.show(message, "iContact", Messagebox.OK, Messagebox.EXCLAMATION);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public static void showCancelScreen(JInternalFrame frame)
//    {
//        String message = "Are you sure you want to close this window?";
//        int response = JOptionPane.showConfirmDialog(frame, message, "iLearn", JOptionPane.YES_NO_OPTION);
//        if (response == JOptionPane.YES_OPTION)
//        {
//            frame.dispose();
//        }
//    }
}
