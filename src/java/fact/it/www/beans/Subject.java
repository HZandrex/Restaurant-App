/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.beans;

import fact.it.www.entity.Personeel;
import java.util.ArrayList;

/**
 *
 * @author nicoc
 */
public abstract class Subject {
    private ArrayList<Personeel> observers = new ArrayList<>();
    public Subject() {
    } 
    public void attachObserver(Personeel obs){
          observers.add(obs);
    }
    public void detachObserver(Personeel obs){
          observers.remove(obs);
    }
    public void notifyObservers(){
          for(Personeel o:observers){
                  o.update();
          }
    }
}
