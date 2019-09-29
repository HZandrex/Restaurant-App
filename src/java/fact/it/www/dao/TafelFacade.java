/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.dao;

import fact.it.www.entity.Tafel;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nicoc
 */
@Stateless
public class TafelFacade extends AbstractFacade<Tafel> {

    @PersistenceContext(unitName = "2APPBIT2_Claes_Nico_restaurant2019PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TafelFacade() {
        super(Tafel.class);
    }
    
    public void generateTestData(int count){
        if(count() <= count){
            for(int i=0; i < count; i++){
                Tafel tafel = new Tafel();
                tafel.setCode("tafel_" + i);
                create(tafel);
            }
        }
    }
}
