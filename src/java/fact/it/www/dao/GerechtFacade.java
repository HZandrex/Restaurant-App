/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.dao;

import fact.it.www.entity.Gerecht;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nicoc
 */
@Stateless
public class GerechtFacade extends AbstractFacade<Gerecht> {

    @PersistenceContext(unitName = "2APPBIT2_Claes_Nico_restaurant2019PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GerechtFacade() {
        super(Gerecht.class);
    }
    
    public void generateTestData(int count){
        Random rnd = new Random();
        
        for(int i=0; i < count; i++){
            Gerecht gerecht = new Gerecht();
            gerecht.setNaam("gerecht_" + i);
            gerecht.setActuelePrijs(15 + rnd.nextInt(10));
            gerecht.setIngredienten(new ArrayList<>());
            create(gerecht);
        }
    }
    
    public List<Gerecht> zoekOpNaam(String naam){
        Query q = em.createNamedQuery("Gerecht.zoekenOpNaam");
		  q.setParameter("naam", "%" + naam + "%");
        return q.getResultList();
    }
    
}
