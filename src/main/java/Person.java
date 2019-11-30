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
    String name, email;
    double Salary;
    ArrayList<Tasks> tarefas = new ArrayList<Tasks>();
   
    
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
        Tasks A = new Tasks();
        double effort=0;
        for(int i=0;i<tarefas.size();i++){
                A = tarefas.get(i);
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
