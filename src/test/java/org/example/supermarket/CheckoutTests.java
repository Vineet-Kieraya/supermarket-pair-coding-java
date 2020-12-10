package org.example.supermarket;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTests {


    private static final List<String> RULES = new ArrayList<>();

    int price(String items) {
        Checkout co = new Checkout(RULES);
        for(int i = 0; i < items.length(); i++) {
            String item = String.valueOf(items.charAt(i));
            co.scan(item);
        }
        return co.total();
    }

    @Test
    void testTotals() {
        assertEquals(0, price(""));
        assertEquals(50, price("A"));
        assertEquals(80, price("AB"));
        assertEquals(115, price("CDBA"));

        assertEquals(100, price("AA"));
        assertEquals(130, price("AAA"));
        assertEquals(180, price("AAAA"));
        assertEquals(230, price("AAAAA"));
        assertEquals(260, price("AAAAAA"));

        assertEquals(160, price("AAAB"));
        assertEquals(175, price("AAABB"));
        assertEquals(190, price("AAABBD"));
        assertEquals(190, price("DABABA"));
    }

    @Test
    void testIncrementals() {
        Checkout co = new Checkout(RULES);
        assertEquals(0, co.total());
        co.scan("A");  assertEquals(50, co.total());
        co.scan("B");  assertEquals(80, co.total());
        co.scan("A");  assertEquals(130, co.total());
        co.scan("A");  assertEquals(160, co.total());
        co.scan("B");  assertEquals(175, co.total());
    }
}
