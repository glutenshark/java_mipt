package HT;
/**
 * Задание 1. Проверка числа на палиндром

Напишите программу на Java, которая будет проверять, является ли число палиндромом.
Возможно ли провести проверку без превращения числа в строку?
 * 
 */
public class Zadanie_1 {
    // Сделал без преобразования  
    public static boolean isPalindrome(int chislo) {
        // Отрицательное число не палиндром.
        if (chislo < 0) {
            return false;
        }
        // Сохраняем исходное значенеи,  потом буду с ним сравнивать.
        int originalChislo = chislo;
        int perevernut = 0;
        // Переворачиваю число: возьму последнюю цифру и добавлю в новое число.
        while (chislo != 0) {
            int cifra = chislo % 10;            // последняя цифра
            perevernut = perevernut * 10 + cifra; // перенос цифры в конец 
            chislo = chislo / 10;               // отбираем последнюю цифру у исходного значения
        }
        // Сравню исходное и новое
        return originalChislo == perevernut;
    }

    public static void main(String[] args) {
        // Прогон примеров:
        System.out.println(isPalindrome(121));   // должно быть true (121 палиндром)
        System.out.println(isPalindrome(-121));  // должно быть false (-121 не палиндром)
        System.out.println(isPalindrome(10));    // должно быть false (10 не палиндром)
    }
}
