package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.NomMachine;
import fr.uvsq.cprog.collex.AdresseIP;

public class App {
    public static void main(String[] args) {
        Dns dns = new Dns();

        // Création des objets NomMachine et AdresseIP
        NomMachine nom = new NomMachine("exemple.uvsq.fr");
        AdresseIP ip = new AdresseIP("192.168.0.1");

        // Ajout de la machine dans le DNS
        AddCommande add = new AddCommande(dns, nom, ip);
        add.execute();

        // Lister les entrées DNS pour le domaine racine, trié par nom (false)
        LsCommande ls = new LsCommande(dns, "", false);
        ls.execute();
    }
}
