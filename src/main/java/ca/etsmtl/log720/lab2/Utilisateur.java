package ca.etsmtl.log720.lab2;

import java.io.Serializable;

public class Utilisateur implements Serializable {
    public Utilisateur(){}

    private int id;
    private String nom;


    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }


    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }


}
