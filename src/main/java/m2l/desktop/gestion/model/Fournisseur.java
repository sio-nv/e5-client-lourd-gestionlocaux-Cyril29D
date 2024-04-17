package m2l.desktop.gestion.model;

public class Fournisseur {
    protected String nom;
    protected String adresse;
    protected String telephone;

    public Fournisseur(String nom, String adresse, String telephone) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
