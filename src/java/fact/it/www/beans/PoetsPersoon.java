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
public class PoetsPersoon extends ExtraTaak {
    //een personeelslid kan naast zijn "standaard" taken nu ook deze taak uitvoeren
    public void schoonMaken(){
        System.out.println("Ik ben " + personeel.getNaam() + " en ik ga nu ook schoonmaken");
    }
    
}

