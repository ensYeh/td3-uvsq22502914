package fr.uvsq.cprog;

import java.util.Scanner;
import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.Commande;
import fr.uvsq.cprog.RechercheIpCommande;

/**
 * Classe pour interagir avec l'utilisateur (TUI = Text User Interface).
 */
public class DnsTUI {
    private final Scanner scanner;

    public DnsTUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Lit la prochaine commande tapée par l'utilisateur.
     * @return la ligne de commande saisie (trimée)
     */
    public String nextInput() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    /**
     * Afficher un message à l'utilisateur.
     * @param o l'objet à afficher
     */
    public void affiche(Object o) {
        System.out.println(o);
    }

    /**
     * Analyse la ligne de commande et renvoie la commande correspondante.
     * @param dns l'objet Dns pour manipuler les entrées DNS
     * @return une instance de Commande ou null si commande invalide
     */
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
