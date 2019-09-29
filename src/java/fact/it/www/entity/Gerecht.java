/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author nicoc
 */
@NamedQueries({
    @NamedQuery(
            name = "Gerecht.zoekenOpNaam",
            query = "select g from Gerecht g where g.naam like :naam"
    )
})

@Entity
public class Gerecht implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String naam;
    private double actuelePrijs;
    @ManyToMany
    @JoinTable(name="ingredientingerecht") 
    private List<Ingredient> ingredienten = new ArrayList<>();

    public Gerecht() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getActuelePrijs() {
        return actuelePrijs;
    }

    public void setActuelePrijs(double actuelePrijs) {
        this.actuelePrijs = actuelePrijs;
    }

    public List<Ingredient> getIngredienten() {
        return ingredienten;
    }

    public void setIngredienten(List<Ingredient> ingredienten) {
        this.ingredienten = ingredienten;
    }
    
    public void addIngredient(Ingredient ingredient){
        ingredienten.add(ingredient);
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
        if (!(object instanceof Gerecht)) {
            return false;
        }
        Gerecht other = (Gerecht) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fact.it.www.entity.Gerecht[ id=" + id + " ]";
    }
    
}
