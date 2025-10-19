package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP implements Comparable<AdresseIP> {
    private final String ip;

    public AdresseIP(String ip) {
        if (!ip.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            throw new IllegalArgumentException("Adresse IP invalide");
        }
        this.ip = ip;
    }

    @Override
    public String toString() {
        return ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseIP)) return false;
        AdresseIP that = (AdresseIP) o;
        return ip.equals(that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    @Override
    public int compareTo(AdresseIP other) {
        return this.ip.compareTo(other.ip);
    }
}
