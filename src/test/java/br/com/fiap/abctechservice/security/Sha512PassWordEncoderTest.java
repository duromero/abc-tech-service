package br.com.fiap.abctechservice.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Sha512PassWordEncoderTest {
    private static final String PASSWORD = "123456789";
    private static final String WRONG_PASSWORD = "123456";
    private static final String ENCODED = "d9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85";

    private final Sha512PassWordEncoder encoder = new Sha512PassWordEncoder();

    @Test
    public void shouldEncodePassword() {
        assertEquals(ENCODED, encoder.encode(PASSWORD));
    }

    @Test
    public void shouldMatchCorrectPassword() {
        assertTrue(encoder.matches(PASSWORD, ENCODED));
    }

    @Test
    public void shouldNotMatchWrongPassword() {
        assertFalse(encoder.matches(WRONG_PASSWORD, ENCODED));
    }
}