package com.javarush.caesar.service;

import java.util.Scanner;

public class Manager {
    private final Scanner scanner = new Scanner(System.in);
    private final Validator validator = new Validator();
    private final char[] alphabet;

    public Manager(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public String getOutputFile() {
        System.out.print("Путь к файлу для результата: ");
        return scanner.nextLine();
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
