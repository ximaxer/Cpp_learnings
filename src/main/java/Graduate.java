
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
public class Graduate extends Grantee{
    //========================================================================//
    //========================================================================//
    //============================             ===============================//
    //============================  variables  ===============================//
    //============================             ===============================//
    //========================================================================//
    //========================================================================//  
    ArrayList<Docent> graduateGuides = new ArrayList<Docent>();
    Project project;
    //========================================================================//
    //========================================================================//
    //===========================                =============================//
    //===========================  Constructors  =============================//
    //===========================                =============================//
    //========================================================================//
    //========================================================================//
    public Graduate(){
        super.name=null;
        super.email=null;
        cost();
    }
    
    public Graduate(String name, String email, Date idate, Date fdate){
        super.name=name;
        super.email=email;
        super.initialDate=idate;
        super.finalDate=fdate;
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
                this.graduateGuides.add(guide);
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
        super.Salary=800.00;
    }
}
