
import javax.swing.*;
import java.awt.event.*;
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
        private JLabel labelInput, labelResult;
        private JTextField textFieldInput,textFieldResult;
        private JButton buttonConvert;
        private JPanel panel= new JPanel();
        private JFrame f = new JFrame("Add Project");
	
	public ProjectAddFrame() {
            
            labelInput=new JLabel ("Celsius:");
            labelInput.setBounds(10,10,100,25);
            textFieldInput=new JTextField(10);
            textFieldInput.setBounds(110,10,150,25);
            labelResult=new JLabel("Fahrenheit:");
            labelResult.setBounds(10,40,100,25);
            textFieldResult=new JTextField(10);
            textFieldResult.setBounds(110,40,150,25);
            textFieldResult.setEditable(false);
            buttonConvert = new JButton("Converter");
            buttonConvert.setBounds(110,70,150,25);

            AddBListener listener=new AddBListener();
            buttonConvert.addActionListener(listener);
            panel.add(labelInput);
            panel.add(textFieldInput);
            panel.add(labelResult);
            panel.add(textFieldResult);
            panel.add(buttonConvert);
            f.add(panel);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setSize(300,300);
            f.setVisible(true);
        }
        
        private class AddBListener implements ActionListener{
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
}
