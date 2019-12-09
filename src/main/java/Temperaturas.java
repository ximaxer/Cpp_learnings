//package com.mycompany.temperatura;
//there u go
import javax.swing.*;
import java.awt.event.*;
//import javax.swing.JFrame;
public class Temperatura extends JFrame{
    private JLabel labelInput, labelResult;
    private JTextField textFieldInput,textFieldResult;
    private JButton buttonConvert;
    private JPanel panel;
    
    public Temperatura (){
        super();
        panel=new JPanel();
        panel.setLayout(null);
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
       
        ButtonListener listener=new ButtonListener();
        buttonConvert.addActionListener(listener);
        panel.add(labelInput);
        panel.add(textFieldInput);
        panel.add(labelResult);
        panel.add(textFieldResult);
        panel.add(buttonConvert);
        this.add(panel);
           
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
    
    
        public static void main(String[]args){
            Temperatura frame=new Temperatura();
            frame.setTitle("Conversor de temperaturas");
            frame.setSize(300,150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
    }
}