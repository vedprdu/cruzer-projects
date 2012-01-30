/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iloan.security;

/**
 *
 * @author mrogers
 */
public class EncryptionTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String plain = "2011-02-08";
        String encrypted = EncryptionHandler.encrypt(plain);
        System.out.println(plain);
        System.out.println(encrypted);
        //System.out.println(EncryptionHandler.decrypt(encrypted));
        //System.out.println(EncryptionHandler.encryptPassword(plain));
        System.exit(0);
    }
}
