package com.example.dutsup.model;

public class Utilisateur {
    private int numUtilisateur;
    private String nomUtilisateur;
    private String emailUtilisateur;
    //CONSTRUCTOR
    public Utilisateur(){}
    public Utilisateur(String nom,String email){
        this.nomUtilisateur=nom;
        this.emailUtilisateur=email;
    }
    //GETTER
    public String getNomUtilisateur(){
        return nomUtilisateur;
    };
    public String getEmailUtilisateur(){
        return emailUtilisateur;
    };
    public int getNumUtilisateur(){
        return numUtilisateur;
    };
    //SETTER
    public void setNomUtilisateur(String n){
        this.nomUtilisateur=n;
    };
    public void setEmailUtilisateur(String e){
        this.emailUtilisateur=e;
    };

    public void setNumUtilisateur(int numUtilisateur) {
        this.numUtilisateur = numUtilisateur;
    }
}
