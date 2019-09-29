/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author nicoc
 */
@Entity
public class BesteldItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int aantal;
    private double toegepastePrijs;
    
    @ManyToOne
    private Bestelling bestelling;
    @ManyToOne
    private Gerecht gerecht;

    public BesteldItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public double getToegepastePrijs() {
        return toegepastePrijs;
    }

    public void setToegepastePrijs(double toegepastePrijs) {
        this.toegepastePrijs = toegepastePrijs;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public Gerecht getGerecht() {
        return gerecht;
    }

    public void setGerecht(Gerecht gerecht) {
        this.gerecht = gerecht;
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
        if (!(object instanceof BesteldItem)) {
            return false;
        }
        BesteldItem other = (BesteldItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fact.it.www.entity.BesteldItem[ id=" + id + " ]";
    }
    
}
