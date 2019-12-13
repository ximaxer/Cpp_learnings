import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.*;

public class ProjectManage {
    private Project projeto;
    private JLabel label;
    private JPanel panel= new JPanel();
    private JFrame f = new JFrame("Manage Project");
    ArrayList<Task> gTaskList;
    private DefaultListModel listaTasks;
    private JList taskList;
    private JButton finalize, add, remove;
    
    public ProjectManage(Project proj) {
        projeto=proj;
        gTaskList=projeto.tasks;
        listaTasks = new DefaultListModel();
        for(int i=0;i<gTaskList.size();i++){
            listaTasks.addElement(gTaskList.get(i).getTName());
        }
        taskList = new JList(listaTasks);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(taskList);

 
        finalize = new JButton("finalize task");
        finalize.setActionCommand("finalize task");
        finalize.addActionListener(new FinalizeListener());
        
        add = new JButton("add task");
        add.setActionCommand("add task");
        add.addActionListener(new AddListener());
        
        remove = new JButton("remove task");
        remove.setActionCommand("remove task");
        remove.addActionListener(new RemoveListener());
       
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(finalize);
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(add);
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(remove);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        f.add(listScrollPane, BorderLayout.CENTER);
        f.add(buttonPane, BorderLayout.PAGE_END);
        f.setSize(350,350);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
    }
        
        
    private class FinalizeListener implements ActionListener{
        @Override

        public void actionPerformed(ActionEvent e){
            try{
            int index = taskList.getSelectedIndex();
            Calendar currentT = Calendar.getInstance();
            Date dataHoje = currentT.getTime();
            //System.out.print((dataHoje));
            if(gTaskList.get(index).getTName().compareTo(listaTasks.get(index).toString())==0 && gTaskList.get(index).progress==100){
                gTaskList.get(index).initialTDate=dataHoje;
                }
            else{
                throw new IllegalArgumentException();
            }
            }
            catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,"Error, task is not 100% completed!","Error",JOptionPane.INFORMATION_MESSAGE);;

            }
        }
    }
    private class AddListener implements ActionListener{
        @Override

        public void actionPerformed(ActionEvent e){
            AddTask taskAddition = new AddTask(gTaskList, listaTasks);
        }
    }
    private class RemoveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int index = taskList.getSelectedIndex();
             if(gTaskList.get(index).getTName().compareTo(listaTasks.get(index).toString())==0){
                 listaTasks.remove(index); 
                 panel.updateUI();
                 gTaskList.remove(index);  
             }
        }
    }
}
