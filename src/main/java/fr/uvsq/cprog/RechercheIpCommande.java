package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.DnsItem;

public class RechercheIpCommande implements Commande {
    private Dns dns;
    private String nomQualifie;

    public RechercheIpCommande(Dns dns, String nomQualifie) {
        this.dns = dns;
        this.nomQualifie = nomQualifie;
    }

    @Override
    public void execute() {
        try {
            DnsItem item = dns.getItem(nomQualifie);
            if (item != null) {
                System.out.println(item.getAdresseIP());
            } else {
                System.out.println("ERREUR : Nom de machine inconnu");
            }
        } catch (Exception e) {
            System.out.println("ERREUR : " + e.getMessage());
        }
    }
}
