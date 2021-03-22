package com.example.dutsup.model;

public class Propose {
    private int numEcole;
    private int numFormation;
    //CONSTRUCTOR
    public Propose(){}
    public Propose(int numEcole,int numFormation){
        this.numEcole=numEcole;
        this.numFormation=numFormation;
    }
    //GETTER

    public int getNumFormation() {
        return numFormation;
    }

    public int getNumEcole() {
        return numEcole;
    }
    //SETTER

    public void setNumFormation(int numFormation) {
        this.numFormation = numFormation;
    }

    public void setNumEcole(int numEcole) {
        this.numEcole = numEcole;
    }
}
