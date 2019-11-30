import java.util.*;

public class InvestigationCenter {
    //========================================================================//
    //========================================================================//
    //============================             ===============================//
    //============================  variables  ===============================//
    //============================             ===============================//
    //========================================================================//
    //========================================================================//
    ArrayList<Project> projects = new ArrayList<Project>();
    
    
    
    
    
    //========================================================================//
    //========================================================================//
    //===========================                =============================//
    //===========================  Constructors  =============================//
    //===========================                =============================//
    //========================================================================//
    //========================================================================//
    public InvestigationCenter(){
        //?
    }
    
    
    
    
    
    //========================================================================//
    //========================================================================//
    //=============================           ================================//
    //=============================  methods  ================================//
    //=============================           ================================//
    //========================================================================//
    //========================================================================//
    public void showDelayedP(){
        Project A = new Project();
        System.out.println("\nPROJETOS ATRASADOS:");
        for(int i=0;i<projects.size();i++){
            A = projects.get(i);
            
            Calendar currentT = Calendar.getInstance();
            Calendar est = Calendar.setTime(A.date);
            est= Calendar.getInstance(A.date);
            est.add(Calendar.MONTH , A.estDuration);
            Date dataEstFinal = est.getTime();    //dataEstFinal -> data inicial + estimated duration
            Date dataHoje = currentT.getTime();
            
            
            if(dataHoje.compareTo(dataEstFinal)==1){
                System.out.printf("\nProjeto: %s", A.name);
            }               
        }
    }
    
    public void showFinishedP(){
        System.out.println("\nPROJETOS ATRASADOS:");
        for(int i=0;i<projects.size();i++){
            A = projects.get(i);
            if(A.finalDate!=null){
                System.out.printf("\nProjeto: %s", A.name);
            }   
        }
    }
    //========================================================================//
    //========================================================================//
    //=============================            ===============================//
    //=============================    main    ===============================//
    //=============================            ===============================//
    //========================================================================//
    //========================================================================//
    public static void main(String[] args) {
        
    }
    
}
