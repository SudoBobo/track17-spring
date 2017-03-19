package track.lessons.lesson1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Задание 1: Реализовать два метода
 * <p>
 * Формат файла: текстовый, на каждой его строке есть (или/или)
 * - целое число (int)
 * - текстовая строка
 * - пустая строка (пробелы)
 * <p>
 * <p>
 * Пример файла - words.txt в корне проекта
 * <p>
 * ******************************************************************************************
 * Пожалуйста, не меняйте сигнатуры методов! (название, аргументы, возвращаемое значение)
 * <p>
 * Можно дописывать новый код - вспомогательные методы, конструкторы, поля
 * <p>
 * ******************************************************************************************
 */
public class CountWords {

    /**
     * Метод на вход принимает объект File, изначально сумма = 0
     * Нужно пройти по всем строкам файла, и если в строке стоит целое число,
     * то надо добавить это число к сумме
     *
     * @param file - файл с данными
     * @return - целое число - сумма всех чисел из файла
     */
    public long countNumbers(File file) throws Exception {
        long sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner sc = new Scanner(br);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                if (isLong(nextLine)) {
                    sum += Long.parseLong(nextLine);
                }
            }
        }
        return sum;
    }


    /**
     * Метод на вход принимает объект File, изначально результат= ""
     * Нужно пройти по всем строкам файла, и если в строка не пустая и не число
     * то надо присоединить ее к результату через пробел
     *
     * @param file - файл с данными
     * @return - результирующая строка
     */
    public String concatWords(File file) throws Exception {
        StringBuilder concatenatedWords = new StringBuilder();
        String line;
        boolean isLastLine;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner sc = new Scanner(br);
            while (sc.hasNext()) {
                line = sc.nextLine();
                if ((!isLong(line)) && (!line.isEmpty())) {
                    concatenatedWords.append(line);
                    if (sc.hasNext()) {
                        concatenatedWords.append(" ");
                    }
                }
            }
        }
        return concatenatedWords.toString();
    }

    private static boolean isLong(String str) {
        try {
            long number = Long.parseLong(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
