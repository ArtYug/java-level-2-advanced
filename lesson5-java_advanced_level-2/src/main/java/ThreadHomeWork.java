import java.util.Arrays;
//                HomeWork 5 java advanced level
//        Необходимо написать два метода, которые делают следующее:
//        1) Создают одномерный длинный массив, например:
//        static final int SIZE = 10_000_000;
//        static final int HALF = size / 2;
//        float[] arr = new float[size];
//        2) Заполняют этот массив единицами.
//        3) Засекают время выполнения: long a = System.currentTimeMillis().
//        4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
//        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
//        Math.cos(0.4f + i / 2));
//        5) Проверяется время окончания метода System.currentTimeMillis().
//        6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).


public class ThreadHomeWork {
    public static final int SIZE = 10_000_000;
    public static final int HALF = SIZE / 2;
    public static float[] array = new float[SIZE];

    public static void main(String[] args) {
        firstMethod();
        secondMethod();
    }

    public static void firstMethod() {
        Arrays.fill(array, 1.0f);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) *
                    Math.cos(0.4f + i / 2.0));
        }
        System.out.println("One thread time: " +
                (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void secondMethod() {
        Arrays.fill(array, 1.0f);

        long startTime = System.currentTimeMillis();
        float[] rightSideArray = Arrays.copyOfRange(array, 0, HALF);
        float[] leftSideArray = Arrays.copyOfRange(array, HALF, array.length);

        @SuppressWarnings("DuplicatedCode") Thread rightSideThread = new Thread(() -> {
            for (int i = 0; i < rightSideArray.length; i++) {
                rightSideArray[i] = (float) (rightSideArray[i] * Math.sin(0.2f + i / 5.0)
                        * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        });
        @SuppressWarnings("DuplicatedCode") Thread leftSideThread = new Thread(() -> {
            for (int i = 0; i < leftSideArray.length; i++) {
                leftSideArray[i] = (float) (leftSideArray[i] * Math.sin(0.2f + i / 5.0)
                        * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        });
        rightSideThread.start();
        leftSideThread.start();
        try {
            rightSideThread.join();
            leftSideThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int a = rightSideArray.length;
        int b = leftSideArray.length;
        float[] result_A_and_B = new float[a + b];
        System.arraycopy(rightSideArray, 0, result_A_and_B, 0, a);
        System.arraycopy(leftSideArray, 0, result_A_and_B, a, b);
        System.out.println("Two thread time: " + (System.currentTimeMillis() -
                startTime) + " ms.");
    }
}
