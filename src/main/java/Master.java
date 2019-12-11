
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ximax
 */
public class Master extends Grantee{
    //========================================================================//
    //========================================================================//
    //============================             ===============================//
    //============================  variables  ===============================//
    //============================             ===============================//
    //========================================================================//
    //========================================================================//
    ArrayList<Docent> masterGuides = new ArrayList<Docent>();
    Project project;
    //========================================================================//
    //========================================================================//
    //===========================                =============================//
    //===========================  Constructors  =============================//
    //===========================                =============================//
    //========================================================================//
    //========================================================================//
    public Master(){
        super.name=null;
        super.email=null;
        cost();
    }
    
    public Master(String name, String email){
        super.name=name;
        super.email=email;
        cost();
    }
    
    //========================================================================//
    //========================================================================//
    //=============================           ================================//
    //=============================  methods  ================================//
    //=============================           ================================//
    //========================================================================//
    //========================================================================//
public void addGuide(Docent guide){
        boolean verificaFeedback=false;
        for(int i=0; i<guide.projects.size();i++){
            if (guide.projects.get(i).getName().compareTo(this.project.getName())==0){
                this.masterGuides.add(guide);
                verificaFeedback=true;
                break;
            }
        }
        if(verificaFeedback==true){
            System.out.printf("\nSuccessfuly added %s as %s's guide.",guide.getName(),this.getName());
        }else{
            System.out.printf("\n%s is not working on the same project as %s",guide.getName(),this.getName());
        }        
    }


    @Override
    public void cost(){
        super.Salary=1000.00;
    }
}
