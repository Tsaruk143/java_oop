package org.example;


import java.awt.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!\n");
        double[][] data = {{1, 2, 3, 4}, {2, 3, 1, 4}, {2, 1, 3, 4}, {4, 3, 2, 1}};
        Matrix matrix = new Matrix(data);
        matrix.printMatrix();

        double[][] data1 = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        ImmutableMatrix matrix_1 = new ImmutableMatrix(data1);
        //matrix_1.printMatrix();

//        matrix.multiplyMatrixesEdit(matrix_1);
//        matrix.printMatrix();

//        ImmutableMatrix res = new ImmutableMatrix(matrix_1.multiplyMatrixesNew(matrix));
//        res.printMatrix();

        Matrix inv = new Matrix(matrix.getInverseMatrix());
        inv.printMatrix();
        matrix.printMatrix();


//        Matrix t = new Matrix(matrix.multiplyMatrixesNew(inv));
//        t.printMatrix();
//
//        Matrix v = new Matrix(inv.multiplyMatrixesNew(matrix));
//        v.printMatrix();










        
        
        
        
        
        



//        ImmutableMatrix res = new ImmutableMatrix(matrix1.addNew(matrix));
//        res.printMatrix();
//        matrix1.printMatrix();
//
//        ImmutableMatrix res1 = new ImmutableMatrix(matrix1.multiplyMatrixesNew(matrix));
//        res1.printMatrix();
//
//
//        ImmutableMatrix trans = matrix1.transposeMatrix();
//        trans.printMatrix();
//        matrix1.printMatrix();
//
//        double[] vector = {1, 2,3,4};
//        ImmutableMatrix fv = ImmutableMatrix.fromVector(vector);
//        fv.printMatrix();

//        Matrix in = matrix.getInverseMatrix();
//        in.printMatrix();

//        Matrix lu = matrix.lowerMatrix();
//        lu.printMatrix();

//        Matrix res = new Matrix(matrix1.addNew(matrix));
        //res.printMatrix();

//        Matrix res1 = new Matrix(matrix1.multiplyToScalarNew(3));
        //res1.printMatrix();


        //matrix1.multiplyMatrixesEdit(matrix);
        //matrix1.printMatrix();

//        Matrix res2 = new Matrix(matrix.multiplyMatrixesNew(matrix1));
//        res2.printMatrix();
//        matrix.printMatrix();
//
//        double[] vector = {1, 2,3,4};
//        Matrix dig = Matrix.fromVector(vector);
//        dig.printMatrix();
//
//        Matrix id = Matrix.identityMatrix(4);
//        id.printMatrix();

//        Matrix transpose = matrix.transposeMatrix();
//        transpose.printMatrix();
//        matrix.printMatrix();


//        matrix.addEdit(matrix1);
//        matrix.printMatrix();
//
//        matrix.upperMatrix();
//        matrix.printMatrix();
//
//        double[] row = matrix1.getRow(1);
//        System.out.println(Arrays.toString(row));
//
//        double[] line = matrix.getColumn(4);
//        System.out.println(Arrays.toString(line));
//
//        matrix1.multiplyToScalarEdit(5);
//        matrix1.printMatrix();

    }
}