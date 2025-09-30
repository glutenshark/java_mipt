/*
*&---------------------------------------------------------------------*
*& Программа : Igra (Крестики-нолики)
*& Пакет     : krestiki_noliki
*& Автор     : Асланов Аслан
*& Дата      : 02.10.2025
*& Назначение: Консольная игра 3x3 для двух игроков (X/O).
*& Ввод      : координаты строки и столбца (1..3).
*& Вывод     : поле, сообщения о ходе/победе/ничьей.
*& Примечание: простая валидация ввода (hasNextInt).
*&---------------------------------------------------------------------*
*/

package krestiki_noliki;

import java.util.Scanner;

public class Igra {

    private static final Scanner skaner = new Scanner(System.in);

    static abstract class Igrok {
        String imya;
        char znak;

        Igrok(String imya, char znak) {
            this.imya = imya;
            this.znak = znak;
        }

        abstract void sdelatHod(Pole pole);
    }

    static class ChelovekIgrok extends Igrok {
        ChelovekIgrok(String imya, char znak) {
            super(imya, znak);
        }

        @Override
        void sdelatHod(Pole pole) {
            int stroka, stolbec;
            while (true) {
                System.out.print("Введите номер строки (1-3): ");
                if (!skaner.hasNextInt()) {
                    System.out.println("Введите целое число от 1 до 3.");
                    skaner.next();
                    continue;
                }
                stroka = skaner.nextInt();

                System.out.print("Введите номер столбца (1-3): ");
                if (!skaner.hasNextInt()) {
                    System.out.println("Введите целое число от 1 до 3.");
                    skaner.next();
                    continue;
                }
                stolbec = skaner.nextInt();

                if (stroka < 1 || stroka > 3 || stolbec < 1 || stolbec > 3) {
                    System.out.println("Некорректный ход, попробуйте снова.");
                    continue;
                }

                stroka--;
                stolbec--;

                if (pole.postavitZnak(stroka, stolbec, this.znak)) {
                    break;
                } else {
                    System.out.println("Клетка уже занята, выберите другую.");
                }
            }
        }
    }

    static class Pole {
        char[][] kletki;

        Pole() {
            kletki = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    kletki[i][j] = '-';
                }
            }
        }

        void vyvestiPole() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(kletki[i][j]);
                    if (j < 2) {
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
        }

        boolean poleZapolneno() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (kletki[i][j] == '-') {
                        return false;
                    }
                }
            }
            return true;
        }

        boolean estPobeda(char znak) {
            for (int i = 0; i < 3; i++) {
                if (kletki[i][0] == znak && kletki[i][1] == znak && kletki[i][2] == znak) {
                    return true;
                }
                if (kletki[0][i] == znak && kletki[1][i] == znak && kletki[2][i] == znak) {
                    return true;
                }
            }
            if (kletki[0][0] == znak && kletki[1][1] == znak && kletki[2][2] == znak) {
                return true;
            }
            if (kletki[0][2] == znak && kletki[1][1] == znak && kletki[2][0] == znak) {
                return true;
            }
            return false;
        }

        boolean postavitZnak(int stroka, int stolbec, char znak) {
            if (kletki[stroka][stolbec] == '-') {
                kletki[stroka][stolbec] = znak;
                return true;
            }
            return false;
        }
    }

    static class Partiya {
        Pole pole;
        Igrok igrok1;
        Igrok igrok2;
        Igrok tekushiyIgrok;

        Partiya() {
            pole = new Pole();
            igrok1 = new ChelovekIgrok("Игрок 1", 'X');
            igrok2 = new ChelovekIgrok("Игрок 2", 'O');
            tekushiyIgrok = igrok1;
        }

        void nachatPartiyu() {
            System.out.println("Игрок 1 (X) и Игрок 2 (O) — начинаем игру!");
            System.out.println("Игрок 1 (X) ходит первым.");
            pole.vyvestiPole();
            while (true) {
                tekushiyIgrok.sdelatHod(pole);
                if (proveritPobeduIliNichyu()) {
                    pole.vyvestiPole();
                    break;
                }
                pole.vyvestiPole();
                smenitIgroka();
            }
        }

        void smenitIgroka() {
            if (tekushiyIgrok == igrok1) {
                tekushiyIgrok = igrok2;
            } else {
                tekushiyIgrok = igrok1;
            }
        }

        boolean proveritPobeduIliNichyu() {
            if (pole.estPobeda(tekushiyIgrok.znak)) {
                System.out.println(tekushiyIgrok.imya + " (" + tekushiyIgrok.znak + ") победил!");
                return true;
            }
            if (pole.poleZapolneno()) {
                System.out.println("Ничья!");
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Partiya partiya = new Partiya();
        partiya.nachatPartiyu();
    }
}
