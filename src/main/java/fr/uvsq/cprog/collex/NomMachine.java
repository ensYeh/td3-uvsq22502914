package fr.uvsq.cprog.collex;

import java.util.Objects;

public class NomMachine {
    private final String nom;

    public NomMachine(String nom) {
        if (!isValidNom(nom)) {
            throw new IllegalArgumentException("Nom de machine invalide : " + nom);
        }
        this.nom = nom;
    }

    private boolean isValidNom(String nom) {
        // Exemple simple : le nom doit contenir au moins un point (domaine)
        // et ne pas être vide.
        if (nom == null || nom.isEmpty()) return false;
        if (!nom.contains(".")) return false;
        // On peut ajouter d'autres vérifications si besoin
        return true;
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
}
