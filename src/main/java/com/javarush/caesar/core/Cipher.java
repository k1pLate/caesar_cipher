package com.javarush.caesar.core;

import java.util.Arrays;

public class Cipher {

    private final char[] alphabet;
    private final int[] indexes;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;

        /// Индексируем наш алфавит для быстрого поиска по позиции

        indexes = new int[Character.MAX_VALUE + 1];

        Arrays.fill(indexes, -1);

        for (int i = 0; i < alphabet.length; i++) {
            indexes[alphabet[i]] = i;
        }
    }

    /**
     * Сдвигаем каждый символ алфавита на shift позиций вперед по кругу
     * символ, которого нет в алфавите, пропускаем
     * позицию символа ищем быстрее, чем перебором всего массива
     * Метод поиска позиции символа
     */
    public String encrypt(String text, int shift) {


        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            c = Character.toLowerCase(c);

            int index = indexes[c];

            if (index == -1) {
                result.append(c);
            } else {
                int newIndex = (index + shift) % alphabet.length;
                result.append(alphabet[newIndex]);
            }
        }

        return result.toString();
    }

    /**
     * Сдвигаем каждый символ на shift позиций назад по кругу
     * символ, которого нет в алфавите, - оставляем как есть
     */
    public String decrypt(String encryptedText, int shift) {
        return encrypt(encryptedText, alphabet.length - shift);
    }
}
