package com.javarush.caesar.service;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Validator {

    public boolean isValidKey(int key, char[] alphabet) {
        /// Ключ допустим от 0 до alphabet.length - 1

        return key >= 1 && key <= alphabet.length - 1;
    }

    public boolean isFileExists(String filePath) {
        /// Проверяем, что путь существует и ведет к файлу (папка не подходит)
        return Files.exists(Paths.get(filePath));
    }
}
