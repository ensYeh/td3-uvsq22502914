package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.NomMachine;
import fr.uvsq.cprog.collex.AdresseIP;

public class AddCommande implements Commande {
    private final Dns dns;
    private final NomMachine nom;
    private final AdresseIP ip;

    public AddCommande(Dns dns, NomMachine nom, AdresseIP ip) {
        this.dns = dns;
        this.nom = nom;
        this.ip = ip;
    }

    @Override
    public void execute() {
        dns.add(nom, ip);
        System.out.println("Ajouté : " + nom + " -> " + ip);
    }
}
