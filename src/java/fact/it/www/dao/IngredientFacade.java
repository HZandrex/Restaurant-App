/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.dao;

import fact.it.www.entity.Ingredient;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nicoc
 */
@Stateless
public class IngredientFacade extends AbstractFacade<Ingredient> {

    @PersistenceContext(unitName = "2APPBIT2_Claes_Nico_restaurant2019PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngredientFacade() {
        super(Ingredient.class);
    }
    
    public void generateTestData(){
        
        for(int i=0; i < 2; i++){
            Ingredient ingredient = new Ingredient();
            ingredient.setAllergeen(true);
            ingredient.setNaam("ingredientA_"+i);
            ingredient.setGerechten(new ArrayList<>());
            create(ingredient);
        }
        for(int i=0; i < 3; i++){
            Ingredient ingredient = new Ingredient();
            ingredient.setAllergeen(false);
            ingredient.setNaam("ingredient_"+i);
            ingredient.setGerechten(new ArrayList<>());
            create(ingredient);
        }
    }
    
}
