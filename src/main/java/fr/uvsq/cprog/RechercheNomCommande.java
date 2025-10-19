package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.NomMachine;
import fr.uvsq.cprog.collex.AdresseIP;

public class RechercheNomCommande implements Commande {
    private final Dns dns;
    private final AdresseIP ip;

    public RechercheNomCommande(Dns dns, AdresseIP ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public void execute() {
        NomMachine nom = dns.getNomParIp(ip);
        if (nom != null) {
            System.out.println(nom);
        } else {
            System.out.println("Adresse IP non trouvée : " + ip);
        }
    }
}
