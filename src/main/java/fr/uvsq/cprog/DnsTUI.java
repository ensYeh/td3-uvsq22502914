package fr.uvsq.cprog;

import fr.uvsq.cprog.collex.Dns;

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

        if (ligne.equalsIgnoreCase("quit") || ligne.equalsIgnoreCase("exit")) {
            return new QuitterCommande();
        }

        // add <ip> <nom.qualifie>
        if (ligne.startsWith("add ")) {
            String[] parts = ligne.split("\\s+");
            if (parts.length == 3) {
                String ip = parts[1];
                String nom = parts[2];
                return new AddCommande(dns, ip, nom);
            } else {
                System.out.println("Commande 'add' mal formée. Usage: add <ip> <nom.qualifie>");
                return null;
            }
        }

        // ls [-a] <domaine>
        if (ligne.startsWith("ls")) {
            String[] parts = ligne.split("\\s+");
            boolean triParIp = false;
            String domaine = null;

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

        // Si ligne est une adresse IP (simple regex)
        if (ligne.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            return new RechercheNomCommande(dns, ligne);
        }

        // Sinon on considère que c'est un nom qualifié
        if (ligne.contains(".")) {
            return new RechercheIpCommande(dns, ligne);
        }

        System.out.println("Commande inconnue.");
        return null;
    }

    public void affiche(String message) {
        System.out.println(message);
    }
}
