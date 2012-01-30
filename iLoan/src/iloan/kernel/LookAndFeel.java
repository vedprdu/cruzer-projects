/************************************************************************/
/* iLoan - Quick Loan Management System                                 */
/* ===========================                                          */
/*                                                                      */
/* Copyright (c) 2011 by Maurice Rogers                                 */
/* http://www.mauricerogers.com                                         */
/************************************************************************/

package iloan.kernel;

import javax.swing.UIManager;

/**
 *
 * @author mrogers
 */
public class LookAndFeel
{

    public LookAndFeel()
    {
        //Synthetica LAF
        String[] li =
        {
            "Licensee=Maurice Rogers", "LicenseRegistrationNumber=------", "Product=Synthetica", "LicenseType=Non Commercial", "ExpireDate=--.--.----", "MaxVersion=2.999.999"
        };
        UIManager.put("Synthetica.license.info", li);
        UIManager.put("Synthetica.license.key", "2BCF99E0-3738913D-F30B5EC9-622511CC-4F19572A");
    }
}
