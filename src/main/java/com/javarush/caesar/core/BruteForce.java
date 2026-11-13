package com.javarush.caesar.core;

import com.javarush.caesar.exception.CipherException;

public class BruteForce {

    /**
     * Перебираем все ключи от 1 до alphabet.length - 1
     * для каждого ключа расшифровываем текст и оцениваем, похож ли он на русский:
     * большее кол-во пробелов в расшифрованном тексте
     * возвращаем вариант с лучшей оценкой
     */
    public String decryptByBruteForce(String encryptedText, char[] alphabet) throws CipherException {


        Cipher cipher = new Cipher(alphabet);
        int maxAmountOfSpace = 0;
        int rightKey = -1;

        for (int i = 1; i <= alphabet.length - 1; i++) {
            int spaceCounter = 0;

            char[] textArray = cipher.decrypt(encryptedText, i).toCharArray();

            for (char c : textArray) {
                if (c == ' ') {
                    spaceCounter++;
                }
            }

            if (spaceCounter > maxAmountOfSpace) {
                maxAmountOfSpace = spaceCounter;
                rightKey = i;
            }
        }

        if (rightKey == -1) {
            throw new CipherException("Не смогли расшифровать текст. Извините...");
        } else {
            System.out.println("Предположительный ключ - " + rightKey);
        }

        return cipher.decrypt(encryptedText, rightKey);
    }
}
