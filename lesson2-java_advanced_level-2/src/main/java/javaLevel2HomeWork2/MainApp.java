package javaLevel2HomeWork2;
//                                        HomeWork2
//        1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4, при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//        2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
//        Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
//        должно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
//        3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException, и вывести результат расчета.


public class MainApp {
    public static void main(String[] args) throws MyArraySizeException {
        StringArray stringArray = new StringArray();
        String[][] array = {
                {"1", "3", "3", "5"},
                {"1", "2", "3", "6"},
                {"1", "2", "5", "7"},
                {"1", "2", "3", "8"}
        };
        stringArray.checkStringArrayForNumberedValues(array);
    }
}
