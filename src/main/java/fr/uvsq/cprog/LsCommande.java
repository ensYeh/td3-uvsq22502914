package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.DnsItem;

import java.util.List;
import java.util.Comparator;

public class LsCommande implements Commande {
    private final Dns dns;
    private final String domaine;
    private final boolean triParIp;

    public LsCommande(Dns dns, String domaine, boolean triParIp) {
        this.dns = dns;
        this.domaine = domaine;
        this.triParIp = triParIp;
    }

    @Override
    public void execute() {
        List<DnsItem> items = dns.getItemsParDomaine(domaine, false);
        if (triParIp) {
            items.sort(Comparator.comparing(DnsItem::getAdresseIP));
        } else {
            items.sort(Comparator.comparing(DnsItem::getNomMachine));
        }
        for (DnsItem item : items) {
            System.out.println(item.getNomMachine() + " -> " + item.getAdresseIP());
        }
        if (items.isEmpty()) {
            System.out.println("Aucun élément pour le domaine : " + domaine);
        }
    }
}
