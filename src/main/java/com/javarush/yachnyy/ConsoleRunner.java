package com.javarush.yachnyy;

import com.javarush.yachnyy.controllers.MainController;
import com.javarush.yachnyy.entity.Result;
import com.javarush.yachnyy.exceptions.AppException;

import java.util.Scanner;

public class ConsoleRunner {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MainController controller = new MainController();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String userChoice = scanner.nextLine();

            try {
                switch (userChoice) {
                    case "1" -> encrypt();
                    case "2" -> decrypt();
                    case "3" -> bruteForce();
                    case "4" -> analyzer();
                    case "0" -> exit();
                    default -> System.out.println("Unknown command\n");
                }
            } catch (Exception e) {
                throw new AppException("Неверная команда: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                1. Encrypt text
                2. Decrypt text with a key
                3. Decrypt text (Brute Force)
                4. Decrypt text (Statistic analyzer)
                0. Exit
                Your choice:
                """);
    }

    private static String askUser(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    private static void exit() {
        System.out.println("Good luck and goodbye !");
        System.exit(0);
    }

    private static void encrypt() {
        String fileIn = askUser("Input file path: ");
        String fileOut = askUser("Output file path: ");
        String key = askUser("Key: ");

        Result result = controller.runAction("encrypt", new String[] {fileIn, fileOut, key});
        System.out.println();
    }

    private static void decrypt() {

    }

    private static void bruteForce() {

    }

    private static void analyzer() {

    }
}
