/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.dao;

import fact.it.www.beans.HappyHourBetaling;
import fact.it.www.beans.NormaleBetaling;
import fact.it.www.entity.Bestelling;
import fact.it.www.entity.Gerecht;
import fact.it.www.entity.Tafel;
import fact.it.www.entity.Zaalpersoneel;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nicoc
 */
@Stateless
public class BestellingFacade extends AbstractFacade<Bestelling> {

    @EJB
    private GerechtFacade gerechtFacade;
    @EJB
    private ZaalpersoneelFacade zaalpersoneelFacade;
    @EJB
    private TafelFacade tafelFacade;
            
    @PersistenceContext(unitName = "2APPBIT2_Claes_Nico_restaurant2019PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BestellingFacade() {
        super(Bestelling.class);
    }
    
    public List<Bestelling> zoekOpDag(Date datum){
        Query q = em.createNamedQuery("Bestelling.zoekenOpDag");
 		  q.setParameter("dag", datum.getDate());
		  q.setParameter("maand", datum.getMonth() + 1);
 		  q.setParameter("jaar", 1900 + datum.getYear());
        return q.getResultList();
    }
    
    public List<Bestelling> zoekOpMaand(Date datum){
        Query q = em.createNamedQuery("Bestelling.zoekenOpMaand");
		  q.setParameter("maand", datum.getMonth() + 1);
 		  q.setParameter("jaar", 1900 + datum.getYear());
        return q.getResultList();
    }
    
    public List<Bestelling> zoekOpJaar(Date datum){
        Query q = em.createNamedQuery("Bestelling.zoekenOpJaar");
 		  q.setParameter("jaar", 1900 + datum.getYear());
        return q.getResultList();
    }
    
    public List<Bestelling> zoekOpTafelCode(String code){
        Query q = em.createNamedQuery("Bestelling.zoekenOpTafelCode");
 		  q.setParameter("tafelCode", code);
        return q.getResultList();
    }
    
    public void generateTestData(int bestellingAantal, int maxAantalBestellingItemsPerBestelling){
        Random rnd = new Random();
        List<Gerecht> gerechten = gerechtFacade.findAll();
        List<Zaalpersoneel> zaalpersoneelsleden = zaalpersoneelFacade.findAll();
        List<Tafel> tafels = tafelFacade.findAll();
        
        if(count() <= bestellingAantal){
            
            for(int i=0; i < bestellingAantal; i++){
                Bestelling bestelling = new Bestelling();
                HappyHourBetaling happyHourBetaling = new HappyHourBetaling();
                NormaleBetaling normaleBetaling = new NormaleBetaling();
                int zaalpersoneelIndex = rnd.nextInt(zaalpersoneelFacade.count());
                int tafelIndex = rnd.nextInt(tafelFacade.count());
                int gerechtIndex = rnd.nextInt(gerechtFacade.count());
                int bestellingItemAantal = 1 + rnd.nextInt(maxAantalBestellingItemsPerBestelling);
                GregorianCalendar gc = new GregorianCalendar();
                gc.set(gc.YEAR, randBetween(2000, 2019));
                gc.set(gc.DAY_OF_YEAR, randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR)));
                
                
                bestelling.setZaalpersoneel(zaalpersoneelsleden.get(zaalpersoneelIndex));
                bestelling.setTafel(tafels.get(tafelIndex));
                bestelling.setDatum(gc);
                bestelling.setBetaald(true);
                
                if(rnd.nextBoolean())
                      bestelling.setBetaalStrategie(happyHourBetaling);
                else
                      bestelling.setBetaalStrategie(normaleBetaling);
                    
                for(int j=0; j < bestellingItemAantal; j++){
                    while(bestelling.isGerechtToegevoegd(gerechten.get(gerechtIndex)))
                        gerechtIndex = rnd.nextInt(gerechtFacade.count());
                    
                    bestelling.addItem(gerechten.get(gerechtIndex), 1 + rnd.nextInt(4));
                }
                
                create(bestelling);
            }
        }
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
