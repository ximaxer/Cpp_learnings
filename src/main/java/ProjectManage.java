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
    private JFrame f = new JFrame("Manage Project"), f2 = new JFrame("list completed"),f3 = new JFrame("not initialized"),f4 = new JFrame("delayed tasks");
    ArrayList<Task> gTaskList,copyTaskList=new ArrayList<Task>() ;
    private DefaultListModel listaTasks, compTasks,notITasks, dTasks;
    private JList taskList,complTasks,notInitTasks,delTasks;
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
        taskList.addListSelectionListener(new taskAlreadyAssignedListener());
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
        assignTask.addActionListener(new AssignListener());
        
        listCompTasks = new JButton("list completed");
        listCompTasks.setActionCommand("list completed");
        listCompTasks.addActionListener(new ListCompTasksListener());
        
        listNotInitTasks = new JButton("list not initialized");
        listNotInitTasks.setActionCommand("list not initialized");
        listNotInitTasks.addActionListener(new ListNotInitTasksListener());
        
        listDelayedTasks = new JButton("list delayed");
        listDelayedTasks.setActionCommand("list delayed");
        listDelayedTasks.addActionListener(new ListDelayedTasksListener());
       
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
    class taskAlreadyAssignedListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) { 
            int taskAssignI;
            taskAssignI=taskList.getSelectedIndex();
            if(gTaskList.get(taskAssignI).responsible!=null){
                assignTask.setEnabled(false);
            }
            else{
            assignTask.setEnabled(true);
            }
        }
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
    private class AssignListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
           int index = taskList.getSelectedIndex();
           TaskAssignment assignTask = new TaskAssignment(projeto, index);
                
        }
    
    }
    private class ListCompTasksListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                copyTaskList.clear();
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
                    complTasks = new JList(compTasks);
                    f2.add(complTasks, BorderLayout.CENTER);
                    f2.setSize(300,300);
                    f2.setVisible(true);
                    f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
            try{
                copyTaskList.clear();
                for(int i=0;i<gTaskList.size();i++){
                    if(gTaskList.get(i).getProgress()==0){
                        copyTaskList.add(gTaskList.get(i));
                        
                    }
                }
                if(copyTaskList.size()!=0){
                    
                    notITasks = new DefaultListModel();
                    for(int j=0;j<copyTaskList.size();j++){
                        notITasks.addElement(copyTaskList.get(j).getTName());
                    }
                    notInitTasks = new JList(notITasks);
                    f3.add(notInitTasks, BorderLayout.CENTER);
                    f3.setSize(300,300);
                    f3.setVisible(true);
                    f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
            catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,"Error, all the tasks have been initialized!","Error",JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }
    private class ListDelayedTasksListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{copyTaskList.clear();
                Calendar est = (Calendar)Calendar.getInstance();
                for(int i=0;i<gTaskList.size();i++){
                    Calendar currentT = Calendar.getInstance();
                    est.setTime(gTaskList.get(i).initialTDate);
                    est.add(Calendar.MONTH , gTaskList.get(i).estTDuration);
                    Date dataEstFinal = est.getTime();    //dataEstFinal -> data inicial + estimated duration
                    Date dataHoje = currentT.getTime();     
                    if(dataHoje.compareTo(dataEstFinal)>0 ){
                        copyTaskList.add(gTaskList.get(i));

                    }               
                }
                if(copyTaskList.size()!=0){
                    dTasks = new DefaultListModel();
                    for(int j=0;j<copyTaskList.size();j++){
                        dTasks.addElement(copyTaskList.get(j).getTName());
                    }
                    delTasks = new JList(dTasks);
                    f4.add(delTasks, BorderLayout.CENTER);
                    f4.setSize(300,300);
                    f4.setVisible(true);
                    f4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    }
                else{
                    throw new IllegalArgumentException();
                }
            }
            catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,"Error, there are no delayed tasks!","Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
}