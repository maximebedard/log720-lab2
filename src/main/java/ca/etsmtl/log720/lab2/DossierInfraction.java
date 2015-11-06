package ca.etsmtl.log720.lab2;

import java.io.Serializable;

/**
 * Created by ALEXANDRE on 2015-11-06.
 */
public class DossierInfraction extends Infraction {
    public DossierInfraction(){}

    public DossierInfraction(Integer id_dossierInfraction) {
      this.id_dossierInfraction = id_dossierInfraction;
    }

    private Integer id_dossierInfraction;

    public Integer getId_dossierInfraction() {
      return id_dossierInfraction;
    }

    public void setId_dossierInfraction(Integer id_dossierInfraction) {
      this.id_dossierInfraction = id_dossierInfraction;
    }

}
