package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;

public class AddCommande implements Commande {
    private final Dns dns;
    private final String adresseIP;
    private final String nomMachine;

    public AddCommande(Dns dns, String adresseIP, String nomMachine) {
        this.dns = dns;
        this.adresseIP = adresseIP;
        this.nomMachine = nomMachine;
    }

    @Override
    public void execute() {
        try {
            dns.addItem(adresseIP, nomMachine);
            System.out.println("Machine ajoutée avec succès !");
        } catch (Exception e) {
            System.out.println("ERREUR : " + e.getMessage());
        }
    }
}
