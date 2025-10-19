package fr.uvsq.cprog;

import java.util.Scanner;

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
}
