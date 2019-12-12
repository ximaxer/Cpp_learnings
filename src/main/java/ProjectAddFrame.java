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
public class ProjectAddFrame {
    private JLabel ProjectNameLabel, IDateLabel, AcronymLabel, estDurationLabel;
    private JTextField ProjectNameInput, AcronymInput, IDateInput, estDurationInput;
    private JButton addButton;
    private JPanel panel= new JPanel();
    private JFrame f = new JFrame("Add Project");
    private DefaultListModel listModel;
    ArrayList<Project> gProjectList;

    public ProjectAddFrame(ArrayList<Project> ProjectList,DefaultListModel list) {
        gProjectList=ProjectList;
        listModel = list;
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
                String ProjectName = ProjectNameInput.getText();
                String ProjectAcronym = AcronymInput.getText();
                String InitialDate = IDateInput.getText();
                Date FormatedDate=new SimpleDateFormat("dd/MM/yyyy").parse(InitialDate); 
                String estDuration = estDurationInput.getText();
                int FormatedEstDuration = Integer.parseInt(estDuration);
                if (ProjectAcronym.trim().isEmpty() || ProjectName.trim().isEmpty() || InitialDate.trim().isEmpty() ||estDuration.trim().isEmpty()){
                    throw new IllegalArgumentException();
                }
                Project newProject = new Project(ProjectName, ProjectAcronym, FormatedDate, FormatedEstDuration);
                gProjectList.add(newProject);
                listModel.addElement(newProject.getName());
                panel.updateUI();
                f.dispose();
            } catch (ParseException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null,"Input Error, Please use the following format:\n\nName\nAcronym\ndd/mm/yy\nEstimated duration","Error on Parse",JOptionPane.INFORMATION_MESSAGE);;
            } 
        } 
    }
}
