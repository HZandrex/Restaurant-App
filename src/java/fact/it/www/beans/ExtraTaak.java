/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.beans;

import fact.it.www.entity.Personeel;

/**
 *
 * @author nicoc
 */
public abstract class ExtraTaak extends Personeel{
    protected Personeel personeel;

   @Override
   public void update(){
     personeel.update();
   }

    public void setPersoneel(Personeel personeel) {
        this.personeel = personeel;
    }
}

