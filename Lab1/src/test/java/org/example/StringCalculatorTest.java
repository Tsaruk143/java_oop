package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class StringCalculatorTest {
    StringCalculator calculator = new StringCalculator();

    @Test
    void emptyStringCase(){
        int result = calculator.add("");
        assertEquals(0, result);
    }

    @Test
    void oneNumberCase(){
        int result = calculator.add("1");
        assertEquals(1, result);
    }

    @Test
    void classicCase(){
        int result = calculator.add("1,2");
        assertEquals(3, result);
    }

    @Test
    void moreThanTwo(){
        int result = calculator.add("1,2,3,4,5");
        assertEquals(15, result);
    }

    @Test
    void onlySlashn(){
        int result = calculator.add("1\n2");
        assertEquals(3, result);
    }

    @Test
    void withSlashn(){
        int result = calculator.add("1,2\n3");
        assertEquals(6, result);
    }

    @Test
    void twoDelimetr(){
        int result = calculator.add("1,\n");
        assertEquals(0, result);
    }

    @Test
    void anotherDelimetr(){
        int result = calculator.add("//;\n1;2");
        assertEquals(3, result);
    }

    @Test
    void neagtiveNumbers(){
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-1,2");
        });
    }

    @Test
    void overThousand(){
        int result = calculator.add("1000,999,1001");
        assertEquals(1999, result);
    }

    @Test
    void moreSymb(){
        int result = calculator.add("//[***]\n1***2***3");
        assertEquals(6, result);
    }

    @Test
    void moreThanOneDelimetr(){
        int result = calculator.add("//[*][%][#]\n1*2%3");
        assertEquals(6, result);
    }
}
