package fr.uvsq.cprog.collex;

public class DnsItem {
    private String nom;
    private String adresseIP;

    public DnsItem(String nom, String adresseIP) {
        this.nom = nom;
        this.adresseIP = adresseIP;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    @Override
    public String toString() {
        return nom + " -> " + adresseIP;
    }
}
