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
    private JButton buttonEdit,bManageTasks,bManagePersonel;
    private DefaultListModel listModel;
    private int index;
    private ArrayList<Grantee> gGranteeList;
    private ArrayList<Docent> gDocentList;
    
    public ProjectEditFrame(Project proj,DefaultListModel list, int i,ArrayList<Grantee> GranteeList,ArrayList<Docent> DocentList) {
        index= i;
        listModel=list;
        projeto=proj;
        gDocentList=DocentList;
        gGranteeList = GranteeList;
        
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

        bManageTasks = new JButton("Manage Tasks");
        c.ipady = 0;       
        c.gridy = 3;
        panel.add(bManageTasks,c);
        
        bManagePersonel = new JButton("Manage Personel");
        c.ipady = 0;       
        c.gridy = 4;
        panel.add(bManagePersonel,c);


        buttonEditListener listener1 = new buttonEditListener();
        buttonEdit.addActionListener(listener1);
        
        bManageTasksListener listener2 = new bManageTasksListener();
        bManageTasks.addActionListener(listener2);
        
        bManagePersonelListener listener3 = new bManagePersonelListener();
        bManagePersonel.addActionListener(listener3);
         

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
    private class bManageTasksListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
            ProjectManage projectManagement = new ProjectManage(projeto);
            f.dispose();
        }    
    }
    private class bManagePersonelListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
            ManagePersonelFather PersonelManagement = new ManagePersonelFather(projeto, gGranteeList,gDocentList);
            f.dispose();
        }
           
    }
}
