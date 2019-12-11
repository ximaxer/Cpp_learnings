import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ximax
 */
public class ProjectAddFrame {
        private JLabel ProjectNameLabel, IDateLabel, AcronymLabel, estDurationLabel;
        private JTextField ProjectNameInput, AcronymInput, IDateInput, estDurationInput;
        private JButton addButton;
        private JPanel panel= new JPanel();
        private JFrame f = new JFrame("Add Project");
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	public ProjectAddFrame(ArrayList<Project> ProjectList) {
            f.setLayout(new GridBagLayout());
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            
            ProjectNameLabel=new JLabel ("Project Name:");
            c.insets = new Insets(0,10,0,0);
            c.gridx = 1;      
            c.gridy = 1;
            panel.add(ProjectNameLabel,c);
            
            ProjectNameInput=new JTextField(10);
            c.ipady = 5; 
            c.gridx = 2;
            c.gridy = 1;
            panel.add(ProjectNameInput,c);
            
            AcronymLabel=new JLabel("Acronym:");
            c.gridx = 1;      
            c.gridy = 2;
            panel.add(AcronymLabel,c);
            
            AcronymInput=new JTextField(10);
            c.ipady = 5;
            c.gridx = 2;
            c.gridy = 2;
            panel.add(AcronymInput,c);
            
            
            IDateLabel=new JLabel("Initial Date:");
            c.gridx = 1;      
            c.gridy = 3;
            panel.add(IDateLabel,c);
            
            IDateInput=new JTextField(10);
            c.ipady = 5;
            c.gridx = 2;
            c.gridy = 3;
            panel.add(IDateInput,c);
            
            
            estDurationLabel=new JLabel("Estimated Duration:");
            c.gridx = 1;      
            c.gridy = 4;
            panel.add(estDurationLabel,c);
            
            estDurationInput=new JTextField(10);
            c.ipady = 5;
            c.gridx = 2;
            c.gridy = 4;
            panel.add(estDurationInput,c);
            
            addButton = new JButton("Adicionar");
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
                    String Projectname = ProjectNameInput.getText();
                    String ProjectAcronym = AcronymInput.getText();
                    String InitialDate = IDateInput.getText();
                    Date FormatedDate=new SimpleDateFormat("dd/MM/yyyy").parse(InitialDate); 
                    String estDuration = estDurationInput.getText();
                    int FormatedEstDuration = Integer.parseInt(estDuration);
                    Project newProject = new Project(Projectname, ProjectAcronym, FormatedDate, FormatedEstDuration);
                    ProjectList.add(newProject);
                
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"piça","janela name",JOptionPane.INFORMATION_MESSAGE);;
                }
            }
            
            
        }
}
