/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.dao.GerechtFacade;
import fact.it.www.dao.IngredientFacade;
import fact.it.www.entity.Gerecht;
import fact.it.www.entity.Ingredient;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author nicoc
 */
@Named(value = "gerechtController")
@SessionScoped
public class GerechtController implements Serializable {

    /**
     * Creates a new instance of GerechtController
     */
    @EJB
    private GerechtFacade gerechtFacade;
    @EJB
    private IngredientFacade ingredientFacade;
    
    private Gerecht gerecht;
    private List<Gerecht> gerechten;

    public Gerecht getGerecht() {
        return gerecht;
    }

    public List<Gerecht> getGerechten() {
        return gerechten;
    }
    
    public List<Gerecht> findAll() {
        return gerechtFacade.findAll();
    }
    
    public GerechtController() {
    }
    
    public String aanvullenDatabank(){
        gerechtFacade.generateTestData(2);
        ingredientFacade.generateTestData();
        
        return "index";
    }
    public String voegIngredientToe(Ingredient ingredient){
        gerecht.addIngredient(ingredient);
        gerechtFacade.edit(gerecht);
        return "samenstelpagina";
    }
    
    public String geefSamenstelling(Gerecht gerecht){
        this.gerecht = gerecht;
        return "samenstelpagina";
    }
    
    public String toonAllergenenkaart(){
        gerechten = this.findAll();
        return "toonAllergenenkaart";
    }
    public String zoekenOpNaam(String naam){
        gerechten = gerechtFacade.zoekOpNaam(naam);
        return "toonAllergenenkaart";
    }
    
}
