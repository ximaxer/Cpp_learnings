/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
/**
 *
 * @author ximax
 */
/* landingScreen.java requires no other files. */

public class landingScreen extends JPanel
    implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String addString = "Add";
    private static final String editString = "Edit";
    private JButton edit, add;
    ArrayList<Project> gProjectList;
    
    public landingScreen(ArrayList<Project> ProjectList) {
        super(new BorderLayout());
        gProjectList=ProjectList;
        listModel = new DefaultListModel();
        for(int i=0;i<ProjectList.size();i++)
        listModel.addElement(gProjectList.get(i).getName());

        
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);


        edit = new JButton(editString);
        edit.setActionCommand(editString);
        edit.addActionListener(new EditListener());

        add = new JButton(addString);
        add.setActionCommand(addString);
        add.addActionListener(new AddListener());
        
        
        String name = listModel.getElementAt(
                              list.getSelectedIndex()).toString();

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(edit);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(add);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            if(gProjectList.get(index).getName().compareTo(listModel.get(index).toString())==0){
                ProjectEditFrame projectEdition = new ProjectEditFrame(gProjectList.get(index),listModel,index);
                System.out.printf("%s\n",listModel.get(index).toString());
            }
            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                edit.setEnabled(false);

            } else {
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    //This listener is shared by the text field and the hire button.
    class AddListener implements ActionListener {
        //Required by ActionListener.
        @Override
        public void actionPerformed(ActionEvent e) {
            ProjectAddFrame projectAddition = new ProjectAddFrame(gProjectList, listModel);
            
        }
    }

    //This method is required by ListSelectionListener.

    /**
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
            //No selection, disable fire button.
                edit.setEnabled(false);

            } else {
            //Selection, enable the fire button.
                edit.setEnabled(true);
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    /*private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("landingScreen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new landingScreen();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }*/

    }
    
    
    /*public void alandingScreen(){
        //super(); // extends Jframe
        panel=new JPanel();
        panel.setLayout(null);
        labelInput=new JLabel ("PISSUS");
        textFieldInput=new JTextField(10);
        textFieldInput.setBounds(110,10,150,25);
        labelResult=new JLabel("ERECTUS");
        labelResult.setBounds(10,40,100,25);
        textFieldResult=new JTextField(10);
        textFieldResult.setBounds(110,40,150,25);
        textFieldResult.setEditable(false);
        buttonConvert = new JButton("Converter");
        buttonConvert.setBounds(110,70,150,25);
       
        
        JTextField jt = new JTextField("TYPE", 10);
        JScrollPane myScrollPane = new JScrollPane(jt); 
        panel.add(myScrollPane);

        setContentPane(myScrollPane);
        
        ButtonListener listener=new ButtonListener();
        buttonConvert.addActionListener(listener);
        panel.add(labelInput);
        panel.add(textFieldInput);
        panel.add(labelResult);
        panel.add(textFieldResult);
        panel.add(buttonConvert);
        this.add(panel);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = env.getMaximumWindowBounds();
        System.out.println("Screen Bounds: " + bounds);

        
        
        panel.setVisible(true);
        this.getRootPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                GraphicsDevice screen = env.getDefaultScreenDevice();
                GraphicsConfiguration config = screen.getDefaultConfiguration();
                labelInput.setBounds(panel.getWidth()-100,panel.getHeight()-25,100,25);
                //labelInput.setFont(new Font("Serif",Font.BOLD,3*(((panel.getHeight()+panel.getWidth())/100))));
                System.out.printf("Y : %d X : %d\n", panel.getHeight(), panel.getWidth());
                
            }
        });
    }
        private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String input = textFieldInput.getText();
                double celsius = Double.parseDouble(input);
                double fahr =(1.8*celsius)+32;
                textFieldResult.setText(String.format("%.2f", fahr));
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"errooooou","janela name",JOptionPane.ERROR_MESSAGE);
            }
        }
            
            
        }
    */
    
