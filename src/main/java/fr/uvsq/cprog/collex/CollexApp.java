package fr.uvsq.cprog.collex;

import fr.uvsq.cprog.Commande;
import fr.uvsq.cprog.AddCommande;
import fr.uvsq.cprog.RechercheIpCommande;
import fr.uvsq.cprog.LsCommande;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class CollexApp {
    public static void main(String[] args) {
        Dns dns = new Dns();

        // Chargement du fichier config.properties
        Properties config = new Properties();
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            config.load(fis);
            fis.close();
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier config.properties : " + e.getMessage());
            return;
        }

        // Récupération du nom du fichier DNS dans la config
        String dnsFile = config.getProperty("dns.file");
        if (dnsFile == null) {
            System.out.println("La propriété 'dns.file' est manquante dans config.properties");
            return;
        }

        // Chargement des données DNS
        try {
            dns.loadFromFile(dnsFile);
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier DNS : " + e.getMessage());
            return;
        }

        // Démarrage de l'interface utilisateur
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
                String ipStr = ligne.substring(12).trim();
                try {
                    AdresseIP ip = new AdresseIP(ipStr);
                    Commande cmd = new RechercheIpCommande(dns, ip);
                    cmd.execute();
                } catch (IllegalArgumentException e) {
                    System.out.println("Adresse IP invalide : " + ipStr);
                }

            } else if (ligne.startsWith("add ")) {
                String[] parts = ligne.substring(4).trim().split("\\s+");
                if (parts.length == 2) {
                    try {
                        NomMachine nom = new NomMachine(parts[0]);
                        AdresseIP ip = new AdresseIP(parts[1]);
                        Commande cmd = new AddCommande(dns, nom, ip); // ordre corrigé ici
                        cmd.execute();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur dans les arguments : " + e.getMessage());
                    }
                } else {
                    System.out.println("Usage : add <nomMachine> <adresseIP>");
                }

            } else if (ligne.startsWith("ls")) {
                Commande cmd = new LsCommande(dns, "", false);
                cmd.execute();

            } else {
                System.out.println("Commande inconnue.");
            }
        }

        scanner.close();
    }
}
