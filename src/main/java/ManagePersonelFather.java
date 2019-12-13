
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ximax
 */
public class ManagePersonelFather{
    private JPanel panel= new JPanel();
    private JFrame f = new JFrame("Edit Project options");
    private JButton addGranteeButton, addDocentButton, removeGranteeButton, removeDocentButton;
    private JComboBox ChooseDocent, ChooseGrantee;
    private DefaultListModel listaGrantees, listaDocents;
    private JList GranteeListF, DocentListF;
    private ArrayList<Grantee> AvailableGranteeList = new ArrayList<Grantee>(), AssignedGrantees = new ArrayList<Grantee>();
    private ArrayList<Docent> gDocentList, AvailableDocents = new ArrayList<Docent>(), AssignedDocents = new ArrayList<Docent>();
    ArrayList<String> StringListAvailableGrantee = new ArrayList<String>();
    ArrayList<String> StringListAvailableDocent = new ArrayList<String>();
    int SelectedGranteeIndex, SelectedDocentIndex, SelectedRemoveDocent, SelectedRemoveGrantee;
    Project project;
    
    public ManagePersonelFather(Project projectI, ArrayList<Grantee> GranteeList, ArrayList<Docent> DocentList){
        gDocentList = DocentList;
        project = projectI;
        
        listaGrantees = new DefaultListModel();
        for(int i=0;i<GranteeList.size();i++){
            if(GranteeList.get(i).project==null){
                StringListAvailableGrantee.add(GranteeList.get(i).getName());
                AvailableGranteeList.add(GranteeList.get(i));
            }else if(GranteeList.get(i).project==projectI){
                listaGrantees.addElement(GranteeList.get(i).getName());
                AssignedGrantees.add(GranteeList.get(i));
            }
        }
        
        String[] AvailableGrantees=StringListAvailableGrantee.toArray(new String[0]);
        ChooseGrantee = new JComboBox(AvailableGrantees);
        AvailableGranteesListener GranteeCBlistener=new AvailableGranteesListener();
        ChooseGrantee.addActionListener(GranteeCBlistener);
        
        
        listaDocents = new DefaultListModel();
        for(int i=0;i<DocentList.size();i++){
            if(DocentList.get(i).projects.contains(project)==false){
                StringListAvailableDocent.add(DocentList.get(i).getName());
                AvailableDocents.add(DocentList.get(i));
            }else{
                listaDocents.addElement(gDocentList.get(i).getName());
                AssignedDocents.add(DocentList.get(i));
            }
        }
        
        
        String[] sAvailableDocents=StringListAvailableDocent.toArray(new String[0]);
        ChooseDocent = new JComboBox(sAvailableDocents);
        AvailableDocentListener DocentCBlistener=new AvailableDocentListener();
        ChooseDocent.addActionListener(DocentCBlistener);
        

        
        
        GranteeListF = new JList(listaGrantees);
        GranteeListF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        GranteeListF.addListSelectionListener(new GranteeListListener());
        GranteeListF.setSelectedIndex(0);
        JScrollPane GranteeScrollPane = new JScrollPane(GranteeListF);
        Dimension d2 = GranteeListF.getPreferredSize();
        d2.width = 175;
        d2.height = 225;
        GranteeScrollPane.setPreferredSize(d2);
        
        DocentListF = new JList(listaDocents);
        DocentListF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DocentListF.addListSelectionListener(new DocentListListener());
        DocentListF.setSelectedIndex(0);
        JScrollPane DocentScrollPane = new JScrollPane(DocentListF);
        Dimension d3 = DocentListF.getPreferredSize();
        d3.width = 175;
        d3.height = 225;
        DocentScrollPane.setPreferredSize(d3);
        
        
        
        f.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;      
        c.insets = new Insets(0,10,10,0);
        panel.add(ChooseGrantee,c);
        
        c.gridx = 1;
        panel.add(ChooseDocent,c);
        
        addGranteeButton = new JButton("Add Grantee");
        c.gridx = 0;       //reset to default
        c.gridy = 1;
        panel.add(addGranteeButton,c);

        addDocentButton = new JButton("Add Docent");
        c.gridx = 1;
        panel.add(addDocentButton,c);
        
        removeGranteeButton = new JButton("Remove Grantee");
        c.gridx = 0;       //reset to default
        c.gridy = 3;
        panel.add(removeGranteeButton,c);

        removeDocentButton = new JButton("Remove Docent");
        c.gridx = 1;
        panel.add(removeDocentButton,c);

        c.gridx=0;
        c.gridy=2;
        panel.add(GranteeScrollPane,c);
        
        c.gridx=1;
        panel.add(DocentScrollPane,c);

        addGranteeBListener aGranteeBListener=new addGranteeBListener();
        addGranteeButton.addActionListener(aGranteeBListener);
        
        addDocentBListener aDocentBlistener=new addDocentBListener();
        addDocentButton.addActionListener(aDocentBlistener);

        removeGranteeBListener rGranteeBListener= new removeGranteeBListener();
        removeGranteeButton.addActionListener(rGranteeBListener);
        
        removeDocentBListener rDocentBListener= new removeDocentBListener();
        removeDocentButton.addActionListener(rDocentBListener);
        
        
        f.add(panel, new GridBagConstraints());
        f.setSize(500,500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private class AvailableGranteesListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
           JComboBox cbGrantee = (JComboBox)e.getSource();
            SelectedGranteeIndex = cbGrantee.getSelectedIndex();
             
        }  
    }
    
    

    private class AvailableDocentListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ex){
            JComboBox cbDocent = (JComboBox)ex.getSource();
            SelectedDocentIndex = cbDocent.getSelectedIndex();
        }
    }
    
    private class addGranteeBListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent exc){
            if ( SelectedGranteeIndex > -1) {
                AvailableGranteeList.get(SelectedGranteeIndex).project=project;
                project.grantees.add(AvailableGranteeList.get(SelectedGranteeIndex));
                AssignedGrantees.add(AvailableGranteeList.get(SelectedGranteeIndex));
                listaGrantees.addElement(AvailableGranteeList.get(SelectedGranteeIndex).getName());
                
                
                AvailableGranteeList.remove(SelectedGranteeIndex);
                StringListAvailableGrantee.remove(SelectedGranteeIndex);
                ChooseGrantee.removeItemAt(SelectedGranteeIndex);
                if (ChooseGrantee.getItemAt(0)==null)addGranteeButton.setEnabled(false);
                else ChooseGrantee.setSelectedIndex(0);
                panel.updateUI();
            }
        }
    }
    
    
    
    private class removeGranteeBListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent exce){
            if (SelectedRemoveGrantee > -1) {
                AssignedGrantees.get(SelectedRemoveGrantee).project=null;
                project.grantees.remove(AssignedGrantees.get(SelectedRemoveGrantee));
                AvailableGranteeList.add(AssignedGrantees.get(SelectedRemoveGrantee));
                
                
                
                StringListAvailableGrantee.add(AssignedGrantees.get(SelectedRemoveGrantee).getName());
                AssignedGrantees.remove(SelectedRemoveGrantee);
                listaGrantees.removeElementAt(SelectedRemoveGrantee);
                if(GranteeListF.getSize().equals(0)==true)removeGranteeButton.setEnabled(false);
                panel.updateUI();
            }
        }
    }
    
    
    private class removeDocentBListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent exce){
            if (SelectedRemoveDocent > -1) {
                AssignedDocents.get(SelectedRemoveDocent).projects.remove(project);
                project.docents.remove(AssignedDocents.get(SelectedRemoveDocent));
                AvailableDocents.add(AssignedDocents.get(SelectedRemoveDocent));
                StringListAvailableDocent.add(AssignedDocents.get(SelectedRemoveDocent).getName());
                AssignedDocents.remove(SelectedRemoveDocent);
                listaDocents.removeElementAt(SelectedRemoveDocent);
                if(DocentListF.getSize().equals(0)==true)removeDocentButton.setEnabled(false);
                panel.updateUI();
            }
        }
    }
    
    
    private class addDocentBListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent exce){
             if (SelectedDocentIndex > -1) {
                AvailableDocents.get(SelectedDocentIndex).projects.add(project);
                project.docents.add(AvailableDocents.get(SelectedDocentIndex));
                AssignedDocents.add(AvailableDocents.get(SelectedDocentIndex));
                listaDocents.addElement(AvailableDocents.get(SelectedDocentIndex).getName());
                
                AvailableDocents.remove(SelectedDocentIndex);
                StringListAvailableDocent.remove(SelectedDocentIndex);
                ChooseDocent.removeItemAt(SelectedDocentIndex);
                if (ChooseDocent.getItemAt(0)==null)addDocentButton.setEnabled(false);
                else ChooseDocent.setSelectedIndex(0);
                panel.updateUI();
            }
        }
    }
    
    class GranteeListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) { 
            SelectedRemoveGrantee = GranteeListF.getSelectedIndex();
        }
    }
    
    class DocentListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) { 
            SelectedRemoveDocent = DocentListF.getSelectedIndex();
        }
    }
}
