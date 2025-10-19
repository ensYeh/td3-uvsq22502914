package fr.uvsq.cprog;

public class QuitterCommande implements Commande {
    @Override
    public void execute() {
        System.out.println("Au revoir !");
        System.exit(0);  // quitte l’application
    }
}
