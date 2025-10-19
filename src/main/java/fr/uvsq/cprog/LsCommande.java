package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.DnsItem;

import java.util.Comparator;
import java.util.List;

public class LsCommande implements Commande {
    private Dns dns;
    private String domaine;
    private boolean triParIp;

    public LsCommande(Dns dns, String domaine, boolean triParIp) {
        this.dns = dns;
        this.domaine = domaine;
        this.triParIp = triParIp;
    }

    @Override
    public void execute() {
        List<DnsItem> items = dns.getItems(domaine);
        if (triParIp) {
            items.sort(Comparator.comparing(DnsItem::getAdresseIP));
        } else {
            items.sort(Comparator.comparing(DnsItem::getNom));
        }

        for (DnsItem item : items) {
            System.out.println(item.getAdresseIP() + " " + item.getNom());
        }
    }
}
