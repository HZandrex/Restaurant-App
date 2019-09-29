/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.entity;

import fact.it.www.beans.IngangTeller;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author nicoc
 */
@Entity
public class Zaalpersoneel extends Personeel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Zaalpersoneel() {
    }

    public Zaalpersoneel(String naam) {
        setNaam(naam);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getType() {
        return "Zaal";
    }
    
    //doe hier iets als er klanten binnenkomen
    @Override
    public void update() {
       String zaalstring = "Ik ben " + getNaam() + 
          " en ga het nodige doen om voor " + 
               IngangTeller.getInstance().getAantal() + 
               " klanten een tafel klaar te maken.";
       System.out.println(zaalstring); 	 
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
        if (!(object instanceof Zaalpersoneel)) {
            return false;
        }
        Zaalpersoneel other = (Zaalpersoneel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fact.it.www.entity.Zaakpersoneel[ id=" + id + " ]";
    }
    
}
