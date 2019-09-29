/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.beans.HappyHourBetaling;
import fact.it.www.beans.NormaleBetaling;
import fact.it.www.dao.BestellingFacade;
import fact.it.www.dao.GerechtFacade;
import fact.it.www.dao.PersoneelFacade;
import fact.it.www.entity.BesteldItem;
import fact.it.www.entity.Bestelling;
import fact.it.www.entity.Gerecht;
import fact.it.www.entity.Personeel;
import fact.it.www.entity.Zaalpersoneel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author nicoc
 */
@Named(value = "bestellingController")
@SessionScoped
public class BestellingController implements Serializable {

    /**
     * Creates a new instance of BestellingController
     */
    @EJB
    private GerechtFacade gerechtFacade;
    @EJB
    private BestellingFacade bestellingFacade;
    @EJB
    private PersoneelFacade personeelFacade;
    
    private List<Bestelling> bestellingen;
    
    private Bestelling bestelling;
    
    public BestellingController() {
    }
    
    public String detailsBestelling(Bestelling bestelling){
        this.bestelling = bestelling;
        return "detailsBestelling";
    }
    
    public String bestellenBestelling(Bestelling bestelling){
        
        bestellingFacade.create(bestelling);
        return "startpagina";
    }
    
    public String aanmakenBestelling(Long personeelId){
        Personeel zaalpersoneel = personeelFacade.findById(personeelId);
        bestelling.setZaalpersoneel((Zaalpersoneel)zaalpersoneel);
        GregorianCalendar gc = new GregorianCalendar();
        bestelling.setDatum(gc);
        bestelling.setItemlijst(new ArrayList());
        bestelling.setBetaald(false);
        return "aanmakenBestelling";
    }
    
    public Bestelling getBestelling(){
        return bestelling;
    }
    
    public String zoekenOpDag(Date datum){
        bestellingen = bestellingFacade.zoekOpDag(datum);
        return "zoekResultaatBestellingen";
    }
    
    public String zoekenOpMaand(Date datum){
        bestellingen = bestellingFacade.zoekOpMaand(datum);
        return "zoekResultaatBestellingen";
    }
    
    public String zoekenOpJaar(Date datum){
        bestellingen = bestellingFacade.zoekOpJaar(datum);
        return "zoekResultaatBestellingen";
    }
    
    public String zoekenOpTafelCode(String code){
        bestellingen = bestellingFacade.zoekOpTafelCode(code);
        return "zoekResultaatBestellingen";
    }
    
    public List<Bestelling> getBestellingen(){
        return bestellingen;
    }
    
    public List<Bestelling> getAllBestellingen(){
        return bestellingFacade.findAll();
    }
    
    public String testStrategyPatroon() {
        System.out.println("####################################################################");
        //Betalingstrategieën aanmaken
        HappyHourBetaling happyHourBetaling = new HappyHourBetaling();
        NormaleBetaling normaleBetaling = new NormaleBetaling();
        //gerechten aanmaken
        Gerecht videe = new Gerecht();
        videe.setNaam("Vidée met frietjes");
        videe.setActuelePrijs(15.0);
        Gerecht croque = new Gerecht();
        croque.setNaam("Croque Monsieur");
        croque.setActuelePrijs(10.0);
        gerechtFacade.create(croque);
        gerechtFacade.create(videe);

        //maak bestelling met bestelitems
        Bestelling bestelling = new Bestelling();
        //NORMAAL
        bestelling.setBetaalStrategie(normaleBetaling);
        bestelling.setDatum(new GregorianCalendar());
        bestelling.addItem(videe, 2);
        bestelling.addItem(croque, 2);

        //HAPPYHOUR
        bestelling.setBetaalStrategie(happyHourBetaling);
        bestelling.addItem(videe, 2);
        bestelling.addItem(croque, 2);

        //NORMAAL
        bestelling.setBetaalStrategie(normaleBetaling);
        bestelling.addItem(videe, 2);
        bestelling.addItem(croque, 2);

        bestelling.maakRekening();
        System.out.println("####################################################################");
        
        //persistent maken
        this.bestellingFacade.create(bestelling);

        return "index";
    }
}
