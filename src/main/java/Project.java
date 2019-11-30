import java.util.ArrayList;
import java.util.*;
public class Project extends InvestigationCenter {
    //========================================================================//
    //========================================================================//
    //============================             ===============================//
    //============================  variables  ===============================//
    //============================             ===============================//
    //========================================================================//
    //========================================================================//  
    ArrayList<Docent> docents = new ArrayList<Docent>();
    ArrayList<Grantee> grantees = new ArrayList<Grantee>();
    ArrayList<Tasks> tasks = new ArrayList<Tasks>();
    Person mD;
    String name, acronym;
    Date initialDate,finalDate;
    double estDuration;
    boolean projectState;
    //========================================================================//
    //========================================================================//
    //===========================                =============================//
    //===========================  Constructors  =============================//
    //===========================                =============================//
    //========================================================================//
    //========================================================================//
    //I DON'T UNDERSTAND WHY WE HAVE TO DO THIS, SO PROB IS WRONG, CAGUEI
    public Project(){
        this.name=null;
        this.acronym=null;
        this.initialDate=null;
        this.estDuration=0;
        this.finalDate=null;
    }

    public Project(String name,String acronym,Date initDate,double estDuration,Date finalDate){
        this.name=name;
        this.acronym=acronym;
        this.initialDate=initDate;
        this.estDuration=estDuration;
        this.finalDate=finalDate;
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
    
    public String getAcronym(){
        return acronym;
    }
    public void setAcronym(String acronym){
        this.acronym=acronym;
    }
    
    public Date getInitialDate(){
        return initialDate;
    }
    public void setInitialDate(Date initDate){
        this.initialDate=initDate;
    }
    
    public double getEstDuration(){
        return estDuration;
    }
    public void setEstDuration(double estDuration){
        this.estDuration=estDuration;
    }
    
    public Date getFinalDate(){
        return finalDate;
    }
    public void setFinalDate(Date finalDate){
        this.finalDate=finalDate;
    }
    public void DeleteTask(){
        
    }
}
