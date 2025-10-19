package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;

public class AddCommande implements Commande {
    private Dns dns;
    private String ip;
    private String nomQualifie;

    public AddCommande(Dns dns, String ip, String nomQualifie) {
        this.dns = dns;
        this.ip = ip;
        this.nomQualifie = nomQualifie;
    }

    @Override
    public void execute() {
        try {
            dns.addItem(ip, nomQualifie);
            System.out.println("Machine ajoutée avec succès.");
        } catch (Exception e) {
            System.out.println("ERREUR : " + e.getMessage());
        }
    }
}
