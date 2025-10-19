package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.NomMachine;
import fr.uvsq.cprog.collex.AdresseIP;

import java.util.Scanner;

public class DnsTUI {
    private final Dns dns;
    private final Scanner scanner = new Scanner(System.in);

    public DnsTUI(Dns dns) {
        this.dns = dns;
    }

    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();

        // Quitter
        if (ligne.equalsIgnoreCase("quit") || ligne.equalsIgnoreCase("exit")) {
            return new QuitterCommande();
        }

        // add <ip> <nom.qualifie>
        if (ligne.startsWith("add ")) {
            String[] parts = ligne.split("\\s+");
            if (parts.length == 3) {
                try {
                    AdresseIP ip = new AdresseIP(parts[1]);
                    NomMachine nom = new NomMachine(parts[2]);
                    return new AddCommande(dns, nom, ip);
                } catch (IllegalArgumentException e) {
                    System.out.println("Adresse IP ou NomMachine invalide.");
                    return null;
                }
            } else {
                System.out.println("Commande 'add' mal formée. Usage: add <ip> <nom.qualifie>");
                return null;
            }
        }

        // ls [-a] <domaine>
        if (ligne.startsWith("ls")) {
            String[] parts = ligne.split("\\s+");
            boolean triParIp = false;
            String domaine;

            if (parts.length == 2) {
                domaine = parts[1];
            } else if (parts.length == 3 && parts[1].equals("-a")) {
                triParIp = true;
                domaine = parts[2];
            } else {
                System.out.println("Commande 'ls' mal formée. Usage: ls [-a] <domaine>");
                return null;
            }

            return new LsCommande(dns, domaine, triParIp);
        }

        // Recherche nom par IP
        if (ligne.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            try {
                AdresseIP ip = new AdresseIP(ligne);
                return new RechercheNomCommande(dns, ip);
            } catch (IllegalArgumentException e) {
                System.out.println("Adresse IP invalide.");
                return null;
            }
        }

        // Recherche IP par nom
        if (ligne.contains(".")) {
            try {
                NomMachine nom = new NomMachine(ligne);
                return new RechercheIpCommande(dns, nom);
            } catch (IllegalArgumentException e) {
                System.out.println("NomMachine invalide.");
                return null;
            }
        }

        System.out.println("Commande inconnue.");
        return null;
    }

    public void affiche(String message) {
        System.out.println(message);
    }
}
