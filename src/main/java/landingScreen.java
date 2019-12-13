/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
/**
 *
 * @author ximax
 */
/* landingScreen.java requires no other files. */

public class landingScreen extends JPanel
    implements ListSelectionListener {
    private JList ProjectListF, DocentListF, GranteeListF;
    private DefaultListModel listaProjetos, listaDocents, listaGrantees;
    private JLabel ProjectsLabel, DocentsLabel, GranteesLabel,titleLabel;
    
    private JPanel panel= new JPanel();
    private JFrame f = new JFrame("Edit Project");
    private static final String addString = "Add";
    private static final String editString = "Edit";
    private JButton edit, addProject, addDocent, addGrantee;
    private ArrayList<Project> gProjectList; 
    private ArrayList<Docent> gDocents;
    private ArrayList<Grantee> gGrantees;
    
    public landingScreen(ArrayList<Project> ProjectList, ArrayList<Grantee> grantees, ArrayList<Docent> docents) {
        super();
        f.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        gDocents=docents;
        gGrantees=grantees;
        gProjectList=ProjectList;
        listaProjetos = new DefaultListModel();
        for(int i=0;i<ProjectList.size();i++)
        listaProjetos.addElement(gProjectList.get(i).getName());

        listaGrantees = new DefaultListModel();
        for(int i=0;i<grantees.size();i++){
            listaGrantees.addElement(gGrantees.get(i).getName() + "   " + gGrantees.get(i).getClass().getSimpleName());
        }
        listaDocents = new DefaultListModel();
        for(int i=0;i<docents.size();i++)
        listaDocents.addElement(gDocents.get(i).getName());

        
        
        ProjectListF = new JList(listaProjetos);
        ProjectListF.setSelectedIndex(0);
        ProjectListF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ProjectListF.addListSelectionListener(this);
        JScrollPane ProjectScrollPane = new JScrollPane(ProjectListF);
        Dimension d1 = ProjectListF.getPreferredSize();
        d1.width = 175;
        d1.height = 225;
        ProjectScrollPane.setPreferredSize(d1);
        
        GranteeListF = new JList(listaGrantees);
        GranteeListF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        GranteeListF.addListSelectionListener(this);
        JScrollPane GranteeScrollPane = new JScrollPane(GranteeListF);
        Dimension d2 = GranteeListF.getPreferredSize();
        d2.width = 175;
        d2.height = 225;
        GranteeScrollPane.setPreferredSize(d2);
        
        DocentListF = new JList(listaDocents);
        DocentListF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DocentListF.addListSelectionListener(this);
        JScrollPane DocentScrollPane = new JScrollPane(DocentListF);
        Dimension d3 = DocentListF.getPreferredSize();
        d3.width = 175;
        d3.height = 225;
        DocentScrollPane.setPreferredSize(d3);


        edit = new JButton(editString);
        edit.setActionCommand(editString);
        edit.addActionListener(new EditListener());

        addProject = new JButton(addString);
        addProject.setActionCommand(addString);
        addProject.addActionListener(new ProjectAddListener());
        
        addDocent = new JButton(addString);
        addDocent.setActionCommand(addString);
        addDocent.addActionListener(new DocentAddListener());
        
        addGrantee = new JButton(addString);
        addGrantee.setActionCommand(addString);
        addGrantee.addActionListener(new GranteeAddListener());
        
        
        JPanel ProjectButtonPane = new JPanel();
        ProjectButtonPane.setLayout(new BoxLayout(ProjectButtonPane, BoxLayout.LINE_AXIS));
        ProjectButtonPane.add(edit);
        ProjectButtonPane.add(new JSeparator(SwingConstants.VERTICAL));
        ProjectButtonPane.add(addProject);
        ProjectButtonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        
        ProjectsLabel = new JLabel ("PROJECTS:");
        DocentsLabel = new JLabel ("DOCENTS:");
        GranteesLabel = new JLabel ("GRANTEES:");
        titleLabel= new JLabel ("Welcome to CISUC!");
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 24));
        
        c.insets = new Insets(50,75,0,75);
        c.gridy = 0;
        c.gridx = 1;
        panel.add(titleLabel, c);
       
        
        c.insets = new Insets(50,75,0,75);
        c.gridy = 1;
        c.gridx = 0;
        panel.add(ProjectsLabel, c);
                
        c.gridx = 1;
        panel.add(DocentsLabel, c);
        
        c.gridx = 2;
        panel.add(GranteesLabel, c);
        
        
        c.insets = new Insets(10,75,0,75);
        c.gridx = 0;      
        c.gridy = 2;
        panel.add(ProjectScrollPane, c);
        
        c.gridx = 1;
        panel.add(DocentScrollPane, c);
        
        c.gridx = 2; 
        panel.add(GranteeScrollPane, c);
        
        c.insets = new Insets(0,75,0,75);
        c.gridy = 3; 
        c.gridx = 0;
        panel.add(ProjectButtonPane, c);
        
        c.gridx = 1;
        panel.add(addDocent,c);
        
        c.gridx = 2;
        panel.add(addGrantee,c);
        add(panel, new GridBagConstraints());
    }

    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = ProjectListF.getSelectedIndex();
            if(gProjectList.get(index).getName().compareTo(listaProjetos.get(index).toString())==0){
                ProjectEditFrame projectEdition = new ProjectEditFrame(gProjectList.get(index),listaProjetos,index,gGrantees,gDocents);
                System.out.printf("%s\n",listaProjetos.get(index).toString());
            }
            int size = listaProjetos.getSize();

            if (size == 0) { 
                edit.setEnabled(false);
            } else {
                ProjectListF.setSelectedIndex(index);
                ProjectListF.ensureIndexIsVisible(index);
            }
        }
    }

    class ProjectAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProjectAddFrame projectAddition = new ProjectAddFrame(gProjectList, listaProjetos);  
        }
    }
    
    class DocentAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DocentAddFrame docentAddition = new DocentAddFrame(gDocents, listaDocents);  
        }
    }
    
    class GranteeAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GranteeAddFrame granteeAddition = new GranteeAddFrame(gGrantees, listaGrantees);  
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (ProjectListF.getSelectedIndex() == -1) {
                edit.setEnabled(false);

            } else {
                edit.setEnabled(true);
            }
        }
    }
}
    
