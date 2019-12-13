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
    private JFrame f = new JFrame("Manage Project"), f3 = new JFrame("list completed");
    ArrayList<Task> gTaskList,copyTaskList=new ArrayList<Task>() ;
    private DefaultListModel listaTasks, compTasks;
    private JList taskList;
    private JButton finalize, add, remove,assignTask,listCompTasks,listNotInitTasks,listDelayedTasks;
    
    public ProjectManage(Project proj) {
        projeto=proj;
        gTaskList=projeto.tasks;
        listaTasks = new DefaultListModel();
        for(int i=0;i<gTaskList.size();i++){
            listaTasks.addElement(gTaskList.get(i).getTName()+"   "+gTaskList.get(i).getProgress()+"%");
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
        
        assignTask = new JButton("assign task");
        assignTask.setActionCommand("assign ask");
        //assignTask.addActionListener(new AssignListener());
        
        listCompTasks = new JButton("list completed");
        listCompTasks.setActionCommand("list completed");
        listCompTasks.addActionListener(new ListCompTasksListener());
        
        listNotInitTasks = new JButton("list not initialized");
        listNotInitTasks.setActionCommand("list not initialized");
        //listNotInitTasks.addActionListener(new ListNotInitTasksListener());
        
        listDelayedTasks = new JButton("list delayed");
        listDelayedTasks.setActionCommand("list delayed");
        //listDelayedTasks.addActionListener(new ListDelayedTasksListener());
       
        //Create a panel that uses BoxLayout.
        JPanel buttonPane1 = new JPanel();
        buttonPane1.setLayout(new BoxLayout(buttonPane1,
                                           BoxLayout.LINE_AXIS));
        buttonPane1.add(finalize);
        buttonPane1.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane1.add(add);
        buttonPane1.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane1.add(remove);
        buttonPane1.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane1.add(assignTask);
        buttonPane1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        JPanel buttonPane2 = new JPanel();
        buttonPane2.setLayout(new BoxLayout(buttonPane2,
                                           BoxLayout.LINE_AXIS));
        buttonPane2.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane2.add(listCompTasks);
        buttonPane2.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane2.add(listNotInitTasks);
        buttonPane2.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane2.add(listDelayedTasks);
        buttonPane2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        

        f.add(listScrollPane, BorderLayout.CENTER);
        f.add(buttonPane1, BorderLayout.PAGE_START);
        f.add(buttonPane2, BorderLayout.PAGE_END);
        f.setSize(450,450);
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
            JOptionPane.showMessageDialog(null,"Error, task is not 100% completed!","Error",JOptionPane.INFORMATION_MESSAGE);

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
                 listaTasks.remove(index); 
                 panel.updateUI();
                 gTaskList.remove(index);  
        }
    
    }
    /*private class AssignListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
           
                
        }
    
    }*/
    private class ListCompTasksListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                for(int i=0;i<gTaskList.size();i++){
                    if(gTaskList.get(i).getProgress()==100){
                        copyTaskList.add(gTaskList.get(i));
                        
                    }
                }
                if(copyTaskList.size()!=0){
                    
                    compTasks = new DefaultListModel();
                    for(int j=0;j<copyTaskList.size();j++){
                        compTasks.addElement(copyTaskList.get(j).getTName());
                    }
                    taskList = new JList(compTasks);
                    //JScrollPane listScrollPane = new JScrollPane(taskList);
                    f3.add(taskList, BorderLayout.CENTER);
                    f3.setSize(300,300);
                    f3.setVisible(true);
                    f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
            catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,"Error, there are no completed tasks yet!","Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    
    }
    private class ListNotInitTasksListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){


        }

    }
    /*
    private class ListDelayedTasksListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){


        }

    }*/
}