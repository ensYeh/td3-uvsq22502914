package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP {
    private final String ip;

    public AdresseIP(String ip) {
        if (!isValidIP(ip)) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ip);
        }
        this.ip = ip;
    }

    private boolean isValidIP(String ip) {
        // Vérifie le format simple d'une IPv4
        String[] parts = ip.split("\\.");
        if (parts.length != 4) return false;
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseIP)) return false;
        AdresseIP adresseIP = (AdresseIP) o;
        return ip.equals(adresseIP.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}

