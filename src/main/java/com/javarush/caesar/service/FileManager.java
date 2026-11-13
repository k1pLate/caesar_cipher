package com.javarush.caesar.service;

import com.javarush.caesar.exception.CipherException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    /**
     * Читаем файл в кодировке UTF-8 через Path и Files
     * IOException заворачиваем в CipherException с понятным сообщением
     */
    public String readFile(String filePath) throws CipherException {


        try {
            return Files.readString(Path.of(filePath));
        } catch (IOException e) {
            throw new CipherException("Не удалось прочитать файл " + filePath, e);
        }
    }

    /**
     * Записываем текст в файл, исходный файл не переписываем
     */
    public void writeFile(String content, String filePath) throws CipherException {

        try {
            Files.writeString(Paths.get(filePath), content);
        } catch (IOException e) {
            throw new CipherException("Не удалось записать файл " + filePath, e);
        }
    }
}
