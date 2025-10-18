package fr.uvsq.cprog.collex;

import java.util.Objects;

public class DnsItem {

    private final AdresseIP adresseIP;
    private final NomMachine nomMachine;

    public DnsItem(AdresseIP adresseIP, NomMachine nomMachine) {
        this.adresseIP = adresseIP;
        this.nomMachine = nomMachine;
    }

    public AdresseIP getAdresseIP() {
        return adresseIP;
    }

    public NomMachine getNomMachine() {
        return nomMachine;
    }

    @Override
    public String toString() {
        return adresseIP.toString() + " " + nomMachine.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DnsItem)) return false;
        DnsItem other = (DnsItem) o;
        return adresseIP.equals(other.adresseIP) && nomMachine.equals(other.nomMachine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresseIP, nomMachine);
    }
}
