import java.util.*;
import java.io.*;
import java.text.*;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class InvestigationCenter {
    //========================================================================//
    //========================================================================//
    //============================             ===============================//
    //============================  variables  ===============================//
    //============================             ===============================//
    //========================================================================//
    //========================================================================//
    private ArrayList<Project> projects = new ArrayList<Project>(); 
    private ArrayList<Docent> docents = new ArrayList<Docent>();
    private ArrayList<Grantee> grantees = new ArrayList<Grantee>();
    
    
    
    
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

public void ReadPersonsFromFile(){
    BufferedReader reader;

    int i = 0, numMec = 0;
    Date idate=null, fdate=null;
    String type = "", name = "", email = "", area = "";

    File personf = new File("C:\\Users\\ximax\\Desktop\\college gomin\\POO\\ProjetoPoo\\Person_list.txt");
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
                                if(type.compareTo("Docent")==0){
                                    numMec= Integer.parseInt(t);
                                }else{
                                    try{
                                        idate=new SimpleDateFormat("dd/MM/yyyy").parse(t);
                                    }catch(ParseException e){
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            case 5:
                                if(type.compareTo("Docent")==0){
                                area = t;
                                }else{
                                    try{
                                        fdate=new SimpleDateFormat("dd/MM/yyyy").parse(t);
                                    }catch(ParseException e){
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            default:
                                break;
                        } 
                    }
                    if(type.compareTo("Graduate")==0){
                        Graduate grantee = new Graduate(name, email, idate, fdate);
                        grantees.add(grantee);
                    }else if(type.compareTo("Doctorate")==0){
                        Doctorate grantee = new Doctorate(name, email, idate, fdate);
                        grantees.add(grantee);
                    }else if(type.compareTo("Master")==0){
                        Master grantee = new Master(name, email, idate, fdate);
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
                for(i=0; i<grantees.size();i++){
        System.out.printf("\nPerson %d:%s ", i+1, grantees.get(i).getName()); 
        }
        }catch(IOException e){
            e.printStackTrace();
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
        InvestigationCenter IC = new InvestigationCenter();
        IC.ReadProjectFromFile();
        IC.ReadPersonsFromFile();
        for(int i=0;i<IC.projects.size();i++){
            IC.projects.get(i).ReadTasksFromFile();
        }
        
        JFrame frame = new JFrame("landingScreen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new landingScreen(IC.projects, IC.grantees, IC.docents);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
            //frame.setSize(600,600);// x, y
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setVisible(true);
    }
    
}