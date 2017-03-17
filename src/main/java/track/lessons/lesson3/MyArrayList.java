package track.lessons.lesson3;

import java.util.NoSuchElementException;

import static java.lang.System.arraycopy;

/**
 * Должен наследовать List
 * <p>
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {
    private int[] array;

    public MyArrayList() {
        array = new int[1];
        positionOfLastElement = -1;
    }

    public MyArrayList(int capacity) {
        array = new int[capacity];
        positionOfLastElement = -1;
    }

    @Override
    void add(int item) {
        if (array.length == positionOfLastElement + 1) {
            int[] newArray = new int[2 * array.length + 1];
            arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[positionOfLastElement + 1] = item;
        positionOfLastElement++;
    }

    @Override
    public int remove(int idx) throws NoSuchElementException {
        if (isIndexValid(idx)) {
            int removed = array[idx];
            for (int i = idx; i < positionOfLastElement; i++) {
                array[i] = array[i + 1];
            }
            positionOfLastElement--;
            return removed;
        } else {
            throw new NoSuchElementException();
        }
    }


    @Override
    public int get(int idx) throws NoSuchElementException {
        if (isIndexValid(idx)) {
            return array[idx];
        } else {
            throw new NoSuchElementException();
        }
    }
}

