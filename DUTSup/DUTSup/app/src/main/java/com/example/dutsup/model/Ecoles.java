package com.example.dutsup.model;

public class Ecoles {
    private int numEcole;
    private String nomEcole;
    private String logoEcole;
    private int numType;
    //CONSTRUCTOR
    public Ecoles(){}
    public Ecoles(String nom, String logo, int numType){
        super();
        this.nomEcole=nom;
        this.logoEcole=logo;
        this.numType=numType;
    }
    //GETTER
    public int getNumEcole() {
        return numEcole;
    }
    public String getLogoEcole() {
        return logoEcole;
    }
    public String getNomEcole() {
        return nomEcole;
    }
    public int getNumType() { return numType; }

    //SETTER

    public void setNumEcole(int numEcole) { this.numEcole = numEcole; }
    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }
    public void setLogoEcole(String logoEcole) {
        this.logoEcole = logoEcole;
    }
    public void setNumType(int numType) { this.numType = numType; }
}
