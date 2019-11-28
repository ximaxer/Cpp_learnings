import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class InvestigationCenter {
    //variables
    ArrayList<Project> projects = new ArrayList<Project>();
    
    
    //constructor
    public InvestigationCenter(){
        //?
    }
    
    //methods
    public void showDelayedP(){
        Project A = new Project();
        println("\nPROJETOS ATRASADOS:");
        for(int i=0;i<projects.size();i++){
            A = Projects.get(i);
            
            Calendar currentT = Calendar.getInstance();
            Calendar est = Calendar.setTime(A.date);
            est= Calendar.getInstance(A.date);
            est.add(Calendar.MONTH , A.estDuration);
            Date dataEstFinal = est.getTime();    //dataEstFinal -> data inicial + estimated duration
            Date dataHoje = currentT.getTime();
            
            
            if(dataHoje.compareTo(dataEstFinal)==1){
                printf("\nProjeto: %s", A.name);
            }               
        }
    }
    public void showFinishedP(){
        
    }
    public static void main(String[] args) {
        
    }
    
}
