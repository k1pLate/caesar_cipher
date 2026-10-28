package com.javarush.caesar.core;

import com.javarush.caesar.exception.CipherException;
import com.javarush.caesar.service.FileManager;

import java.util.Arrays;
import java.util.Scanner;

public class Cipher {

    private final char[] alphabet;
    private final int[] indexes;
    private Scanner scanner;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;

        /// Индексируем наш алфавит для быстрого поиска по позиции

        indexes = new int[Character.MAX_VALUE + 1];

        Arrays.fill(indexes, -1);

        for (int i = 0; i < alphabet.length; i++) {
            indexes[alphabet[i]] = i;
        }
    }

    public String encrypt(String text, int shift) {
        /// Сдвигаем каждый символ алфавита на shift позиций вперед по кругу
        /// символ, которого нет в алфавите, пропускаем
        /// позицию символа ищем быстрее, чем перебором всего массива

        /// Метод перебора массива

//        char[] charArray = text.toCharArray();
//
//        for (int i = 0; i < charArray.length; i++) {
//            for (int j = 0; j < alphabet.length; j++) {
//                if (Character.toLowerCase(charArray[i]) == alphabet[j]) {
//                    char c = alphabet[j + shift];
//                    if (Character.isUpperCase(charArray[i])) {
//                        charArray[i] = Character.toUpperCase(c);
//                    } else {
//                        charArray[i] = c;
//                    }
//                    break;
//                }
//            }
//        }
//
//        return new String(charArray);

        /// Метод поиска позиции символа

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isUpperCase(text.charAt(i))) {
                c = Character.toLowerCase(c);
            }

            int index = indexes[c];

            if (index == -1) {
                result.append(text.charAt(i));
            } else {
                int newIndex = (index + shift) % alphabet.length;
                if (Character.isUpperCase(text.charAt(i))) {
                    result.append(Character.toUpperCase(alphabet[newIndex]));
                } else {
                    result.append(alphabet[newIndex]);
                }
            }
        }

        return result.toString();
    }

    public String decrypt(String encryptedText, int shift) throws CipherException {
        // TODO сдвинуть каждый символ на shift позиций назад по кругу
        //  символ, которого нет в алфавите, - ошибка: бросаем CipherException
        throw new UnsupportedOperationException("Метод decrypt пока не написан");
    }
}
