import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

     
public class ProjectEditFrame {
    private Project projeto;
    private JPanel panel= new JPanel();
    private JLabel label;
    private JFrame f = new JFrame("Edit Project options");
    private JButton buttonEdit,buttonManage;
    private DefaultListModel listModel;
    private int index;
    
    public ProjectEditFrame(Project proj,DefaultListModel list, int i) {
        index= i;
        listModel=list;
        projeto=proj;
        
        f.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        label=new JLabel ("PROJECT OPTIONS");
        //c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets(0,0,20,0);
        c.gridx = 0;      
        c.gridy = 1;
        panel.add(label,c);
        
        buttonEdit = new JButton("Edit Project");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,5,0);
        c.ipady = 0;       
        c.gridy = 2;
        panel.add(buttonEdit,c);

        buttonManage = new JButton("Manage Project");
        c.ipady = 0;       
        c.gridy = 3;
        panel.add(buttonManage,c);


        buttonEditListener listener1=new buttonEditListener();
        buttonEdit.addActionListener(listener1);
        
        buttonManageListener listener2=new buttonManageListener();
        buttonManage.addActionListener(listener2);
         

        f.add(panel, new GridBagConstraints());
        f.setSize(300,300);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private class buttonEditListener implements ActionListener{
        @Override    
        public void actionPerformed(ActionEvent e){
        ProjectEdit projectEdition = new ProjectEdit(projeto,listModel,index);
        f.dispose();
        }
    }
    private class buttonManageListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
            ProjectManage projectManagement = new ProjectManage(projeto);
            f.dispose();
        }
           
    }
}
