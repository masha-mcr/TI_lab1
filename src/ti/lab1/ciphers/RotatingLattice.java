package ti.lab1.ciphers;

import java.util.Scanner;

public class RotatingLattice {
    private static char[][] charLattice;

    public void rotatingLatticeMain(final String s){
        System.out.println("Введите размер решетки (четное число): ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n % 2 != 0 || n * n < s.length()) {
            System.out.println("Данные введены неверно.Повторите попытку: ");
            n = scanner.nextInt();
        } else {
            boolean[][] lattice = initLattice(n);

            String encrypted = encrypt(s, lattice);
            System.out.println("Шифрование: " + encrypted);
            System.out.println("Дешифрование: " + decrypt(encrypted, lattice));
        }

    }

    private boolean[][] initLattice(final int n){
        boolean[][] boolLattice = new boolean[n][n];
        int[][] numLattice = new int[n][n];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < n; i++)                 //Инициализация матриц
            for (int j = 0; j < n; j++)
            {
                boolLattice[i][j] = false ;
                numLattice[i][j] = 0 ;
            }

        for (int rotations = 0; rotations < 4 ; rotations++) {
            int number = 1 ;
            for (int i = 0; i < n / 2; i++)
                for (int j = 0; j < n / 2; j++) {
                    numLattice[i][j] = number++;
                }
            numLattice = rotateNumLattice(numLattice);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(numLattice[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < (n/2)*(n/2); i++) {      //"Вырезание отверстий" в логической матрице
            boolean flag = true;
            while (flag)
            {
                System.out.println("Введите координаты отверстия для " + (i+1) + " (0-" + (n - 1) + "):");
                System.out.print("x: ");
                int x = scanner.nextInt();
                System.out.print("y: ");
                int y = scanner.nextInt() ;
                if (numLattice[x][y] == (i + 1))
                {
                    boolLattice[x][y] = true;
                    flag = false;
                }
                else {
                    System.out.println("Данные введены неверно!");
                }
            }
        }
        return boolLattice;
    }

    private int[][] rotateNumLattice(int[][] numLattice) {
        final int n = numLattice.length;
        int[][] resLattice = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resLattice[i][j] = numLattice[n - j - 1][i];
            }
        }
        return resLattice;
    }

    private boolean[][] rotateBoolLattice (boolean[][] boolLattice) {
        final int n = boolLattice.length;
        boolean[][] resLattice = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resLattice[i][j] = boolLattice[n - j - 1][i];
            }
        }
        return resLattice;
    }

    private String encrypt(final String s, boolean[][] boolLattice){
        final int n = boolLattice.length;
        charLattice = new char[n][n];

        for (int i = 0; i < n ; i++)
            for(int j = 0 ; j < n ; j++)
                charLattice[i][j] = s.charAt(0) < 'А' ? (char) ((int)(Math.random() * 26) + 'A') : (char) ((int)(Math.random() * 33) + 'А') ;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(charLattice[i][j] + " ");
            }
            System.out.println();
        }

        int index = 0;

        for (int rotations = 0; rotations < 4; rotations++){
            if (index <= s.length())
            {
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                    {
                        if (boolLattice[i][j])
                        {
                            if (index < s.length()) {
                                charLattice[i][j] = s.charAt(index);
                            }
                            if (index == s.length()) {
                                charLattice[i][j] = '0';
                            }
                            index++ ;
                        }
                    }
            }

            boolLattice = rotateBoolLattice(boolLattice);
        }

        StringBuilder encrypted = new StringBuilder();
        for (int i = 0 ; i < n; i++)
            for (int j = 0 ; j < n; j++)
                encrypted.append(charLattice[i][j]);

        return encrypted.toString();
    }

    private String decrypt(final String s, boolean[][] boolLattice){
        final int n = boolLattice.length;
        charLattice = new char[n][n];

        int index = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                charLattice[i][j] = s.charAt(index);
                index++;
            }

        StringBuilder decrypted = new StringBuilder();

        for (int rotations = 0; rotations < 4; rotations++)
        {
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                {
                    if (boolLattice[i][j])
                    {
                        decrypted.append(charLattice[i][j]);
                    }
                }
            boolLattice = rotateBoolLattice(boolLattice);
        }

        return decrypted.substring(0, decrypted.toString().indexOf('0'));
    }
}
