package com.javarush.caesar;

import com.javarush.caesar.core.BruteForce;
import com.javarush.caesar.core.Cipher;
import com.javarush.caesar.exception.CipherException;
import com.javarush.caesar.service.FileManager;
import com.javarush.caesar.service.Manager;

import java.util.Scanner;

public class MainApp {

    private static final char[] ALPHABET = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
            'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '
    };

    private final Scanner scanner = new Scanner(System.in);
    private final FileManager fileManager = new FileManager();
    private final Cipher cipher = new Cipher(ALPHABET);
    private final BruteForce bruteForce = new BruteForce();
    private final Manager manager = new Manager(scanner, ALPHABET);

    public static void main(String[] args) {
        new MainApp().run();
    }

    private void run() {
        System.out.println("Шифр Цезаря: шифрование и расшифровка файлов");
        while (true) {
            printMenu();
            String choice = ask("Выберите действие: ");
            try {
                switch (choice) {
                    case "1":
                        encryptFile();
                        break;
                    case "2":
                        decryptFile();
                        break;
                    case "3":
                        bruteForceFile();
                        break;
                    case "0":
                    case "exit":
                        System.out.println("До свидания!");
                        return;
                    default:
                        System.out.println("Нет такого пункта: " + choice);
                }
            } catch (CipherException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Зашифровать файл");
        System.out.println("2. Расшифровать файл с ключом");
        System.out.println("3. Взломать перебором ключей");
        System.out.println("0. Выход (или exit)");
    }

    /**
     * Запрашиваем исходный файл, файл для результата и ключ
     * проверяем их через validator, читаем текст через fileManager,
     * зашифровываем через cipher и записываем результат
     */
    private void encryptFile() throws CipherException {

        String inputFile;
        String outputFile;
        int key;

        inputFile = manager.getInputFile("Путь к файлу, который надо зашифровать: ");
        outputFile = manager.getOutputFile();
        key = manager.getKey();

        String inputText = fileManager.readFile(inputFile);
        fileManager.writeFile(cipher.encrypt(inputText, key), outputFile);
    }

    /**
     * То же, что в encryptFile, только расшифровка
     */
    private void decryptFile() throws CipherException {
        String inputFile;
        String outputFile;
        int key;

        inputFile = manager.getInputFile("Путь к файлу который надо расшифровать: ");
        outputFile = manager.getOutputFile();
        key = manager.getKey();

        String inputText = fileManager.readFile(inputFile);
        fileManager.writeFile(cipher.decrypt(inputText, key), outputFile);
    }

    /**
     * Запрашиваем зашифрованный файл и файл для результата,
     * подбираем ключ через bruteForce и записываем расшифрованный текст
     */
    private void bruteForceFile() throws CipherException {
        String inputFile;
        String outputFile;

        inputFile = manager.getInputFile("Путь к файлу который надо расшифровать: ");
        outputFile = manager.getOutputFile();

        String inputText = fileManager.readFile(inputFile);
        fileManager.writeFile(bruteForce.decryptByBruteForce(inputText, ALPHABET), outputFile);
    }


    private String ask(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
