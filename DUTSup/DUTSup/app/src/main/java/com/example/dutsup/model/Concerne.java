package com.example.dutsup.model;

public class Concerne {
    private int numDomaine;
    private int numFormation;
    //CONSTRUCTOR
    public Concerne(){}
    public Concerne(int numDomaine,int numFormation){
        this.numDomaine=numDomaine;
        this.numFormation=numFormation;
    }
    //GETTER

    public int getNumDomaine() {
        return numDomaine;
    }

    public int getNumFormation() {
        return numFormation;
    }
    //SETTER

    public void setNumFormation(int numFormation) {
        this.numFormation = numFormation;
    }

    public void setNumDomaine(int numDomaine) {
        this.numDomaine = numDomaine;
    }
}
