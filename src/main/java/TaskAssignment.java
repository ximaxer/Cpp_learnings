import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class TaskAssignment {
    int taskIndex;
    private Project projeto;
    private JLabel label;
    private JPanel panel= new JPanel();
    private JFrame f = new JFrame("Manage Project"), f3 = new JFrame("list completed");
    ArrayList<Task> gTaskList,copyTaskList=new ArrayList<Task>() ;
    private DefaultListModel listaDocents, listaGrantees;
    private JList DocentsJList, GranteesJList;
    private JButton assignGranteeButton, assignDocentButton;
    
    TaskAssignment(Project project, int index){
        projeto=project;
        taskIndex= index;
        listaDocents = new DefaultListModel();
        listaGrantees = new DefaultListModel();
        for(int x=0;x<project.docents.size();x++)listaDocents.addElement(project.docents.get(x).getName());
        DocentsJList = new JList(listaDocents);
        DocentsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane DocentsScrollPane= new JScrollPane(DocentsJList);
        Dimension d2 = DocentsJList.getPreferredSize();
        d2.width = 175;
        d2.height = 225;
        DocentsScrollPane.setPreferredSize(d2);
        
        
        for(int x=0;x<project.grantees.size();x++)listaGrantees.addElement(project.grantees.get(x).getName());
        GranteesJList = new JList(listaGrantees);
        GranteesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane GranteesScrollPane= new JScrollPane(GranteesJList);
        Dimension d3 = GranteesJList.getPreferredSize();
        d3.width = 175;
        d3.height = 225;
        GranteesScrollPane.setPreferredSize(d3);
        
        
        f.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;      
        c.insets = new Insets(0,10,10,0);
        panel.add(DocentsScrollPane,c);
        
        c.gridx = 1;
        panel.add(GranteesScrollPane,c);

        assignDocentButton = new JButton("Assign Docent");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(assignDocentButton,c);
        
        assignGranteeButton = new JButton("Assign Grantee");
        c.gridx = 1;
        panel.add(assignGranteeButton,c);

        GranteeListListener assignGranteeBListener=new GranteeListListener();
        assignGranteeButton.addActionListener(assignGranteeBListener);
        
        DocentListListener assignDocentBListener=new DocentListListener();
        assignDocentButton.addActionListener(assignDocentBListener);
        
        f.add(panel, new GridBagConstraints());
        f.setSize(750,750);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    
    private class GranteeListListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                int granteeIndex = GranteesJList.getSelectedIndex();
                if(projeto.grantees.get(granteeIndex).verifyEffort()+projeto.tasks.get(taskIndex).getEffort()<=1){
                    projeto.grantees.get(granteeIndex).tasks.add(projeto.tasks.get(taskIndex));
                    projeto.tasks.get(taskIndex).responsible=projeto.grantees.get(granteeIndex);
                    f.dispose();
                }
                else{
                    throw new IllegalArgumentException();
                }
            
            }
            catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,"Error, this grantee can't receive that task, too much work!","Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    
    private class DocentListListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                int docentIndex = DocentsJList.getSelectedIndex();
                if(projeto.docents.get(docentIndex).verifyEffort()+projeto.tasks.get(taskIndex).getEffort()<=1){
                    projeto.docents.get(docentIndex).tasks.add(projeto.tasks.get(taskIndex));
                    projeto.tasks.get(taskIndex).responsible=projeto.docents.get(docentIndex);
                    f.dispose();
                }
            else{
                    throw new IllegalArgumentException();
                }
            }
            catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,"Error, this docent can't receive that task, too much work!","Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
