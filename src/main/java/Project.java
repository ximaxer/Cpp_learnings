import java.io.*;
import java.text.*;
import java.util.*;
public class Project extends InvestigationCenter {
    //========================================================================//
    //========================================================================//
    //============================             ===============================//
    //============================  variables  ===============================//
    //============================             ===============================//
    //========================================================================//
    //========================================================================//  
    protected ArrayList<Docent> docents = new ArrayList<Docent>();
    protected ArrayList<Grantee> grantees = new ArrayList<Grantee>();
    protected ArrayList<Task> tasks = new ArrayList<Task>();
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

    public Project(String name,String acronym,Date initDate,int estDuration){
        this.name=name;
        this.acronym=acronym;
        this.initialDate=initDate;
        this.estDuration=estDuration;
        this.finalDate=null;
        this.projectState=true;

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
    
        
    public void ReadTasksFromFile(){
        BufferedReader reader;
        
        int i = 0, estDuration = 0;
        String type = "", name = "";
        Date date = new Date(0,0,0);
        
        File f = new File("C:\\Users\\ximax\\Desktop\\college gomin\\POO\\ProjetoPoo\\Task_list.txt");
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
                            tasks.add(task);
                        }else if(type.compareTo("Development")==0){
                            Development task = new Development(name, date, estDuration);
                            tasks.add(task);
                        }else if(type.compareTo("Documentation")==0){
                            Documentation task = new Documentation(name, date, estDuration);
                            tasks.add(task);
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




    
    
    public void deleteTask(int taskI){
        tasks.remove(taskI);
        System.out.printf("\nTask %d deleted with success!", taskI); 
    }
    public void showTaskList(){
        System.out.println("\nLISTING TASKS!");
        for(int i=0; i<tasks.size();i++){
        System.out.printf("\nTask %d:%s ", i+1, tasks.get(i).getTName()); 
        }
    }
    public void showUnitializedTasks(){
        System.out.println("\nLISTING UNITIALIZED TASKS");
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
        for(int j=0;j<tasks.size();j++){
           if(grantees.get(i).name.compareTo(tasks.get(j).responsible.name)==0){
            cost+=grantees.get(i).Salary*tasks.get(j).getEstTDuration()*tasks.get(j).getEffort();
            }
        }
    }
    System.out.printf("\nProject %s cost:%f",getName(),cost);
    }
    public void terminateProject(){
        this.projectState=false;
    }
}
