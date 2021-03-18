package ti.lab1.ciphers;

import java.util.*;

public class Columns {

    public void columnsMain(final String s){
        System.out.println("\nВведите ключ(слово): ");
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        key = key.toUpperCase();
        ArrayList<Integer> keyInt = keyToInt(key);
        String encrypted = encrypt(s, keyInt);
        System.out.println("Шифрование: " + encrypted);
        System.out.println("Дешифрование: " + decrypt(encrypted, keyInt));
    }

    private ArrayList<Integer> keyToInt(String key){
        final char MAX = (int) 'Я' + 1;
        int[] keyInt = new int[key.length()];

        int keyNum = 1;
        for (int j=0; j < key.length(); j++) {
            int min = key.charAt(j) < (int) 'А' ? (int) 'Z' : (int) MAX;
            int indMin = 0;

            for (int i = 0; i < key.length(); i++) {
                if (key.charAt(i) < min) {
                    min = key.charAt(i);
                    indMin = i;
                }
            }
            keyInt[indMin] = keyNum++;
            key = key.replaceFirst(String.valueOf(key.charAt(indMin)), String.valueOf(MAX));
        }

        ArrayList<Integer> keyList = new ArrayList<>();
        for (int i : keyInt)
            keyList.add(i);

        return keyList;
    }

    private String encrypt(String s, final ArrayList<Integer> keyList){

        StringBuilder sBuilder = new StringBuilder(s);
        while (sBuilder.length() % keyList.size() != 0){
            sBuilder.append('*');
        }
        s = sBuilder.toString();

        StringBuilder encrypted = new StringBuilder();
        for (int i = 1; i < keyList.size() + 1; i++){
            int index = keyList.indexOf(i);
            while (index < s.length()) {
                if (s.charAt(index) != '*') {
                    encrypted.append(s.charAt(index));
                }
                index += keyList.size();
            }
        }
        return encrypted.toString();
    }

    private String decrypt(String s, ArrayList<Integer> keyList){
        int matrixHeight = s.length() % keyList.size() == 0 ? s.length() / keyList.size() : s.length() / keyList.size() + 1;
        char[][] matrix = new char[matrixHeight][keyList.size()];
        if (s.length() % keyList.size() != 0) {
            for (int i = s.length() % keyList.size(); i < keyList.size(); i++)
                matrix[matrixHeight - 1][i] = '*';
        }


        int sInd = 0;
        for (int i = 1; i < keyList.size() + 1; i++){
            int index = keyList.indexOf(i);
            for (int j = 0; j < matrixHeight; j++) {
                if (sInd < s.length() && matrix[j][index] != '*') {
                    matrix[j][index] = s.charAt(sInd++);
                } else {
                    matrix[j][index] = '*';
                }
            }
        }

        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < matrixHeight; i++)
            for (int j = 0; j < keyList.size(); j++){
                if (matrix[i][j] != '*')
                    decrypted.append(matrix[i][j]);
            }

        return decrypted.toString();
    }
}
