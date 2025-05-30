package com.foodexpress.microservice.Security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class Password implements PasswordEncoder {
    private static final String SALT = "Cache_Salt_Yeah";

    /**
     * Método para codificar la contraseña de los usuarios.
     *
     * @param rawPassword Contraseña en estado bruto.
     * @return La contraseña ya codificada.
     */
    @Override
    public String encode(CharSequence rawPassword) {
        try {
            // 1. Prepara el digest SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 2. Inyecta el salt
            digest.update(SALT.getBytes(StandardCharsets.UTF_8));
            // 3. Hashea la contraseña
            byte[] hash = digest.digest(rawPassword.toString().getBytes(StandardCharsets.UTF_8));
            // 4. Devuélve en Base64
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new IllegalStateException("Error al codificar la contraseña", e);
        }
    }

    /**
     * Para comparar contraseñas, se pasa la bruta y la codificada.
     *
     * @param rawPassword Contraseña bruta.
     * @param encodedPassword Contraseña ya codificada.
     * @return True or False.
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
