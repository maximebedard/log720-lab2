package ca.etsmtl.log720.lab2;

import java.util.HashMap;

public class Dossier {
    private final String nom;
    private final String prenom;
    private final String noPlaque;
    private final String noPermis;

    public Dossier(String nom, String prenom, String noPlaque, String noPermis) {
        this.nom = nom;
        this.prenom = prenom;
        this.noPlaque = noPlaque;
        this.noPermis = noPermis;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getNoPlaque() {
        return noPlaque;
    }

    public String getNoPermis() {
        return noPermis;
    }


    private static HashMap<String, Dossier> dossiers = new HashMap<>();
    static {
        dossiers.put("1", new Dossier("Lemieux", "Henry", "123123", "123123"));
    }

    public static Dossier getDossier(String id) {
        return dossiers.get(id);
    }

}
