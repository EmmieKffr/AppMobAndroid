package com.example.dutsup.model;

public class Formation {
    private int numFormation;
    private String nomFormation;
    private String descriptifFormation;
    //CONSTRUCTOR
    public Formation(){}
    public Formation(String nom,String descriptif){
        super();
        this.nomFormation=nom;
        this.descriptifFormation=descriptif;
    }
    //GETTER
    public int getNumFormation() {
        return numFormation;
    }
    public String getDescriptifFormation() {
        return descriptifFormation;
    }
    public String getNomFormation() {
        return nomFormation;
    }
    //SETTER
    public void setDescriptifFormation(String descriptifFormation) {
        this.descriptifFormation = descriptifFormation;
    }
    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public void setNumFormation(int numFormation) {
        this.numFormation = numFormation;
    }
}
