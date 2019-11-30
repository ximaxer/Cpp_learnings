import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ximax
 */
abstract public class Person extends InvestigationCenter{
    //========================================================================//
    //========================================================================//
    //============================             ===============================//
    //============================  variables  ===============================//
    //============================             ===============================//
    //========================================================================//
    //========================================================================//
    protected String name, email;
    protected double Salary;
    ArrayList<Task> tasks = new ArrayList<Task>();
   
    
    //========================================================================//
    //============================================= ===========================//
    //===========================                =============================//
    //===========================  Constructors  =============================//
    //===========================                =============================//
    //========================================================================//
    //========================================================================//
    public Person() {
        this.name = null;
        this.email = null;
    }
    
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    //========================================================================//
    //========================================================================//
    //=============================           ================================//
    //=============================  methods  ================================//
    //=============================           ================================//
    //========================================================================//
    //========================================================================// 

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean verifyEffor(){
        Task A = new Task();
        double effort=0;
        for(int i=0;i<tasks.size();i++){
                A = tasks.get(i);
                effort+=A.effort();
        }
        if(effort>=1){
            return false;       //nao pode ter mais tarefas
        }else{
            return true;        //pode ter mais tarefas
        }
    }
    abstract void cost();
}
