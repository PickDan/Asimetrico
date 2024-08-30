package com.encriptar.asimetrico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@Controller
public class KeyController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/generate-keys")
    public String generateKeys(@RequestParam("input") String input, Model model) {
        try {
            // Generar claves RSA (pública y privada)
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Tamaño de la clave
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Obtener las claves en formato Base64 para que se represente como texto
            String publicKey = java.util.Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            String privateKey = java.util.Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
            //Añade la clave al modelo para que sea visible
            model.addAttribute("publicKey", publicKey);
            model.addAttribute("privateKey", privateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error generando las claves.");
        }

        return "result";
    }
}
