package com.javarush.caesar.core;

import com.javarush.caesar.exception.CipherException;

public class BruteForce {

    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
        /// Перебираем все ключи от 1 до alphabet.length - 1
        /// для каждого ключа расшифровываем текст и оцениваем, похож ли он на русский:
        /// большее кол-во пробелов в расшифрованном тексте
        /// возвращаем вариант с лучшей оценкой

        Cipher cipher = new Cipher(alphabet);
        int maxAmountOfSpace = 0;
        int rightKey = -1;

        for (int i = 1; i <= alphabet.length - 1; i++) {
            int spaceCounter = 0;

            char[] textArray = cipher.decrypt(encryptedText, i).toCharArray();

            for (int j = 0; j < textArray.length; j++) {
                if (textArray[i] == ' ') {
                    spaceCounter++;
                }
            }

            if (spaceCounter > maxAmountOfSpace) {
                maxAmountOfSpace = spaceCounter;
                rightKey = i;
            }
        }

        if (rightKey == -1) {
            System.out.println("Не смогли расшифровать текст. Извините...");
            return encryptedText;
        }

        return cipher.decrypt(encryptedText, rightKey);
    }
}
