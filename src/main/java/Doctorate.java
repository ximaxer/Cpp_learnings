/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author ximax
 */
public class Doctorate extends Grantee{
    //========================================================================//
    //========================================================================//
    //============================             ===============================//
    //============================  variables  ===============================//
    //============================             ===============================//
    //========================================================================//
    //========================================================================//
    
    
    //========================================================================//
    //========================================================================//
    //===========================                =============================//
    //===========================  Constructors  =============================//
    //===========================                =============================//
    //========================================================================//
    //========================================================================//  
    public Doctorate(){
        super.name=null;
        super.email=null;
        cost();
    }
    
    public Doctorate(String name, String email, Date idate, Date fdate){
        super.name=name;
        super.email=email;
        super.initialDate=idate;
        super.finalDate=fdate;
        cost();
    }
    
    //========================================================================//
    //========================================================================//
    //=============================           ================================//
    //=============================  methods  ================================//
    //=============================           ================================//
    //========================================================================//
    //========================================================================//

    @Override
    public void cost(){
        super.Salary=1200.00;
    }
}
