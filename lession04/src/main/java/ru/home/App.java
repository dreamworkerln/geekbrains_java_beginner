package ru.home;

import java.util.Locale;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        // Setting "." as decimal separator
        Locale.setDefault(Locale.ROOT);

        // 1-4 Задание
        Employee.test();

        System.out.println("\n\n\n\n");

        // 5-9 Задание
        TestAnimals.test();
    }
}





