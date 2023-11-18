package org.example;

import java.util.Arrays;
import java.util.Random;

public class ImmutableMatrix extends Matrix {

    public ImmutableMatrix() {
        super();
    }

    public ImmutableMatrix(int rows, int columns) {
        super(rows, columns);
    }

    public ImmutableMatrix(double[][] anotherMatrix) {
        super(anotherMatrix);
    }

    public ImmutableMatrix(Matrix anotherMatrix) {
        super(anotherMatrix);
    }

    @Override
    public ImmutableMatrix transposeMatrix() {
        int[] dimensions = getDim();
        double[][] transposedData = new double[dimensions[1]][dimensions[0]];

        for (int i = 0; i < dimensions[0]; i++) {
            for (int j = 0; j < dimensions[1]; j++) {
                transposedData[j][i] = this.getElement(i+1,j+1);
            }
        }

        return new ImmutableMatrix(transposedData);
    }


    public static ImmutableMatrix fromVector(double[] vector) {
        int dim = vector.length;
        double[][] data = new double[dim][dim];

        for (int i = 0; i < dim; i++) {
            data[i][i] = vector[i];
        }

        return new ImmutableMatrix(data);
    }

    public static ImmutableMatrix identityMatrix(int dim) {
        double[][] data = new double[dim][dim];

        for (int i = 0; i < dim; i++) {
            data[i][i] = 1;
        }

        return new ImmutableMatrix(data);
    }

    public static ImmutableMatrix createRowMatrix(int columns, int min, int max) {
        if (columns < 1) {
            throw new IllegalArgumentException("Number of columns must be positive.");
        }

        Random random = new Random();
        double[][] data = new double[1][columns];

        for (int j = 0; j < columns; j++) {
            data[0][j] = random.nextDouble() * (max - min) + min;
        }

        return new ImmutableMatrix(data);
    }

    public static ImmutableMatrix createColumnMatrix(int rows, int min, int max) {
        if (rows < 1) {
            throw new IllegalArgumentException("Number of rows must be positive.");
        }

        Random random = new Random();
        double[][] data = new double[rows][1];

        for (int j = 0; j < rows; j++) {
            data[j][0] = random.nextDouble() * (max - min) + min;
        }

        return new ImmutableMatrix(data);
    }

    @Override
    public ImmutableMatrix upperMatrix(){
        return new ImmutableMatrix(theLUDecomposition()[1]);
        //this.matrix = theLUDecomposition()[1];
    }


    @Override
    public ImmutableMatrix lowerMatrix(){
        return new ImmutableMatrix(theLUDecomposition()[0]);
    }

    @Override
    public void setZeros() {
        throw new UnsupportedOperationException("Cannot set zeros in an immutable matrix");
    }

    @Override
    public void setFromConsole() {
        throw new UnsupportedOperationException("Cannot set from console in an immutable matrix");
    }

    @Override
    public void setRandom(int min, int max) {
        throw new UnsupportedOperationException("Cannot set random values in an immutable matrix");
    }

    @Override
    public void setOneElement(int row, int column, double element) {
        throw new UnsupportedOperationException("Cannot set one element in an immutable matrix");
    }

    @Override
    public void addEdit(Matrix anotherMatrix) {
        throw new UnsupportedOperationException("Cannot add or edit in an immutable matrix");
    }

    @Override
    public void multiplyToScalarEdit(double scalar) {
        throw new UnsupportedOperationException("Cannot add or edit in an immutable matrix");
    }

    @Override
    public void multiplyMatrixesEdit(Matrix anotherMatrix) {
        throw new UnsupportedOperationException("Cannot add or edit in an immutable matrix");
    }

}