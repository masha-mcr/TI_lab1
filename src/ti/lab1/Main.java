package ti.lab1;

import ti.lab1.ciphers.Columns;
import ti.lab1.ciphers.Railfence;
import ti.lab1.ciphers.RotatingLattice;
import ti.lab1.ciphers.Vigener;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Railfence railfence = new Railfence();
        Columns columns = new Columns();
        RotatingLattice rotatingLattice = new RotatingLattice();
        Vigener vigener = new Vigener();

        while (true) {

            System.out.println("\nВыберите:");
            System.out.println("1. Железнодорожная изгородь");
            System.out.println("2. Перестановка столбцов");
            System.out.println("3. Поворачивающаяся решётка");
            System.out.println("4. Шифр Вижинера");
            System.out.println("5. Выход");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ваш выбор:");
            int choice = scanner.nextInt();

            if (choice == 5) break;

            Scanner scan = new Scanner(System.in);
            System.out.println("\nВведите строку для шифрования (англ/рус):");
            String in = scan.nextLine();

            switch (choice) {
                case 1:
                    railfence.railfenceMain(in);
                    break;
                case 2:
                    columns.columnsMain(in);
                    break;
                case 3:
                    rotatingLattice.rotatingLatticeMain(in);
                    break;
                case 4:
                    vigener.vigenerMain(in);
                    break;
            }
        }

    }
}

