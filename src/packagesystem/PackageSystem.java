/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagesystem;

import GUI.GUI;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matexo
 */
public class PackageSystem {

    public static void main(String[] args) throws FileNotFoundException  {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                try {
                    new GUI().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PackageSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
