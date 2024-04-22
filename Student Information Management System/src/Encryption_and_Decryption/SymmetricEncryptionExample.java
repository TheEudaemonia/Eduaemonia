package Encryption_and_Decryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SymmetricEncryptionExample {
    String secretKeyString = "MySecretKeysssss"; // 秘钥字符串

    // 将秘钥字符串转换为字节数组
    byte[] secretKeyBytes = secretKeyString.getBytes();

    // 创建秘钥对象
    SecretKey secretKey = new SecretKeySpec(secretKeyBytes, "AES");


    //加密
    public String Encryption(String s) throws Exception  {

        // 创建加密器
        Cipher cipher = Cipher.getInstance("AES");

        // 加密
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(s.getBytes());

        // 将加密后的字节数组转换为 Base64 编码的字符串
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        return encryptedText;
    };

    //解密
    public String Decryption(String s) throws Exception{
        // 创建加密器
        Cipher cipher = Cipher.getInstance("AES");

        // 创建秘钥对象
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, "AES");

        // 解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(s));

        // 将解密后的字节数组转换为字符串
        String decryptedText = new String(decryptedBytes);
        return decryptedText;
    }
}
