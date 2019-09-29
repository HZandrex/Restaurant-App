/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.dao;

import fact.it.www.entity.Keukenpersoneel;
import fact.it.www.entity.Personeel;
import fact.it.www.entity.Zaalpersoneel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nicoc
 */
@Stateless
public class PersoneelFacade extends AbstractFacade<Personeel> {

    @PersistenceContext(unitName = "2APPBIT2_Claes_Nico_restaurant2019PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersoneelFacade() {
        super(Personeel.class);
    }
    
    public Personeel findById(Long id){
        Query q = em.createNamedQuery("Personeel.findById");
 		  q.setParameter("id", id);
        return (Personeel)q.getSingleResult();
    }
    
    public void generateTestData(int count){
        if(count() <= count){
            for(int i=0; i < count/2; i++)
                create(new Zaalpersoneel("zaalpersoneel_" + i));
            
            for(int i=0; i < count/2 + 1; i++)
                create(new Keukenpersoneel("keukenpersoneel_" + i));
        }
    }
}
