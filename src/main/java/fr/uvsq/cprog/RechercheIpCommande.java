package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.NomMachine;
import fr.uvsq.cprog.collex.AdresseIP;

public class RechercheIpCommande implements Commande {
    private final Dns dns;
    private final NomMachine nom;

    public RechercheIpCommande(Dns dns, NomMachine nom) {
        this.dns = dns;
        this.nom = nom;
    }

    @Override
    public void execute() {
        AdresseIP ip = dns.getIpParNom(nom);
        if (ip != null) {
            System.out.println(ip);
        } else {
            System.out.println("Nom machine non trouvé : " + nom);
        }
    }
}
