package fr.uvsq.cprog;

public class QuitterCommande implements Commande {
    @Override
    public void execute() {
        System.out.println("Fin du programme.");
    }
}
