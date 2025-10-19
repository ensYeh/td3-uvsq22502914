package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdresseIPTest {

    @Test
    public void testAdresseIPValide() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        assertEquals("192.168.0.1", ip.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdresseIPInvalide() {
        new AdresseIP("999.999.999.999");
    }

    @Test
    public void testEgaliteAdresseIP() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.1");
        assertEquals(ip1, ip2);
    }

    @Test
    public void testNonEgaliteAdresseIP() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.2");
        assertNotEquals(ip1, ip2);
    }

    @Test
    public void testHashCode() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.1");
        assertEquals(ip1.hashCode(), ip2.hashCode());
    }

    @Test
    public void testCompareTo() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.2");
        assertTrue(ip1.compareTo(ip2) < 0);
        assertTrue(ip2.compareTo(ip1) > 0);
        assertEquals(0, ip1.compareTo(new AdresseIP("10.0.0.1")));
    }
}
