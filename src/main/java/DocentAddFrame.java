import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ximax
 */
public class DocentAddFrame {
    private JLabel DocentNameLabel, areaLabel, emailLabel, numMecLabel;
    private JTextField DocentNameInput, emailInput, areaInput, numMecInput;
    private JButton addButton;
    private JPanel panel= new JPanel();
    private JFrame f = new JFrame("Add Docent");
    private DefaultListModel listModel;
    private ArrayList<Docent> gDocentList;

    public DocentAddFrame(ArrayList<Docent> DocentList, DefaultListModel list) {
        gDocentList=DocentList;
        listModel = list;
        f.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        DocentNameLabel=new JLabel ("Docent Name:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,0);
        c.gridx = 1;      
        c.gridy = 1;
        panel.add(DocentNameLabel,c);

        DocentNameInput=new JTextField(10);
        c.ipady = 5; 
        c.gridx = 2;
        c.gridy = 1;
        panel.add(DocentNameInput,c);

        emailLabel=new JLabel("Email:");
        c.gridx = 1;      
        c.gridy = 2;
        panel.add(emailLabel,c);

        emailInput=new JTextField(10);
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(emailInput,c);


        areaLabel=new JLabel("Specialization Area:");
        c.gridx = 1;      
        c.gridy = 3;
        panel.add(areaLabel,c);

        areaInput=new JTextField(10);
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 3;
        panel.add(areaInput,c);


        numMecLabel=new JLabel("Mechanografic Number:");
        c.gridx = 1;      
        c.gridy = 4;
        panel.add(numMecLabel,c);

        numMecInput=new JTextField(10);
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 4;
        panel.add(numMecInput,c);

        addButton = new JButton("Add");
        c.ipady = 0;       //reset to default
        c.gridy = 5;
        panel.add(addButton,c);


        AddBListener listener=new AddBListener();
        addButton.addActionListener(listener);


        f.add(panel, new GridBagConstraints());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(300,300);
        f.setVisible(true);
    }

    private class AddBListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String name = DocentNameInput.getText();
                String email = emailInput.getText();
                String numMec = numMecInput.getText(); 
                String area = areaInput.getText();
                int FormatedNumMec = Integer.parseInt(numMec);
                if (name.trim().isEmpty() || email.trim().isEmpty() || numMec.trim().isEmpty() || area.trim().isEmpty()){
                    throw new IllegalArgumentException();
                }
                Docent newDocent = new Docent(name, email, FormatedNumMec, area);
                gDocentList.add(newDocent);
                listModel.addElement(newDocent.getName());
                panel.updateUI();
                f.dispose();
            }catch ( IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null,"Input Error, Please use the following format:\n\nName\nEmail\nSpecialization Area\nMechanografic number","Error on Parse",JOptionPane.INFORMATION_MESSAGE);;
            } 
        } 
    }
}
