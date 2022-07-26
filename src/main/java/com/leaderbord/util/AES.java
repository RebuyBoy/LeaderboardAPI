package com.leaderbord.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AES {

    public static String decrypt(String secret, String data) {

        secret = convertStringToHex(secret);
        String decryptedText = "";

        try {
            byte[] cipherData = Base64.getDecoder().decode(data);
            byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            final byte[][] keyAndIV = generateKeyAndIV(saltData, secret.getBytes(StandardCharsets.UTF_8), md5);
            SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
            IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

            byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
            Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
            aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] decryptedData = aesCBC.doFinal(encrypted);
            decryptedText = new String(decryptedData, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException
                | InvalidAlgorithmParameterException
                | NoSuchPaddingException
                | IllegalBlockSizeException
                | BadPaddingException
                | InvalidKeyException e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

    private static byte[][] generateKeyAndIV(byte[] salt, byte[] password, MessageDigest md) {

        int digestLength = md.getDigestLength();
        int requiredLength = (32 + 16 + digestLength - 1) / digestLength * digestLength;
        byte[] generatedData = new byte[requiredLength];
        int generatedLength = 0;

        try {
            md.reset();
            while (generatedLength < 32 + 16) {
                if (generatedLength > 0) {
                    md.update(generatedData, generatedLength - digestLength, digestLength);
                }
                md.update(password);
                if (salt != null) {
                    md.update(salt, 0, 8);
                }
                md.digest(generatedData, generatedLength, digestLength);
                generatedLength += digestLength;
            }
            byte[][] result = new byte[2][];
            result[0] = Arrays.copyOfRange(generatedData, 0, 32);
            result[1] = Arrays.copyOfRange(generatedData, 32, 32 + 16);
            return result;
        } catch (DigestException e) {
            throw new RuntimeException(e);
        } finally {
            Arrays.fill(generatedData, (byte) 0);
        }
    }

    private static String convertStringToHex(String str) {
        long i = Long.parseLong(str);
        return Long.toHexString(i);
    }
}
