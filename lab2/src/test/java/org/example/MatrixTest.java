package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    @Test
    void testDefaultConstructor() {
        Matrix matrix = new Matrix();
        assertNotNull(matrix);
        assertEquals(0, matrix.getDim()[0]);
        assertEquals(0, matrix.getDim()[1]);
    }

    @Test
    void testParameterizedConstructor() {
        int rows = 3;
        int columns = 4;
        Matrix matrix = new Matrix(rows, columns);
        assertNotNull(matrix);
        assertEquals(rows, matrix.getDim()[0]);
        assertEquals(columns, matrix.getDim()[1]);
    }


    @Test
    void testGetElement() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix matrix = new Matrix(data);

        assertEquals(5, matrix.getElement(2, 2));
        assertEquals(7, matrix.getElement(3, 1));
        assertThrows(IllegalArgumentException.class, () -> matrix.getElement(0, 2));
    }

    @Test
    void testGetRow() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix matrix = new Matrix(data);

        double[] expectedRow = {4, 5, 6};
        assertArrayEquals(expectedRow, matrix.getRow(2));
        assertThrows(IllegalArgumentException.class, () -> matrix.getRow(0));
    }

    @Test
    void testGetDim() {
        Matrix matrix = new Matrix(3, 4);
        int[] expectedDim = {3, 4};
        assertArrayEquals(expectedDim, matrix.getDim());
    }

    @Test
    void testEquals() {
        double[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data3 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);
        Matrix matrix3 = new Matrix(data3);

        assertTrue(matrix1.equals(matrix2));
        assertFalse(matrix1.equals(matrix3));
    }

    @Test
    void testHashCode() {
        double[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data3 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);
        Matrix matrix3 = new Matrix(data3);

        assertEquals(matrix1.hashCode(), matrix2.hashCode());
        assertNotEquals(matrix1.hashCode(), matrix3.hashCode());
    }

    @Test
    void testAddEdit() {
        double[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);

        matrix1.addEdit(matrix2);

        double[][] expectedSum = {{10, 10, 10}, {10, 10, 10}, {10, 10, 10}};
        assertArrayEquals(expectedSum, matrix1.getData());
    }

    @Test
    void testAddNew() {
        double[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);

        double[][] result = matrix1.addNew(matrix2);

        double[][] expectedSum = {{10, 10, 10}, {10, 10, 10}, {10, 10, 10}};
        assertArrayEquals(expectedSum, result);
    }

    @Test
    void testMultiplyToScalarEdit() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix matrix = new Matrix(data);

        matrix.multiplyToScalarEdit(2);

        double[][] expectedScaledMatrix = {{2, 4, 6}, {8, 10, 12}, {14, 16, 18}};
        assertArrayEquals(expectedScaledMatrix, matrix.getData());
    }

    @Test
    void testMultiplyToScalarNew() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix matrix = new Matrix(data);

        double[][] result = matrix.multiplyToScalarNew(2);

        double[][] expectedScaledMatrix = {{2, 4, 6}, {8, 10, 12}, {14, 16, 18}};
        assertArrayEquals(expectedScaledMatrix, result);
    }

    @Test
    void testMultiplyMatrixesEdit() {
        double[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);

        matrix1.multiplyMatrixesEdit(matrix2);

        double[][] expectedProduct = {{30, 24, 18}, {84, 69, 54}, {138, 114, 90}};
        assertArrayEquals(expectedProduct, matrix1.getData());
    }

    @Test
    void testMultiplyMatrixesNew() {
        double[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);

        double[][] result = matrix1.multiplyMatrixesNew(matrix2);

        double[][] expectedProduct = {{30, 24, 18}, {84, 69, 54}, {138, 114, 90}};
        assertArrayEquals(expectedProduct, result);
    }

    @Test
    void testTransposeMatrix() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix matrix = new Matrix(data);

        Matrix transposedMatrix = matrix.transposeMatrix();

        double[][] expectedTranspose = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        assertArrayEquals(expectedTranspose, transposedMatrix.getData());
    }

    @Test
    void testFromVector() {
        double[] vector = {1, 2, 3};
        Matrix result = Matrix.fromVector(vector);

        double[][] expectedMatrix = {{1, 0, 0}, {0, 2, 0}, {0, 0, 3}};
        assertArrayEquals(expectedMatrix, result.getData());
    }


}
