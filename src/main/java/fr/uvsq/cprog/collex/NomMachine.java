package fr.uvsq.cprog.collex;

import java.util.Objects;

public class NomMachine {

    private final String nomComplet;  // ex: www.uvsq.fr
    private final String nomMachine;  // ex: www
    private final String domaine;     // ex: uvsq.fr

    public NomMachine(String nomComplet) {
        if (!isValidNomMachine(nomComplet)) {
            throw new IllegalArgumentException("Nom de machine invalide : " + nomComplet);
        }
        this.nomComplet = nomComplet;
        int index = nomComplet.indexOf('.');
        this.nomMachine = nomComplet.substring(0, index);
        this.domaine = nomComplet.substring(index + 1);
    }

    private boolean isValidNomMachine(String nom) {
        if (nom == null) return false;
        int index = nom.indexOf('.');
        if (index <= 0 || index == nom.length() - 1) return false;
        // Tu peux ajouter plus de validation si tu veux
        return true;
    }

    public String getNomMachine() {
        return nomMachine;
    }

    public String getDomaine() {
        return domaine;
    }

    @Override
    public String toString() {
        return nomComplet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NomMachine)) return false;
        NomMachine other = (NomMachine) o;
        return nomComplet.equals(other.nomComplet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomComplet);
    }
}
