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
public class GranteeAddFrame {
    private JLabel GranteeNameLabel, initialDateLabel, emailLabel, finalDateLabel;
    private JTextField GranteeNameInput, emailInput, initialDateInput, finalDateInput;
    private JButton addButton;
    private JPanel panel= new JPanel();
    private JFrame f = new JFrame("Add Grantee");
    private DefaultListModel listModel;
    private ArrayList<Grantee> gGranteeList;
    String nivel;

    public GranteeAddFrame(ArrayList<Grantee> GranteeList, DefaultListModel list) {
        String[] Levels = { "Graduate", "Master", "Doctorate" };
        JComboBox ChooseScholarLvl = new JComboBox(Levels);
        GradeLvlListener CBlistener=new GradeLvlListener();
        ChooseScholarLvl.addActionListener(CBlistener);
        
        gGranteeList=GranteeList;
        listModel = list;
        f.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 2;      
        c.insets = new Insets(0,10,60,0);
        panel.add(ChooseScholarLvl,c);
        
        GranteeNameLabel=new JLabel ("Grantee Name:");
        c.insets = new Insets(0,10,0,0);
        c.gridy = 1;
        panel.add(GranteeNameLabel,c);

        GranteeNameInput=new JTextField(10);
        c.ipady = 5; 
        c.gridx = 2;
        c.gridy = 1;
        panel.add(GranteeNameInput,c);

        emailLabel=new JLabel("Email:");
        c.gridx = 1;      
        c.gridy = 2;
        panel.add(emailLabel,c);

        emailInput=new JTextField(10);
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(emailInput,c);


        initialDateLabel=new JLabel("Initial Contract Date:");
        c.gridx = 1;      
        c.gridy = 3;
        panel.add(initialDateLabel,c);

        initialDateInput=new JTextField(10);
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 3;
        panel.add(initialDateInput,c);


        finalDateLabel=new JLabel("Contract Expiration Date:");
        c.gridx = 1;      
        c.gridy = 4;
        panel.add(finalDateLabel,c);

        finalDateInput=new JTextField(10);
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 4;
        panel.add(finalDateInput,c);

        addButton = new JButton("Add");
        c.ipady = 0;       //reset to default
        c.gridy = 5;
        panel.add(addButton,c);


        AddBListener Blisten=new AddBListener();
        addButton.addActionListener(Blisten);


        f.add(panel, new GridBagConstraints());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(300,300);
        f.setVisible(true);
    }

    private class AddBListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String name = GranteeNameInput.getText();
                String email = emailInput.getText();
                String iDate = initialDateInput.getText(); 
                Date FormatedInitialDate=new SimpleDateFormat("dd/MM/yyyy").parse(iDate); 
                String fDate = finalDateInput.getText();
                Date FormatedFinalDate=new SimpleDateFormat("dd/MM/yyyy").parse(fDate); 
                if (name.trim().isEmpty() || email.trim().isEmpty() || iDate.trim().isEmpty() || fDate.trim().isEmpty()){
                    throw new IllegalArgumentException();
                }
                if(nivel.compareTo("Graduate")==0){
                    Graduate newGrantee = new Graduate(name, email, FormatedInitialDate, FormatedFinalDate);
                    gGranteeList.add(newGrantee);
                    listModel.addElement(newGrantee.getName());
                }else if(nivel.compareTo("Master")==0){
                    Master newMaster = new Master(name, email, FormatedInitialDate, FormatedFinalDate);                    
                    gGranteeList.add(newMaster);
                    listModel.addElement(newMaster.getName());
                }else if(nivel.compareTo("Doctorate")==0){
                    Doctorate newDoctorate = new Doctorate(name, email, FormatedInitialDate, FormatedFinalDate);
                    gGranteeList.add(newDoctorate);
                    listModel.addElement(newDoctorate.getName());
                }
                panel.updateUI();
                f.dispose();
            }catch (ParseException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null,"Input Error, Please use the following format:\n\nName\nEmail\nSpecialization Area\nMechanografic number","Error on Parse",JOptionPane.INFORMATION_MESSAGE);;
            } 
        } 
    }
    
    private class GradeLvlListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
           JComboBox cb = (JComboBox)e.getSource();
            nivel = (String)cb.getSelectedItem();
             
        }  
    }
}
