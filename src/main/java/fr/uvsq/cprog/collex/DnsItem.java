package fr.uvsq.cprog.collex;

import java.util.Objects;

public class DnsItem {
    private final NomMachine nomMachine;
    private final AdresseIP adresseIP;

    public DnsItem(NomMachine nomMachine, AdresseIP adresseIP) {
        this.nomMachine = nomMachine;
        this.adresseIP = adresseIP;
    }

    public NomMachine getNomMachine() {
        return nomMachine;
    }

    public AdresseIP getAdresseIP() {
        return adresseIP;
    }

    @Override
    public String toString() {
        return nomMachine.toString() + " -> " + adresseIP.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DnsItem)) return false;
        DnsItem dnsItem = (DnsItem) o;
        return nomMachine.equals(dnsItem.nomMachine) && adresseIP.equals(dnsItem.adresseIP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomMachine, adresseIP);
    }
}
