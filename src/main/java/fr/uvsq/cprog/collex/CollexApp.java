package fr.uvsq.cprog.collex;

import fr.uvsq.cprog.*;
import java.util.Scanner;

public class CollexApp {
    public static void main(String[] args) {
        try {
            Dns dns = new Dns("dns_data.txt");
            Scanner scanner = new Scanner(System.in);
            String ligne;

            System.out.println("Bienvenue dans le gestionnaire DNS !");
            while (true) {
                System.out.print("> ");
                ligne = scanner.nextLine().trim();
                if (ligne.equalsIgnoreCase("quitter")) {
                    System.out.println("Au revoir !");
                    break;
                } else if (ligne.startsWith("rechercheip ")) {
                    String nom = ligne.substring(12).trim();
                    Commande cmd = new RechercheIpCommande(dns, nom);
                    cmd.execute();
                } else if (ligne.startsWith("add ")) {
                    String[] parts = ligne.substring(4).trim().split("\\s+");
                    if (parts.length == 2) {
                        Commande cmd = new AddCommande(dns, parts[0], parts[1]);
                        cmd.execute();
                    } else {
                        System.out.println("Usage : add <adresseIP> <nomMachine>");
                    }
                } else {
                    System.out.println("Commande inconnue.");
                }
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
