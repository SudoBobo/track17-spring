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
                sum += getSum(sc.nextLine());
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
                isLastLine = !sc.hasNext();
                concatenatedWords.append(concatWordsInLine(line, isLastLine));
            }
        }
        return concatenatedWords.toString();
    }


    private static String concatWordsInLine(String line, boolean isLastLine) {
        StringBuilder concatenatedLine = new StringBuilder();
        char []lineArray = line.toCharArray();
        for (int i = 0; i < lineArray.length; i++) {
            if (!Character.isDigit(lineArray[i])) {
                concatenatedLine.append(lineArray[i]);

                if (((i + 1) < lineArray.length) && (Character.isDigit(lineArray[i + 1]))) {
                    concatenatedLine.append(" ");
                }
                if (((i + 1) == lineArray.length) && (!isLastLine)) {
                    concatenatedLine.append(" ");
                }
            }
        }
        return concatenatedLine.toString();
    }

    private static long getSum(String line) {
        long sum = 0;
        StringBuilder number = new StringBuilder();
        char []lineArray = line.toCharArray();
        for (char ch : lineArray) {
            if (Character.isDigit(ch)) {
                number.append(ch);
            } else {
                sum += extractNumber(number);
            }
        }

        sum += extractNumber(number);
        return sum;
    }

    private static long extractNumber(StringBuilder number) {
        long result = 0;
        if (!number.toString().isEmpty()) {
            result += Long.parseLong(number.toString());
            number.delete(0, number.length() - 1);
        }
        return result;
    }
}
