package fr.uvsq.cprog;

import java.util.Scanner;
import fr.uvsq.cprog.collex.Dns;

public class DnsTUI {
    private final Scanner scanner;

    public DnsTUI() {
        scanner = new Scanner(System.in);
    }

    public String nextInput() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public void affiche(Object o) {
        System.out.println(o);
    }

    public Commande nextCommande(Dns dns) {
        String ligne = nextInput();
        String[] parties = ligne.split("\\s+");

        if (parties.length == 0 || parties[0].isEmpty()) {
            return null;
        }

        String cmd = parties[0];

        switch (cmd) {
            case "rechercheip":
                if (parties.length < 2) {
                    affiche("ERREUR : il manque le nom de machine");
                    return null;
                }
                return new RechercheIpCommande(dns, parties[1]);

            case "add":
                if (parties.length < 3) {
                    affiche("ERREUR : syntaxe : add <adresse_ip> <nom_machine>");
                    return null;
                }
                return new AddCommande(dns, parties[1], parties[2]);

            case "quitter":
                return new Commande() {
                    @Override
                    public void execute() {
                        System.out.println("Au revoir !");
                        System.exit(0);
                    }
                };

            default:
                affiche("Commande inconnue : " + cmd);
                return null;
        }
    }
}

