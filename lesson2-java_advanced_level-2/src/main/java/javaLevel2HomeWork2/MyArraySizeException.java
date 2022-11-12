package javaLevel2HomeWork2;

public class MyArraySizeException extends CheckMatrixNumberException {

    public MyArraySizeException(int row, int column, int[][] arr) {
        super("Invalid data in [" + row + ";" + column + "]: " + arr[row][column]);
    }
}
