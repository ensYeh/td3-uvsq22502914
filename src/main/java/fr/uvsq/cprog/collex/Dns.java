package fr.uvsq.cprog.collex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dns {
    private final List<DnsItem> items = new ArrayList<>();
    private final String filename;

    public Dns(String filename) {
        this.filename = filename;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    String nom = parts[0];
                    String ip = parts[1];
                    DnsItem item = new DnsItem(nom, ip);
                    items.add(item);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public void addItem(String ip, String nom) throws Exception {
        // Vérifie si l’adresse ou le nom existe déjà
        for (DnsItem item : items) {
            if (item.getNom().equals(nom)) {
                throw new Exception("Le nom de machine existe déjà !");
            }
            if (item.getAdresseIP().equals(ip)) {
                throw new Exception("L'adresse IP existe déjà !");
            }
        }

        // Ajoute l’élément à la liste
        DnsItem newItem = new DnsItem(nom, ip);
        items.add(newItem);

        // Met à jour le fichier
        try {
            String ligne = nom + " " + ip + System.lineSeparator();
            Files.write(Paths.get(filename),
                        ligne.getBytes(),
                        StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new Exception("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }

    public void afficheTout() {
        for (DnsItem item : items) {
            System.out.println(item);
        }
    }

    public DnsItem getItem(String nomQualifie) {
        for (DnsItem item : items) {
            if (item.getNom().equals(nomQualifie)) {
                return item;
            }
        }
        return null;
    }
}
