import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class AddTask {
    private JLabel TaskNameLabel, initialDateLabel, estDurationLabel;
    private JTextField TaskNameInput, initialDateInput, estDurationInput;
    private JButton addButton;
    private JPanel panel= new JPanel();
    private JFrame f = new JFrame("Add Task");
    private DefaultListModel listModel;
    private ArrayList<Task> gTaskList;
    String tipo;

    public AddTask(ArrayList<Task> TaskList, DefaultListModel list) {
        String[] Types = { "Design", "Development", "Documentation" };
        JComboBox ChooseType = new JComboBox(Types);
        ChooseTypeListener CBlistener=new ChooseTypeListener();
        ChooseType.addActionListener(CBlistener);
        
        gTaskList=TaskList;
        listModel = list;
        f.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 2;      
        c.insets = new Insets(0,10,60,0);
        panel.add(ChooseType,c);
        
        TaskNameLabel=new JLabel ("Task Name:");   
        c.insets = new Insets(0,10,0,0);
        c.gridy = 1;
        c.gridx = 1; 
        panel.add(TaskNameLabel,c);

        TaskNameInput=new JTextField(10);
        c.ipady = 5; 
        c.gridx = 2;
        c.gridy = 1;
        panel.add(TaskNameInput,c);

        initialDateLabel=new JLabel("InitialDate:");
        c.gridx = 1;      
        c.gridy = 2;
        panel.add(initialDateLabel,c);

        initialDateInput=new JTextField(10);
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(initialDateInput,c);


        estDurationLabel=new JLabel("Estimated duration:");
        c.gridx = 1;      
        c.gridy = 3;
        panel.add(estDurationLabel,c);

        estDurationInput=new JTextField(10);
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 3;
        panel.add(estDurationInput,c);


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
                String name =  TaskNameInput.getText();
                String iDate = initialDateInput.getText(); 
                String eDuration = estDurationInput.getText();
                Date FormatedInitialDate =new SimpleDateFormat("dd/MM/yyyy").parse(iDate); 
                int FormatedEstimatedDate = Integer.parseInt(eDuration);
                if (name.trim().isEmpty() || iDate.trim().isEmpty() || eDuration.trim().isEmpty()){
                    throw new IllegalArgumentException();
                }
                if(tipo.compareTo("Design")==0){
                    Design newDesignTask = new Design(name, FormatedInitialDate, FormatedEstimatedDate);
                    gTaskList.add(newDesignTask);
                    listModel.addElement(newDesignTask.getTName());
                    
                }else if(tipo.compareTo("Development")==0){
                    Development newDevelopmentTask = new Development(name, FormatedInitialDate, FormatedEstimatedDate);                    
                    gTaskList.add(newDevelopmentTask);
                    listModel.addElement(newDevelopmentTask.getTName());
                    
                }else if(tipo.compareTo("Documentation")==0){
                    Documentation newDocumentationTask = new Documentation(name, FormatedInitialDate, FormatedEstimatedDate);
                    gTaskList.add(newDocumentationTask);
                    listModel.addElement(newDocumentationTask.getTName());
                }
                panel.updateUI();
                f.dispose();
            }catch (ParseException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null,"Input Error, Please use the following format:\n\nName\nInitialDate\nEstimated Duration","Error on Parse",JOptionPane.INFORMATION_MESSAGE);;
            }
        } 
    }
    
    private class ChooseTypeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
           JComboBox cb = (JComboBox)e.getSource();
            tipo = (String)cb.getSelectedItem();
             
        }  
    }
}
