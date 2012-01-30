/************************************************************************/
/* iLoan - Quick Loan Management System                                 */
/* ===========================                                          */
/*                                                                      */
/* Copyright (c) 2011 by Maurice Rogers                                 */
/* http://www.mauricerogers.com                                         */
/************************************************************************/
package iloan.pawn;

import iloan.kernel.Utilities;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class Pawn
{

    private static ArrayList<String> itemTypes = new ArrayList<String>();
    private static ArrayList<Double> itemValues = new ArrayList<Double>();
    private static ArrayList<String> itemDescriptions = new ArrayList<String>();
    private static ArrayList<Object> itemAttachments = new ArrayList<Object>();

    /**
     * This method clears the array lists allowing you to start adding a new pawn item.
     */
    public static void newPawn()
    {
        itemTypes = new ArrayList<String>();
        itemValues = new ArrayList<Double>();
        itemDescriptions = new ArrayList<String>();
        itemAttachments = new ArrayList<Object>();
    }

    /**
     * This method adds a pawn item to the end of the list.
     * This is called when you're simply adding a new item.
     *
     * @param type
     * @param value
     * @param description
     * @param attachments
     */
    public static void addPawnItem(String type, Double value, String description, ArrayList<File> attachments)
    {
        getItemTypes().add(type);
        getItemValues().add(value);
        getItemDescriptions().add(description);
        getItemAttachments().add(attachments);
    }

    /**
     * This method adds a pawn item to a specific location the list.
     * This method is called when editing a pawn item.
     *
     * @param location
     * @param type
     * @param value
     * @param description
     * @param attachments
     */
    public static void addPawnItem(int location, String type, Double value, String description, ArrayList<File> attachments)
    {
        getItemTypes().add(location, type);
        getItemValues().add(location, value);
        getItemDescriptions().add(location, description);
        getItemAttachments().add(location, attachments);
    }

    /**
     * This method loads the pawn items into the specified table.
     * @param table
     */
    public static void loadPawnItems(JTable table)
    {
        ArrayList<String> formattedValues = new ArrayList<String>();

        for (double value : getItemValues())
        {
            formattedValues.add(Utilities.formatAsMoney(value));
        }

        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class getColumnClass(int mColIndex)
            {
                int rowIndex = 0;
                Object o = getValueAt(rowIndex, mColIndex);
                if (o == null)
                {
                    return Object.class;
                }
                else
                {
                    return o.getClass();
                }
            }
        };

        model.addColumn("Type", getItemTypes().toArray());
        model.addColumn("Description", getItemDescriptions().toArray());
        model.addColumn("Value", formattedValues.toArray());

        table.setModel(model);
    }

    /**
     * This method removes the specified item from the list.
     * @param itemIndex
     */
    public static void removeItem(int itemIndex)
    {
        itemTypes.remove(itemIndex);
        itemAttachments.remove(itemIndex);
        itemDescriptions.remove(itemIndex);
        itemValues.remove(itemIndex);
    }

    /**
     * This method gets the total value of all the
     * pawn items in the current list.
     *
     * @returns a double containing the sum of all item values.
     */
    public static double getValueTotal()
    {
        double total = 0;
        for (double value : getItemValues())
        {
            total = total + value;
        }
        return total;
    }

    /**
     * This method returns the total value of all the
     * pawn items in the current list formatted with a
     * dollar sign and rounded to two decimal places.
     * @return a string representing the sum of all item values.
     */
    public static String getFormattedValueTotal()
    {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        String total = df.format(getValueTotal());
        return total;
    }

    /**
     * @return the itemTypes
     */
    public static ArrayList<String> getItemTypes()
    {
        return itemTypes;
    }

    /**
     * @return the itemValues
     */
    public static ArrayList<Double> getItemValues()
    {
        return itemValues;
    }

    /**
     * @return the itemDescriptions
     */
    public static ArrayList<String> getItemDescriptions()
    {
        return itemDescriptions;
    }

    /**
     * @return the itemAttachments
     */
    public static ArrayList<Object> getItemAttachments()
    {
        return itemAttachments;
    }
}
