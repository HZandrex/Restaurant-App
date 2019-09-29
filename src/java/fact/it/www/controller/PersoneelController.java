/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.beans.Administrator;
import fact.it.www.beans.Chef;
import fact.it.www.beans.IngangTeller;
import fact.it.www.beans.PoetsPersoon;
import fact.it.www.dao.BestellingFacade;
import fact.it.www.dao.GerechtFacade;
import fact.it.www.dao.PersoneelFacade;
import fact.it.www.dao.TafelFacade;
import fact.it.www.entity.Keukenpersoneel;
import fact.it.www.entity.Personeel;
import fact.it.www.entity.Zaalpersoneel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author nicoc
 */
@Named(value = "personeelController")
@SessionScoped
public class PersoneelController implements Serializable {

    /**
     * Creates a new instance of PersoneelController
     */
    @EJB
    private PersoneelFacade personeelFacade;
    @EJB
    private TafelFacade tafelFacade;
    @EJB
    private GerechtFacade gerechtFacade;
    @EJB
    private BestellingFacade bestellingFacade;
    private Personeel personeel;
    private List<Personeel> personeelLijst;
    
    public PersoneelController() {
    }
    
    public List<Personeel> getPersoneelsleden() {
        return this.personeelFacade.findAll();
    }

    public Personeel getPersoneel() {
        return personeel;
    }
    
    public String startPagina(){
        personeelFacade.generateTestData(5);
        gerechtFacade.generateTestData(5);
        tafelFacade.generateTestData(5);
        bestellingFacade.generateTestData(5, 5);
        return "startpagina";
    }
    
    public String testSingletonPatroon() {
        System.out.println("####################################################################");
        IngangTeller it1  = IngangTeller.getInstance();
        IngangTeller it2; 
        it2 = IngangTeller.getInstance();
        if (it1 == it2) {
            System.out.println("De twee singletonvariabelen verwijzen naar hetzelfde object.");
        } else {
            System.out.println("Dit kan in principe niet.");
        }
        System.out.println("####################################################################");
        return "index";
    }
    
    public String testObserverPatroonExamen(){
        System.out.println("####################################################################");
        IngangTeller ingangTeller = IngangTeller.getInstance();
        Keukenpersoneel jef = new Keukenpersoneel("Jef");
        Chef chef = new Chef();
        chef.setPersoneel(jef);
        System.out.println("Na het decoreren van Jef met de verantwoordelijkheid Chef");
        chef.Commandeer();
        ingangTeller.attachObserver(chef);
        System.out.println("Na het toevoegen van Jef als observer en het veranderen van de ingangteller naar 7");
        ingangTeller.setAantal(7);
        System.out.println("####################################################################");
        
        // jef opslaan in database
        personeelFacade.create(jef);
        
        return "index";
    }
    
    public String testObserverPatroon() {
        IngangTeller klantTeller = IngangTeller.getInstance();

        //een paar personeelsleden
        Zaalpersoneel jan = new Zaalpersoneel("Jan");
        Zaalpersoneel piet = new Zaalpersoneel("Piet");
        Keukenpersoneel serge = new Keukenpersoneel("Serge");
        Keukenpersoneel jeroen = new Keukenpersoneel("Jeroen");

        //we koppelen de spelers en scheidsrechter als observer aan de bal
        klantTeller.attachObserver(jan);
        klantTeller.attachObserver(piet);
        klantTeller.attachObserver(serge);
        klantTeller.attachObserver(jeroen);
        this.personeelFacade.create(jan);
        this.personeelFacade.create(piet);
        this.personeelFacade.create(serge);
        this.personeelFacade.create(jeroen);

        System.out.println("####################################################################");
        System.out.println("Na het toevoegen van de observers...");
        //bal van positie veranderen
        klantTeller.setAantal(5);
        //lege lijn
        System.out.println();
        //we doen enkele observers weg
        klantTeller.detachObserver(piet);
        klantTeller.detachObserver(serge);

        System.out.println("Na het ontkoppelen van Piet en Serge ...");
        //we veranderen de bal weer van positie
        klantTeller.setAantal(3);
        System.out.println("####################################################################");
        return "index";
    }
    
    public String testDecoratorPatroon() {
        IngangTeller ingangTeller = IngangTeller.getInstance();
        // een nieuw zaalpersoneelslid toevoegen   
        System.out.println("####################################################################");
        Zaalpersoneel manu = new Zaalpersoneel("Manu");
        ingangTeller.attachObserver(manu);
        ingangTeller.setAantal(7);
        // we gaan manu detachen en hem als poetspersoon attachen zodat hij nog altijd kan reageren op de klantenteller maar daarbij ook kan schoonmaken
        System.out.println("####################################################################");
        ingangTeller.detachObserver(manu);
        ingangTeller.setAantal(10);
        PoetsPersoon poetsPersoon = new PoetsPersoon();
        poetsPersoon.setPersoneel(manu);
        poetsPersoon.schoonMaken();
        // Manu moet nu ook nog de administratie erbij nemen als iemand binnenkomt
        System.out.println("####################################################################");
        Administrator administrator = new Administrator();
        administrator.setPersoneel(manu);
        ingangTeller.attachObserver(administrator);
        ingangTeller.setAantal(5);
        System.out.println("####################################################################");

        return "index";
    }
}
