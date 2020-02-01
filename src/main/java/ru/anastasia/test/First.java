package ru.anastasia.test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class First {
    private String first = "t";
    String second = "t";
    protected String third = "t";
    public String fourth = "t";


    public static void main(String[] args) {


        String result = "";
        boolean exit = false;
        while (!exit) {
            try {
                System.out.print("Введите любое целое число от 1 до 10: ");

                Scanner scan = new Scanner(System.in);

                int number = scan.nextInt();
                if (number < 0 || number > 10) {
                    throw new Exception("Это ошибка");
                }
                System.out.println("Вы ввели число " + number);
            /*while (number != 0) {
                result = result + "h";
                //number = number -1;
                number--;
            }*/
                for (int i = number; i != 0; i--) {
                    result = result + "h";
                }
                exit = true;
            } catch (InputMismatchException e) {
                System.out.println("вам необходимо ввести целое число, глупый человек");
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
        System.out.println(result);
        System.out.println("Программа все");

    }
}