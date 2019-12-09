import java.util.*;
import java.io.*;
import java.text.*;

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
        Calendar est = (Calendar)Calendar.getInstance();
        Calendar currentT = Calendar.getInstance();
        System.out.println("\nDELAYED PROJECTS:");
        for(int i=0;i<projects.size();i++){
            if(projects.get(i).getFinalDate()==null){
                est.setTime(projects.get(i).getInitialDate());
                est.add(Calendar.MONTH , projects.get(i).getEstDuration());
                Date dataEstFinal = est.getTime();    //dataEstFinal -> data inicial + estimated duration
                Date dataHoje = currentT.getTime();     

                if(dataHoje.compareTo(dataEstFinal)==1){
                    System.out.printf("\nProject: %s", projects.get(i).getName());
                }               
            }
        }
    }
    
    public void showFinishedP(){
        System.out.println("\nFINISHED PROJECTS:");
        for(int i=0;i<projects.size();i++){
            if(projects.get(i).getFinalDate()!=null){
                System.out.printf("\nProject: %s", projects.get(i).getName());
            }   
        }
    }



public void ReadProjectFromFile(){
        BufferedReader reader;
        
        int i = 0, est = 0;
        Date date = new Date(0,0,0);
        String acronym = "", name = "";
        
        File personf = new File("C:\\Users\\ximax\\Desktop\\college gomin\\POO\\ProjetoPoo\\Project_list.txt");
        if(personf.exists() && personf.isFile()){
            try{
                reader = new BufferedReader(new FileReader(personf));
                    String line = reader.readLine();
                    while (line != null) {        
                        String[] tokens = line.split("\t");
                           i=0;
                        for (String t : tokens){
                            i++;
                            switch(i){
                                case 1:
                                    name = t;
                                    break;
                                case 2:
                                    acronym = t;
                                    break;
                                case 3:
                                    est = Integer.parseInt(t);
                                    break;
                                case 4:
                                    try{
                                        date=new SimpleDateFormat("dd/MM/yyyy").parse(t);
                                    }catch(ParseException e){
                                        e.printStackTrace();
                                    }
                                    break;
                                default:
                                    break;
                            } 
                        }
                        Project project = new Project(name, acronym, date, est);
                        projects.add(project);
                        
                        line = reader.readLine();
                        //le a proxima linha e passa à proxime iteração                            
                        //do ciclo com a nova linha
                        //de forma a garantir que a linha não é nula
                    }
                    reader.close();
            }catch(IOException e){
                e.printStackTrace();
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
