package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;

public class App {
    public static void main(String[] args) {
        // Passe le nom du fichier au constructeur
        Dns dns = new Dns("dns_data.txt");

        DnsTUI tui = new DnsTUI();

        while (true) {
            Commande cmd = tui.nextCommande(dns);
            if (cmd != null) {
                cmd.execute();
            }
        }
    }
}
