import java.util.*;
/**
 *
 * @author ximax
 */
abstract public class Grantee extends Person{

    
    //========================================================================//
    //========================================================================//
    //============================             ===============================//
    //============================  variables  ===============================//
    //============================             ===============================//
    //========================================================================//
    //========================================================================//  
    protected Date initialDate, finalDate;
    //========================================================================//
    //========================================================================//
    //===========================                =============================//
    //===========================  Constructors  =============================//
    //===========================                =============================//
    //========================================================================//
    //========================================================================//
    public Grantee() {
        this.initialDate = null;
        this.finalDate = null;
    }
    
    public Grantee(String name, String email, Date initialDate, Date finalDate) {
        super.name = name;
        super.email = email;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }
    
    //========================================================================//
    //========================================================================//
    //=============================           ================================//
    //=============================  methods  ================================//
    //=============================           ================================//
    //========================================================================//
    //========================================================================// 
    
    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}
