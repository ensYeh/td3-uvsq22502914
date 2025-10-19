package fr.uvsq.cprog.collex;

public class CollexApp {
    public static void main(String[] args) {
        try {
            Dns dns = new Dns("dns_data.txt");
            dns.afficheTout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
