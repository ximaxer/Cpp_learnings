import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class ProjectManage {
    private Project projeto;
    private JLabel label;
    private JPanel panel= new JPanel();
    private JFrame f = new JFrame("Manage Project");
    ArrayList<Task> gTaskList;
    private DefaultListModel listModel;
    private JList list;
    private JButton edit, add;
    
    public ProjectManage(Project proj) {
        projeto=proj;
        gTaskList=projeto.tasks;
        listModel = new DefaultListModel();
        for(int i=0;i<gTaskList.size();i++){
            System.out.println(gTaskList.get(i).getTName());
            listModel.addElement(gTaskList.get(i).getTName());
        }
        System.out.println();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list.addListSelectionListener(this)
        JScrollPane listScrollPane = new JScrollPane(list);


        edit = new JButton("edit task");
        edit.setActionCommand("edit task");
        edit.addActionListener(new EditListener());
        
        add = new JButton("add task");
        add.setActionCommand("add task");
        add.addActionListener(new AddListener());
        
       
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(edit);
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(add);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        f.add(listScrollPane, BorderLayout.CENTER);
        f.add(buttonPane, BorderLayout.PAGE_END);
        f.setSize(300,300);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //add(listScrollPane, BorderLayout.CENTER);
        //add(buttonPane, BorderLayout.PAGE_END);
    }
        
        
    private class EditListener implements ActionListener{
        @Override

        public void actionPerformed(ActionEvent e){

        }
    }
    
    private class AddListener implements ActionListener{
        @Override

        public void actionPerformed(ActionEvent e){

        }
    }
}
