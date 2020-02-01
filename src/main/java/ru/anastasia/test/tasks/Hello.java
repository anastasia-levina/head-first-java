package ru.anastasia.test.tasks;

import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        System.out.print("Введите своё имя: ");

        Scanner scan = new Scanner(System.in);
        String result = "";
        String str = "";
        while (scan.hasNext()) {
            str = scan.next();
            if(str.equals("все")) {
                break;
            }
            result = result + str;

        }
        System.out.println("Злравствуй, " + result);
    }
}
