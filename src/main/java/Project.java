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

    public Project(){
        this.name=null;
        this.acronym=null;
        this.initialDate=null;
        this.estDuration=0;
        this.finalDate=null;
        this.projectState=true;
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
    public void showTaskList(){
        System.out.println("\nLISTING TASKS!");
        for(int i=0; i<tasks.size();i++){
        System.out.printf("\nTask %s ", tasks.get(i).getTName()); 
        }
    }
    public void showUnitializedTasks(){
        System.out.println("\nLISISTING UNITIALIZED TASKS");
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).getProgress()==0){
                System.out.printf("\nTask %s not initalized", tasks.get(i).getTName());
            }
        }
    }
    public void showDelayedTasks(){
        System.out.println("\nLISTING DELAYED TASKS");
        Calendar est = (Calendar)Calendar.getInstance();
        for(int i=0;i<tasks.size();i++){
            Calendar currentT = Calendar.getInstance();
            est.setTime(tasks.get(i).initialTDate);
            est.add(Calendar.MONTH , tasks.get(i).estTDuration);
            Date dataEstFinal = est.getTime();    //dataEstFinal -> data inicial + estimated duration
            Date dataHoje = currentT.getTime();     
            if(dataHoje.compareTo(dataEstFinal)==1){
                System.out.printf("\n Task: %s delayed", tasks.get(i).Tname);
            }               
        }
    }
    
    public void showCompletedTasks(){
    System.out.println("\nLISTING COMPLETED TASKS");
    for(int i = 0;i<tasks.size();i++){
        if(tasks.get(i).getFinalTDate().compareTo(null)!=0){
            System.out.printf("\n Task: %d completed",tasks.get(i).getFinalTDate());
        }
    }
    }
    public void getProjectCost(){
    double cost=0;
    for(int i =0;i<grantees.size();i++){
        cost+=grantees.get(i).Salary;
    }
    System.out.printf("\nProject %s cost:%f",getName(),cost);
    }
    public void terminateProject(){
        this.projectState=false;
    }
}
