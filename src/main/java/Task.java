import java.util.*;
abstract public class Task extends Project{

    //========================================================================//
    //========================================================================//
    //============================             ===============================//
    //============================  variables  ===============================//
    //============================             ===============================//
    //========================================================================//
    //========================================================================// 
    protected Date initialTDate,finalTDate;
    protected int estTDuration, progress;
    protected double effort;
    protected String Tname;
    Person responsible;
    //========================================================================//
    //========================================================================//
    //===========================                =============================//
    //===========================  Constructors  =============================//
    //===========================                =============================//
    //========================================================================//
    //========================================================================//
    
    
    public Task() {
        this.Tname = null;
        this.initialTDate = null;
        this.finalTDate = null;
        this.estTDuration = 0;
        this.progress = 0;
        
    }
    //========================================================================//
    //========================================================================//
    //=============================           ================================//
    //=============================  methods  ================================//
    //=============================           ================================//
    //========================================================================//
    //========================================================================//
    public double getEffort() {
        return effort;
    }

    public void setEffort(double effort) {
        this.effort = effort;
    }

    public String getTName() {
        return Tname;
    }

    public void setTName(String name) {
        this.Tname = name;
    }
    
    public Date getInitialTDate() {
        return initialTDate;
    }

    public void setInitialTDate(Date initialTDate) {
        this.initialTDate = initialTDate;
    }

    public Date getFinalTDate() {
        return finalTDate;
    }

    public void setFinalTDate(Date finalTDate) {
        this.finalTDate = finalTDate;
    }

    public int getEstTDuration() {
        return estTDuration;
    }

    public void setEstTDuration(int estTDuration) {
        this.estTDuration = estTDuration;
    }

    public int getProgress() {
        return progress;
    }
    
    public void setResponsible(Person responsible){
        this.responsible = responsible ;
    }
    
    public Person getResponsible(){
        return this.responsible;
    }

    public void setProgress(int progress) {
        if (progress>=0 && progress<=100){
            this.progress = progress;
        }else{
            System.out.print("\nO valor excede uma percentagem");
        }
    }
    

    @Override
    public String toString() {
        return "\nTask{" + ", Tname=" + Tname + "initialTDate=" + initialTDate + ", finalTDate=" + finalTDate + ", estTDuration=" + estTDuration + ", progress=" + progress + ", effort=" + effort + '}';
    }
  
    public abstract double effort();

}
