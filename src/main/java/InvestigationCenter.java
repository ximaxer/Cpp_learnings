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
        System.out.println("\nPROJETOS ATRASADOS:");
        for(int i=0;i<projects.size();i++){
            if(projects.get(i).getFinalDate()==null){
                est.setTime(projects.get(i).getInitialDate());
                est.add(Calendar.MONTH , projects.get(i).getEstDuration());
                Date dataEstFinal = est.getTime();    //dataEstFinal -> data inicial + estimated duration
                Date dataHoje = currentT.getTime();     

                if(dataHoje.compareTo(dataEstFinal)==1){
                    System.out.printf("\nProjeto: %s", projects.get(i).getName());
                }               
            }
        }
    }
    
    public void showFinishedP(){
        System.out.println("\nPROJETOS ATRASADOS:");
        for(int i=0;i<projects.size();i++){
            if(projects.get(i).getFinalDate()!=null){
                System.out.printf("\nProjeto: %s", projects.get(i).getName());
            }   
        }
    }
    
    public void ReadTasksFromFile(){
        BufferedReader reader;
        
        int i = 0, estDuration = 0;
        String type = "", name = "";
        Date date = new Date(0,0,0);
        
        File f = new File("tasks.txt");
        if(f.exists() && f.isFile()){
            try{
                reader = new BufferedReader(new FileReader(f));
                    String line = reader.readLine();
                    while (line != null) {        
                        String[] tokens = line.split("\t");
                           i=0;
                        for (String t : tokens){
                            i++;
                            switch(i){
                                case 1:
                                    type = t;
                                    break;
                                case 2:
                                    name = t;
                                    break;
                                case 3:
                                    estDuration= Integer.parseInt(t);
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
                        if(type.compareTo("Design")==0){
                            Design task = new Design(name, date, estDuration);
                        }else if(type.compareTo("Development")==0){
                            Development task = new Development(name, date, estDuration);
                        }else if(type.compareTo("Documentation")==0){
                            Documentation task = new Documentation(name, date, estDuration);
                        }
                        tasks.add(task);
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


public void ReadPersonsFromFile(){
        BufferedReader reader;
        
        int i = 0, numMec = 0;
        String type = "", name = "", email = "", area = "";
        
        File personf = new File("Person_list.txt");
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
                                    type = t;
                                    break;
                                case 2:
                                    name = t;
                                    break;
                                case 3:
                                    email= t;
                                    break;
                                case 4:
                                    numMec= Integer.parseInt(t);
                                    break;
                                case 5:
                                    area = t;
                                    break;
                                default:
                                    break;
                            } 
                        }
                        if(type.compareTo("Graduate")==0){
                            Graduate grantee = new Graduate(name, email);
                            grantees.add(grantee);
                        }else if(type.compareTo("Doctorate")==0){
                            Doctorate grantee = new Doctorate(name, email);
                            grantees.add(grantee);
                        }else if(type.compareTo("Master")==0){
                            Master grantee = new Master(name, email);
                            grantees.add(grantee);
                        }else if(type.compareTo("Docent")==0){
                            Docent docent = new Docent(name, email, numMec, area);
                            docents.add(docent);
                        }
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
