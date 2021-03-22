package com.example.dutsup.model;

public class Domaine {
    private int numDomaine;
    private String nomDomaine;
    private String descriptifDomaine;
    //CONSTRUCTOR
    public Domaine(){}
    public Domaine(String nom, String descriptif){
        super();
        this.nomDomaine=nom;
        this.descriptifDomaine=descriptif;
    }
    //GETTER
    public int getNumDomaine() {
        return numDomaine;
    }
    public String getDescriptifDomaine() {
        return descriptifDomaine;
    }
    public String getNomDomaine() {
        return nomDomaine;
    }
    //SETTER
    public void setDescriptifDomaine(String descriptifDomaine) {
        this.descriptifDomaine = descriptifDomaine;
    }
    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }

    public void setNumDomaine(int numDomaine) {
        this.numDomaine = numDomaine;
    }
}
