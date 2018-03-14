package com.apoel.crypthography;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class CrypthoFunctions {

    public static void createKeyPairs(int id, String pass){
        System.out.println("dzięki za id: " + id);
        try {

            //generowanie kluczy
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(128);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            byte[] privateKey = keyPair.getPrivate().getEncoded();
            byte[] publicKey = keyPair.getPublic().getEncoded();

            //funkcja skrótu z hasła usera przy pomocy SHA=256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secret_key = new SecretKeySpec(hash, "SHA-256");

            //inicjacja ciphera
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret_key);

            //szyfrowanie klucza prywatnego
            byte[] encryptedPrivateKey = cipher.doFinal(privateKey);

            //zapisanie publicznego klucza i prywatnego do oddzielnych plików
            KeyIdRel privKeyId = new KeyIdRel(id, encryptedPrivateKey);
            KeyIdRel publicKeyId = new KeyIdRel(id, publicKey);
            saveKeys(privKeyId, publicKeyId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveKeys(KeyIdRel priv, KeyIdRel pub) throws IOException {
        FileOutputStream fos;
        ObjectOutputStream oos;

        fos = new FileOutputStream("D:\\Keys\\private.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(priv);
        oos.close();

        fos = new FileOutputStream("D:\\Keys\\public.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(pub);
        oos.close();
    }


}
