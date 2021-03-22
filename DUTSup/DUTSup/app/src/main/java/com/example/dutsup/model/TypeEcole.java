package com.example.dutsup.model;

public class TypeEcole {
    private int numType;
    private String nomType;
    //CONSTRUCTOR
    public TypeEcole(){}
    public TypeEcole(String nom){
        super();
        this.nomType=nom;
    }
    //GETTER
    public int getNumType() {
        return numType;
    }
    public String getNomType() {
        return nomType;
    }
    //SETTER
    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public void setNumType(int numType) {
        this.numType = numType;
    }
}
