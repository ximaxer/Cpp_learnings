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
    private ArrayList<Docent> docents = new ArrayList<Docent>();
    private ArrayList<Grantee> grantees = new ArrayList<Grantee>();
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Person mD;
    private String name, acronym;
    private Date initialDate,finalDate;
    private int estDuration;
    private boolean projectState;
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

    public Project(String name,String acronym,Date initDate,int estDuration,Date finalDate){
        this.name=name;
        this.acronym=acronym;
        this.initialDate=initDate;
        this.estDuration=estDuration;
        this.finalDate=null;

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
    
    public int getEstDuration(){
        return estDuration;
    }
    public void setEstDuration(int estDuration){
        this.estDuration=estDuration;
    }
    
    public Date getFinalDate(){
        return finalDate;
    }
    public void setFinalDate(Date finalDate){
        this.finalDate=finalDate;
    }
    public void deleteTask(int taskI){
        tasks.remove(taskI);
        System.out.printf("\nTask %d deleted with success!", taskI); 
    }
    public void showTasks(){
        Task L = new Task();
        for(int i=0; i<tasks.size();i++){
        
        }
    }
        
}
