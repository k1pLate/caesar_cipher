package com.javarush.caesar.service;

import java.util.Scanner;

public class Manager {
    private final Scanner scanner;
    private final Validator validator = new Validator();
    private final char[] alphabet;

    public Manager(Scanner scanner, char[] alphabet) {
        this.scanner = scanner;
        this.alphabet = alphabet;
    }

    public String getOutputFile() {
        String outputFile;

        while (true) {
            System.out.print("Путь к файлу для результата: ");
            outputFile = scanner.nextLine();
            if (validator.isFileExists(outputFile)) {
                System.out.println("Файл уже существует " + outputFile);
            } else {
                break;
            }
        }
        return outputFile;
    }

    public int getKey() {
        int key;
        while (true) {
            System.out.print("Ключ шифрования: ");
            try {
                key = Integer.parseInt(scanner.nextLine());
                if (validator.isValidKey(key, alphabet)) {
                    break;
                } else {
                    System.out.println("Неверный ключ! Ключ должен быть от 1 до " + (alphabet.length - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Ключ должен быть ЧИСЛОМ от 1 до " + (alphabet.length - 1));
            }
        }
        return key;
    }

    public String getInputFile(String s) {
        String inputFile;

        while (true) {
            System.out.print(s);
            inputFile = scanner.nextLine();
            if (validator.isFileExists(inputFile)) {
                break;
            } else {
                System.out.println("Неверный путь!");
            }
        }
        return inputFile;
    }
}
