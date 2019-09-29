/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.dao.IngredientFacade;
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
@Named(value = "ingredientController")
@SessionScoped
public class IngredientController implements Serializable {
    @EJB
    private IngredientFacade ingredientFacade;
    /**
     * Creates a new instance of IngredientController
     */
    public IngredientController() {
    }
    
    public List<Ingredient> findAll(){
        return ingredientFacade.findAll();
    }
    
}
