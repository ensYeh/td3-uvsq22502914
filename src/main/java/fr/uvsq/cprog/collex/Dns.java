package fr.uvsq.cprog.collex;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Dns {
    private final List<DnsItem> items;

    public Dns() {
        this.items = new ArrayList<>();
    }

    public void add(NomMachine nom, AdresseIP ip) {
        DnsItem newItem = new DnsItem(nom, ip);
        if (!items.contains(newItem)) {
            items.add(newItem);
        }
    }

    public AdresseIP getIpParNom(NomMachine nom) {
        for (DnsItem item : items) {
            if (item.getNomMachine().equals(nom)) {
                return item.getAdresseIP();
            }
        }
        return null;
    }

    public NomMachine getNomParIp(AdresseIP ip) {
        for (DnsItem item : items) {
            if (item.getAdresseIP().equals(ip)) {
                return item.getNomMachine();
            }
        }
        return null;
    }

    public List<DnsItem> getAll() {
        return new ArrayList<>(items);
    }

    public List<DnsItem> getItemsParDomaine(String domaine, boolean exact) {
        return items.stream()
            .filter(item -> {
                String str = item.getNomMachine().toString();
                if (exact) {
                    return str.equals(domaine);
                } else {
                    return str.endsWith("." + domaine) || str.equals(domaine);
                }
            })
            .collect(Collectors.toList());
    }

    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // Ignore les lignes vides ou commentaires
                }
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    try {
                        NomMachine nom = new NomMachine(parts[0]);
                        AdresseIP ip = new AdresseIP(parts[1]);
                        add(nom, ip);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ligne invalide dans le fichier DNS : " + line);
                    }
                } else {
                    System.out.println("Format incorrect dans le fichier DNS : " + line);
                }
            }
        }
    }
}
