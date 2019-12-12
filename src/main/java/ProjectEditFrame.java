import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//project: name, acronym, initialDate,estDuration
     
public class ProjectEditFrame {
    private Project projeto;
    private JLabel labelName,labelAcronym;
    private JTextField textFieldName,textFieldAcronym;
    private JPanel panel= new JPanel();
    private JFrame f = new JFrame("Edit Project");
    private JButton buttonEdit;
    private DefaultListModel listModel;
    private int index;

    public ProjectEditFrame(Project proj,DefaultListModel list, int i) {
        index= i;
        listModel=list;
        projeto=proj;
        f.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        labelName=new JLabel ("Name:");
        c.insets = new Insets(0,10,0,0);
        c.gridx = 1;      
        c.gridy = 1;
        panel.add(labelName,c);

        textFieldName=new JTextField(projeto.getName());
        c.ipady = 5; 
        c.gridx = 2;
        c.gridy = 1;
        panel.add(textFieldName,c); 
        
        labelAcronym=new JLabel("Acronym:");
        c.gridx = 1;      
        c.gridy = 2;
        panel.add(labelAcronym,c);

        textFieldAcronym=new JTextField(projeto.getAcronym());
        c.ipadx = 105;
        c.ipady = 5;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(textFieldAcronym,c);

        buttonEdit = new JButton("Adicionar alteracoes");
        c.ipadx=0;
        c.ipady = 0;       //reset to default
        c.gridy = 3;
        panel.add(buttonEdit,c);
            
            
         
        buttonEditListener listener=new buttonEditListener();
        buttonEdit.addActionListener(listener);
       
        f.add(panel, new GridBagConstraints());
        f.setSize(300,300);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private class buttonEditListener implements ActionListener{
            @Override
            
            public void actionPerformed(ActionEvent e){
                try{
                    String ProjectName = textFieldName.getText();
                    String ProjectAcronym = textFieldAcronym.getText();
                    projeto.setName(ProjectName);
                    projeto.setAcronym(ProjectAcronym);
                    if(ProjectName.trim().isEmpty() || ProjectAcronym.trim().isEmpty()){
                        throw new IllegalArgumentException();
                    }
                    listModel.set(index,projeto.getName());
                    panel.updateUI();
                    f.dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null,"NAME OR/AND ACRONYM CANNOT BE EMPTY","NAME ERROR",JOptionPane.INFORMATION_MESSAGE);;
                }
            }
    }
}
