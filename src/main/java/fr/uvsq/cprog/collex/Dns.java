package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Dns {
    private final List<DnsItem> items = new ArrayList<>();
    private final Path fichier;

    public Dns(String filename) throws IOException {
        this.fichier = Path.of(filename);
        List<String> lignes = Files.readAllLines(fichier);
        for (String ligne : lignes) {
            String[] parts = ligne.trim().split("\\s+");
            if (parts.length == 2) {
                String nom = parts[0];
                String ip = parts[1];
                items.add(new DnsItem(nom, ip));
            }
        }
    }

    public DnsItem getItemParNom(String nomQualifie) {
        for (DnsItem item : items) {
            if (item.getNom().equals(nomQualifie)) {
                return item;
            }
        }
        return null;
    }

    public DnsItem getItemParIP(String ip) {
        for (DnsItem item : items) {
            if (item.getAdresseIP().equals(ip)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Renvoie la liste des DnsItem d'un domaine donné.
     * @param domaine ex: uvsq.fr
     * @param triParIP true pour trier par adresse IP, false pour trier par nom
     * @return liste triée
     */
    public List<DnsItem> getItemsParDomaine(String domaine, boolean triParIP) {
        List<DnsItem> result = items.stream()
            .filter(item -> item.getNom().endsWith("." + domaine))
            .collect(Collectors.toList());

        Comparator<DnsItem> comparateur = triParIP
            ? Comparator.comparing(DnsItem::getAdresseIP)
            : Comparator.comparing(DnsItem::getNom);

        result.sort(comparateur);
        return result;
    }

    /**
     * Ajoute un DnsItem et met à jour le fichier.
     * @throws Exception si nom ou IP existe déjà
     */
    public void addItem(String ip, String nom) throws Exception {
        for (DnsItem item : items) {
            if (item.getNom().equals(nom)) {
                throw new Exception("Le nom de machine existe déjà !");
            }
            if (item.getAdresseIP().equals(ip)) {
                throw new Exception("L'adresse IP existe déjà !");
            }
        }

        DnsItem newItem = new DnsItem(nom, ip);
        items.add(newItem);

        String ligne = nom + " " + ip + System.lineSeparator();
        try {
            Files.writeString(fichier, ligne, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new Exception("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }
}
