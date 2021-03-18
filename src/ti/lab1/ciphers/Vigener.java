package ti.lab1.ciphers;

import java.util.Scanner;

public class Vigener {

    public void vigenerMain(String s){
        System.out.println("\nВведите ключ(строку): ");
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        s = s.toUpperCase();
        s = s.replace(" ", "");
        key = generateKey(s, key);
        String encrypted = encrypt(s, key);
        System.out.println("Шифрование: " + encrypted);
        System.out.println("Дешифрование: " + decrypt(encrypted, key));
    }

    private String generateKey(String s, String key)
    {
        StringBuilder newKey = new StringBuilder();
        key = key.toUpperCase();
        String sWithoutSpaces = s.replace(" ", "");
        for (int i = 0; i < s.length(); i++)
        {
            if (i >= key.length()) {
                int ind = (i - key.length()) % sWithoutSpaces.length();
                newKey.append(sWithoutSpaces.charAt(ind));
            }
            else {
                newKey.append(key.charAt(i));
            }
        }
        return newKey.toString();
    }

    public String encrypt(String s, String key) {
        StringBuilder encrypted = new StringBuilder();
        int alph;
        if (s.charAt(0) < 'А') {
            alph = 26;
        } else {
            alph = 32;
        }
        for (int i = 0; i < s.length(); i++) {
            int offset = (s.charAt(i) + key.charAt(i)) % alph;
            if (alph == 26) {
                offset += 'A';
            } else {
                offset += 'А';
            }
            encrypted.append((char) offset);
        }
        return encrypted.toString();
    }

    public String decrypt(String s, String key) {
        StringBuilder decrypted = new StringBuilder();
        int alph;
        if (s.charAt(0) < 'А') {
            alph = 26;
        } else {
            alph = 32;
        }
        for (int i = 0; i < s.length(); i++) {
            int offset = (s.charAt(i) - key.charAt(i) + alph) % alph;

            if (alph == 26) {
                offset += 'A';
            } else {
                offset += 'А';
            }
            decrypted.append((char) offset);
        }
        return decrypted.toString();
    }

}
