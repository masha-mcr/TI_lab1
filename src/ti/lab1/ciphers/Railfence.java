package ti.lab1.ciphers;

import java.util.Scanner;

public class Railfence {

    private static char[][] table;

    public void railfenceMain(final String s){
        System.out.println("\nВведите ключ(число): ");
        Scanner scanner = new Scanner(System.in);
        int key = scanner.nextInt();

        String encrypted = encrypt(s, key);
        System.out.println("Шифрование: " + encrypted);
        System.out.println("Дешифрование: " + decrypt(encrypted, key));
    }
    private String encrypt(final String s, final int rows){
        if (rows == 1) return s;
        if (rows < 1) return null;

        table = new char[rows][s.length()];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < s.length(); j++){
                table[i][j] = '*';
            }

        int period = 2 * (rows - 1);

        for (int i = 0; i < s.length(); i++) {
            int ost = i % period;
            int row = rows - 1 - Math.abs(rows - 1 - ost);
            table[row][i] = s.charAt(i);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < s.length(); j++){
                if (table[i][j] != '*')
                    result.append(table[i][j]);

            }
        return result.toString();
    }

    public String decrypt(final String s, final int rows){
        if (rows == 1) return s;
        if (rows < 1) return null;

        table = new char[rows][s.length()];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < s.length(); j++){
                table[i][j] = '*';
            }

        int period = 2 * (rows - 1);

        for (int i = 0; i < s.length(); i++) {
            int ost = i % period;
            int row = rows - 1 - Math.abs(rows - 1 - ost);
            table[row][i] = '_';
        }

        int ind = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < s.length(); j++){
                if (table[i][j] == '_')
                    table[i][j] = s.charAt(ind++);
            }

        StringBuilder result = new StringBuilder();
        for (int j = 0; j < s.length(); j++)
            for (int i = 0; i < rows; i++){
                if (table[i][j] != '*') {
                    result.append(table[i][j]);
                }
            }

        return result.toString();
    }
}
