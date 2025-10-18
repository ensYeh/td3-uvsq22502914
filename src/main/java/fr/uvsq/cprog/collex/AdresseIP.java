package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP implements Comparable<AdresseIP> {

    private final String ip;

    public AdresseIP(String ip) {
        if (!isValidIP(ip)) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ip);
        }
        this.ip = ip;
    }

    private boolean isValidIP(String ip) {
        if (ip == null) return false;
        String[] parts = ip.split("\\.");
        if (parts.length != 4) return false;
        try {
            for (String part : parts) {
                int n = Integer.parseInt(part);
                if (n < 0 || n > 255) return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseIP)) return false;
        AdresseIP other = (AdresseIP) o;
        return ip.equals(other.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    @Override
    public int compareTo(AdresseIP other) {
        String[] parts1 = this.ip.split("\\.");
        String[] parts2 = other.ip.split("\\.");
        for (int i = 0; i < 4; i++) {
            int n1 = Integer.parseInt(parts1[i]);
            int n2 = Integer.parseInt(parts2[i]);
            if (n1 != n2) {
                return n1 - n2;
            }
        }
        return 0;
    }
}
