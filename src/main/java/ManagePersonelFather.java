
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ximax
 */
public class ManagePersonelFather {
    private JPanel panel= new JPanel();
    private JLabel label;
    private JFrame f = new JFrame("Edit Project options");
    
    public ManagePersonelFather(){
    f.add(panel, new GridBagConstraints());
        f.setSize(300,300);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
