package ca.etsmtl.log720.lab2;

import java.util.HashMap;

public class Infraction {

    private final String description;
    private final int gravite;

    public Infraction(String description, int gravite) {
        this.description = description;
        this.gravite = gravite;
    }

    public int getGravite() {
        return gravite;
    }

    public String getDescription() {
        return description;
    }

    private static HashMap<String, Infraction> infractions = new HashMap<>();
    static {
        infractions.put("1", new Infraction("Délit de fuite", 50));
    }

    public static Infraction getInfraction(String id) {
        return infractions.get(id);
    }
}
