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
public class HappyHourBetaling implements BetaalStrategie {
    @Override
    public double getToegepastePrijs(double prijs){
        return prijs*0.8;
    }

    @Override
    public String getPrijsType() {
        return "Happy Hour betaling";
    }
    
}

