/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.beans;

/**
 *
 * @author nicoc
 */
public class Chef extends ExtraTaak {
    
    public void Commandeer(){
        System.out.println("Chef " + personeel.getNaam() + " commandeert het keukenpersoneel");
    }
    @Override
    public void update() {
       String chefstring = "Chef " + personeel.getNaam() + 
               " heet de " + 
               IngangTeller.getInstance().getAantal() + " welkom!";
       System.out.println(chefstring); 	 
    }
}
