package br.com.fiap.abctechservice.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha512PassWordEncoder implements PasswordEncoder {

    public String encode(CharSequence senhaAberta) {
        return DigestUtils.sha512Hex(senhaAberta.toString());
    }

    public boolean matches(CharSequence senhaAberta, String senhaEncoded) {
        return DigestUtils.sha512Hex(senhaAberta.toString()).equals(senhaEncoded);
    }
}
