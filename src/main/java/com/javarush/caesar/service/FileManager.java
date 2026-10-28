package com.javarush.caesar.service;

import com.javarush.caesar.exception.CipherException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public String readFile(String filePath) throws CipherException {
        /// Читаем файл в кодировке UTF-8 через Path и Files
        /// IOException заворачиваем в CipherException с понятным сообщением

        try {
            return Files.readString(Path.of(filePath));
        }  catch (IOException e) {
            throw new CipherException("Ошибка! Файл не найден.");
        }
    }

    public void writeFile(String content, String filePath) throws CipherException {
        /// Записываем текст в файл, исходный файл не переписываем

        try {
            Files.write(Paths.get(filePath), content.getBytes());
        } catch (IOException e) {
            throw new CipherException("Ошибка!");
        }
    }
}
