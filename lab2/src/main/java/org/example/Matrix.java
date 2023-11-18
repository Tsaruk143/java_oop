package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Matrix {
    private double[][] matrix;

    // Пункт 2: конструктор стоврення порожньої матриці
    public Matrix() {
        matrix = new double[0][0];
    }

    // Пункт 2: конструктор матриці для заданої розмірності
    public Matrix(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("Matrix dimensions must be positive.");
        }
        matrix = new double[rows][columns];
    }

    // Пункт 2: конструктор матриці що робить копію матриці (створює нову і копією туди задану)
    public Matrix(double[][] anotherMatrix) {
        int rows = anotherMatrix.length;
        if (rows == 0){
            throw new IllegalArgumentException("Empty matrix.");
        }
        int columns = anotherMatrix[0].length;

        matrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = anotherMatrix[i][j];
            }
        }
    }

    //Копія матриці
    public Matrix(Matrix anotherMatrix) {
        int rows = anotherMatrix.getDim()[0];
        if (rows == 0){
            throw new IllegalArgumentException("Empty matrix.");
        }
        int columns = anotherMatrix.getDim()[1];

        matrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = anotherMatrix.getElement(i+1,j+1);
            }
        }
    }

    // Пункт 3: заповнення матриці нулями
    public void setZeros() {
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i], 0);
        }
    }

    // Пункт 3: заповнення матриці введенням користувача з консолі
    public void setFromConsole() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("Enter value for row " + (i + 1) + ", column " + (j + 1) + ": ");
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    // Пункт 3: заповнення матриці рандомними числами (числа генеруються у проміжку між двома заданими)
    public void setRandom(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min value must be less than or equal to max value.");
        }
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextDouble((max - min) + 1) + min;
            }
        }
    }

    // Пункт 3: заповнення одного елемента матриці (його заміна)
    public void setOneElement(int row, int column, double element) {
        if (row < 1 || column < 1 || row > matrix.length || column > matrix[0].length) {
            throw new IllegalArgumentException("Index out of range.");
        }
        matrix[row-1][column-1] = element;
    }

    // Пункт 4: знайти заданий елемент (дані його "координами" і треба дістати значення)
    public double getElement(int row, int column){
        if (row < 1 || column < 1 || row > matrix.length || column > matrix[0].length){
            throw new IllegalArgumentException("Index out of range");
        }
        double el = matrix[row-1][column-1];
        return el;
    }

    // Пункт 4: знайти заданий рядок (є номер рядка і треба повернути масив з елементами цього рядка)
    public double[] getRow(int row){
        if (row < 1 || row > matrix.length){
            throw new IllegalArgumentException("Index out of range");
        }
        double[] frow = new double[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            frow[i] = matrix[row-1][i];
        }
        return frow;
    }

    // Пункт 4: знайти заданий стовпчик (дано номер стовпчина, задача повернути його елементи в масиві)
    public double[] getColumn(int column){
        if (column < 1 || column > matrix[0].length){
            throw new IllegalArgumentException("Index out of range");
        }
        double[] fcol = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            fcol[i] = matrix[i][column-1];
        }
        return fcol;
    }

    // Пункт 5: знайти розмірність матриці (масив з двох чисел)
    public int[] getDim(){
        int[] dim = new int[2];
        int rows = matrix.length;
        if (rows == 0){ dim[0] = 0; dim[1] = 0; return dim;}
        int columns = matrix[0].length;
        dim[0] = rows;
        dim[1] = columns;
        return dim;
    }


    // Пункт 6: перевизначення методу equals
    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Matrix matrix = (Matrix) obj;
        return (getDim()[0] == matrix.getDim()[0] && getDim()[1] == matrix.getDim()[1] && Arrays.deepEquals(getData(), matrix.getData()));
    }

    // Пункт 6: перевизначення методу hashCode
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }

    //Пункт 8 додавання матриць (зміна матриці)
    public void addEdit(Matrix anotherMatrix) {
//        if (getDim()[0] != anotherMatrix.getDim()[0] || getDim()[1] != anotherMatrix.getDim()[1]){
//            throw new IllegalArgumentException("Matrix dimensions must be the same for addition.");
//        }
//
//        int rows = matrix.length;
//        int columns = matrix[0].length;
//        Matrix result = new Matrix(rows, columns);
        int[] dimension = getDim();
        for (int i = 0; i < dimension[0]; i++) {
            for (int j = 0; j < dimension[1]; j++) {
                matrix[i][j] = matrix[i][j] + anotherMatrix.matrix[i][j];
            }
        }
    }

    //Пункт 8 додавання матриць (створення нової матриці)
    public double[][] addNew(Matrix anotherMatrix) {
        if (getDim()[0] != anotherMatrix.getDim()[0] || getDim()[1] != anotherMatrix.getDim()[1]) {
            throw new IllegalArgumentException("Matrix dimensions must be the same for addition.");
        }

        int[] dimension = getDim();
        int row = dimension[0];
        int column = dimension[1];
        double[][] result = new double[row][column];
        for (int i = 0; i < dimension[0]; i++) {
            for (int j = 0; j < dimension[1]; j++) {
                result[i][j] = matrix[i][j] + anotherMatrix.matrix[i][j];
            }
        }
        return result;
    }


    // Пункт 8 множення матриці на скаляр (Зміна матриці)
    public void multiplyToScalarEdit(double scalar) {
        int[] dimension = getDim();
        double[][] result = new double[dimension[0]][dimension[1]];
        for (int i = 0; i < dimension[0]; i++) {
            for (int j = 0; j < dimension[1]; j++) {
                result[i][j] = scalar*matrix[i][j];
            }
        }
        this.matrix = result;
    }

    // Пункт 8 множення матриці на скаляр (Створення нової матриці)
    public double[][] multiplyToScalarNew(double scalar) {
        int[] dimension = getDim();
        double[][] result = new double[dimension[0]][dimension[1]];
        for (int i = 0; i < dimension[0]; i++) {
            for (int j = 0; j < dimension[1]; j++) {
                result[i][j] = scalar*matrix[i][j];
            }
        }
        return result;
    }

    //Пункт 9 множення двох матриць (Зміна матриці)
    public void multiplyMatrixesEdit(Matrix anotherMatrix) {
        int[] firstDim = getDim();
        int[] secondDim = anotherMatrix.getDim();

        if (firstDim[1] != secondDim[0]) {
            throw new IllegalArgumentException("Number of columns in the first matrix must be equal to the number of rows in the second matrix for matrix multiplication.");
        }
        double[][] result = new double[firstDim[0]] [secondDim[1]];
        for (int i = 0; i < firstDim[0]; i++) {
            for (int j = 0; j < secondDim[1]; j++) {
                double sum = 0;
                for (int k = 0; k < firstDim[1]; k++) {
                    sum += matrix[i][k] * anotherMatrix.matrix[k][j];
                }
                result[i][j] = sum;
            }
        }
        this.matrix = result;
    }

    //Пункт 9 множення двох матриць ()
    public double[][] multiplyMatrixesNew(Matrix anotherMatrix) {
        int[] firstDim = getDim();
        int[] secondDim = anotherMatrix.getDim();

        if (firstDim[1] != secondDim[0]) {
            throw new IllegalArgumentException("Number of columns in the first matrix must be equal to the number of rows in the second matrix for matrix multiplication.");
        }
        double[][] result = new double[firstDim[0]][secondDim[1]];
        for (int i = 0; i < firstDim[0]; i++) {
            for (int j = 0; j < secondDim[1]; j++) {
                double sum = 0;
                for (int k = 0; k < firstDim[1]; k++) {
                    sum += matrix[i][k] * anotherMatrix.matrix[k][j];
                }
                result[i][j] =  sum;
            }
        }
        return result;
    }


    // Пункт 10 Транспонування матриці (створення нової матриці)
    public Matrix transposeMatrix() {
        int[] dismension = getDim();
        Matrix transposedMatrix = new Matrix(dismension[1], dismension[0]);
        for (int i = 0; i < dismension[0]; i++) {
            for (int j = 0; j < dismension[1]; j++) {
                transposedMatrix.matrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }

    // Пункт 11 створення діагональної матриці на основі вектора
    public static Matrix fromVector(double[] vector) {
        int dim = vector.length;
        Matrix result = new Matrix(dim, dim);

        for (int i = 0; i < dim; i++) {
            result.matrix[i][i] = vector[i];
        }
        return result;
    }
    //Пункт 12 одинична матриця
    public static Matrix identityMatrix(int dim){
        Matrix identity = new Matrix(dim, dim);
        for (int i = 0; i < dim; i++) {
            identity.matrix[i][i] = 1;
        }
        return identity;
    }

    //Пункт 13 Створення матриці-рядка
    public static Matrix createRowMatrix(int columns, int min, int max) {
        if (columns < 1) {
            throw new IllegalArgumentException("Number of columns must be positive.");
        }

        Random random = new Random();
        Matrix rowMatrix = new Matrix(1, columns);

        for (int j = 0; j < columns; j++) {
            rowMatrix.matrix[0][j] = random.nextDouble((max - min) + 1) + min;
        }

        return rowMatrix;
    }

    //Пункт 14 створення матриці-стовпчика
    public static Matrix createColumnMatrix(int rows, int min, int max) {
        if (rows < 1) {
            throw new IllegalArgumentException("Number of columns must be positive.");
        }

        Random random = new Random();
        Matrix columnMatrix = new Matrix(1, rows);

        for (int j = 0; j < rows; j++) {
            columnMatrix.matrix[j][0] = random.nextDouble((max - min) + 1) + min;
        }

        return columnMatrix;
    }


    // Методи на 6 балів

    //Допоміжний метод (по факту головний) для LU декомнозиції матриці (він відразу створює 2 маттриці - верхню і нижню і зберігає їх у масиві)
    public double[][][] theLUDecomposition() {
        int n = matrix.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];

        // один з масивів ініціалізується як одинична матриця для LU декомпозиції (модифікований метод Гауса)
        for (int i = 0; i < n; i++) {
            L[i][i] = 1.0;
        }

        // реалізація LU декомпозиції (розділеня однієї матриці на дві трикутні, при тому їх добуток буде давати нашу матрицю)
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                double sum = 0.0;
                for (int k = 0; k < i; k++) {
                    sum += L[i][k] * U[k][j];
                }
                U[i][j] = matrix[i][j] - sum;
            }
            for (int i = j + 1; i < n; i++) {
                double sum = 0.0;
                for (int k = 0; k < j; k++) {
                    sum += L[i][k] * U[k][j];
                }
                L[i][j] = (matrix[i][j] - sum) / U[j][j];
            }
        }

        // Збереження двох трикутних матриць до масиву
        double[][][] result = new double[3][][];
        result[0] = L;
        result[1] = U;
        return result;
    }

    // З попереднього методу дістає верхню трикутну матрицю для користувача (зміна матриці)
    public Matrix upperMatrix(){
        return new Matrix(theLUDecomposition()[1]);
        //this.matrix = theLUDecomposition()[1];
    }


    // Дістає нижню трикутну матрицю з того ж методу (зміна матриці)
    public Matrix lowerMatrix(){
        return new Matrix(theLUDecomposition()[0]);
    }



    // Пункт 2: створення оберненої матриці (нова матриця)
    public Matrix getInverseMatrix() {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square to have an inverse.");
        } // Перевірка чи матриця квадратна

        int dim = matrix.length;
        Matrix inverse = new Matrix(dim, dim);
        double[][] dt = getData();

        // Cтворення одиничної матриці
        for (int i = 0; i < dim; i++) {
            inverse.matrix[i][i] = 1;
        }

        // реалізація агоритму Гауса-Жордана
        for (int i = 0; i < dim; i++) {
            // Якщо діагональний елемент дорівнює нулю, ми його заміняємо на інший елемент стовпчика
            if (matrix[i][i] == 0) {
                for (int j = i + 1; j < dim; j++) {
                    if (matrix[j][i] != 0) {
                        swapRows(i, j);
                        inverse.swapRows(i, j);
                        break;
                    }
                }
            }

            // ділимо елементи на діагональний (ми починали з одиничної матриці, і для справедливого перетворення вона ж має вийти вкінці з ашої сторони)
            double divisor = matrix[i][i];
            for (int j = 0; j < dim; j++) {
                matrix[i][j] /= divisor;
                inverse.matrix[i][j] /= divisor;
            }

            // Закінчення кроків алгоритму Гауса-Жордана (зводяться елемнти до нуля в допоміжній матриці (нашій початковій) та завершується оброблення рядка оберненої матриці)
            for (int j = 0; j < dim; j++) {
                if (j != i) {
                    double factor = matrix[j][i];
                    for (int k = 0; k < dim; k++) {
                        matrix[j][k] -= factor * matrix[i][k];
                        inverse.matrix[j][k] -= factor * inverse.matrix[i][k];
                    }
                }
            }
        }

        return inverse;
    }

    // зміна двох значень (допоміжний метод)
    private void swapRows(int i, int j) {
        double[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }

    private void swapRow(double[][] matrix, int i, int j) {
        double[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }


    // Вивід матриці у консоль
    public void printMatrix() {
        int[] dim = getDim();

        System.out.println("Matrix:");
        for (int i = 0; i < dim[0]; i++) {
            for (int j = 0; j < dim[1]; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public double[][] getData() {
        int rows = matrix.length;
        int columns = matrix[0].length;

        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[i][j];
            }
        }

        return result;
    }
}
