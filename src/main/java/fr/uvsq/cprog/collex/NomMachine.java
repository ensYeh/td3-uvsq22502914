package fr.uvsq.cprog.collex;

import java.util.Objects;

public class NomMachine implements Comparable<NomMachine> {
    private final String nom;

    public NomMachine(String nom) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Nom de machine invalide");
        }
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NomMachine)) return false;
        NomMachine that = (NomMachine) o;
        return nom.equals(that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public int compareTo(NomMachine other) {
        return this.nom.compareTo(other.nom);
    }
}
