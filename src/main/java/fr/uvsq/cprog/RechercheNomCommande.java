package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.DnsItem;

public class RechercheNomCommande implements Commande {
    private final Dns dns;
    private final String adresseIP;

    public RechercheNomCommande(Dns dns, String adresseIP) {
        this.dns = dns;
        this.adresseIP = adresseIP;
    }

    @Override
    public void execute() {
        DnsItem item = dns.getItemParIP(adresseIP);
        if (item != null) {
            System.out.println(item.getNom());
        } else {
            System.out.println("ERREUR : Adresse IP inconnue");
        }
    }
}
