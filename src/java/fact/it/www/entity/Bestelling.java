/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.entity;

import fact.it.www.beans.BetaalStrategie;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author nicoc
 */
@NamedQueries({
    @NamedQuery(
            name = "Bestelling.zoekenOpDag",
            query = "select b from Bestelling b where "
                    + "EXTRACT(day from b.datum) = :dag and "
                    + "EXTRACT(month from b.datum) = :maand and "
                    + "EXTRACT(year from b.datum) = :jaar"
    ),
    @NamedQuery(
            name = "Bestelling.zoekenOpMaand",
            query = "select b from Bestelling b where "
                    + "EXTRACT(month from b.datum) = :maand and "
                    + "EXTRACT(year from b.datum) = :jaar"
    ), 
    @NamedQuery(
            name = "Bestelling.zoekenOpJaar",
            query = "select b from Bestelling b where "
                    + "EXTRACT(year from b.datum) = :jaar"
    ), 
    @NamedQuery(
            name = "Bestelling.zoekenOpTafelCode",
            query = "select b from Bestelling b where b.tafel.code =:tafelCode"
    )
})
@Entity
public class Bestelling implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private GregorianCalendar datum;
    private boolean betaald;
    @ManyToOne
    private Zaalpersoneel zaalpersoneel;
    @ManyToOne
    private Tafel tafel;
    @OneToMany(mappedBy="bestelling", cascade = CascadeType.PERSIST)
    private List<BesteldItem> itemlijst = new ArrayList<>();
    
    @Transient
    private BetaalStrategie betaalStrategie;

    public Bestelling() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatum() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        fmt.setCalendar(this.datum);
        String dateFormatted = fmt.format(this.datum.getTime());
        return dateFormatted;
    }

    public void setDatum(GregorianCalendar datum) {
        this.datum = datum;
    }

    public boolean getBetaald() {
        return betaald;
    }

    public void setBetaald(boolean betaald) {
        this.betaald = betaald;
    }

    public Zaalpersoneel getZaalpersoneel() {
        return zaalpersoneel;
    }

    public void setZaalpersoneel(Zaalpersoneel zaalpersoneel) {
        this.zaalpersoneel = zaalpersoneel;
    }

    public Tafel getTafel() {
        return tafel;
    }

    public void setTafel(Tafel tafel) {
        this.tafel = tafel;
    }

    public List<BesteldItem> getItemlijst() {
        return itemlijst;
    }

    public void setItemlijst(List<BesteldItem> itemlijst) {
        this.itemlijst = itemlijst;
    }

    public BetaalStrategie getBetaalStrategie() {
        return betaalStrategie;
    }

    public void setBetaalStrategie(BetaalStrategie betaalStrategie) {
        this.betaalStrategie = betaalStrategie;
    }
    
    public String isBetaald(){
        if(betaald)
            return "Ja";
        else
            return "Nee";
    }
    
    public boolean isGerechtToegevoegd(Gerecht gerecht){
        for(Iterator<BesteldItem> i = itemlijst.iterator(); i.hasNext();){
            BesteldItem besteldItem = i.next();
            
            if(besteldItem.getGerecht() == gerecht)
                return true;
        }
        return false;
    }
    
    //besteldItem maken in controller dan daar opslaan in database en hier doorgeven
    public void addItem(Gerecht gerecht, int aantal){
       BesteldItem besteldItem = new BesteldItem();
       besteldItem.setAantal(aantal);
       besteldItem.setGerecht(gerecht);
       besteldItem.setBestelling(this);
       besteldItem.setToegepastePrijs(betaalStrategie.getToegepastePrijs(gerecht.getActuelePrijs()));
       itemlijst.add(besteldItem);
    } 

     public void maakRekening(){
       double sum = 0;
       for (BesteldItem bi : itemlijst) {
          sum += bi.getAantal() * bi.getToegepastePrijs();
          System.out.println(bi.getAantal() + " " + bi.getGerecht().getNaam() + " prijs " + bi.getAantal() * bi.getToegepastePrijs());
       }
       System.out.println("-----------------------------------");        
       System.out.println("Totaal: " + sum);
       System.out.println("-----------------------------------");  
       System.out.println("Op dit moment is het volgende betaaltype van toepassing: " + betaalStrategie.getPrijsType());  

    }
      
    public double toonRekening(){
        double sum = 0;
        for (BesteldItem bi : itemlijst) {
           sum += bi.getAantal() * bi.getToegepastePrijs();
           System.out.println(bi.getAantal() + " " + bi.getGerecht().getNaam() + " prijs " + bi.getAantal() * bi.getToegepastePrijs());
        }
        return sum;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bestelling)) {
            return false;
        }
        Bestelling other = (Bestelling) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fact.it.www.entity.Bestelling[ id=" + id + " ]";
    }
    
}
