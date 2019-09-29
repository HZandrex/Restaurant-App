/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.beans;

/**
 *
 * @author nicoc
 */
public interface BetaalStrategie {
    public abstract double getToegepastePrijs(double prijs);
    public abstract String getPrijsType();
}
