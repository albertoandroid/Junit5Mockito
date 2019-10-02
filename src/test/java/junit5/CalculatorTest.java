package junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by albertopalomarrobledo on 2/10/19.
 */
class CalculatorTest {

    private Calculator calculator;

    /*
     //METODO////////////////////////////////////////ESPECIFICACIOM//////////////////////////////////////////////////////////////////////////////
    int sumar(                  |Este método devuelve un int resultado de la suma de numero 1 y numero2
        int numero1,            |
        int numero2)            |
    ------------------------------------------------------------------------------------------------------------------------
    int restar(                 |Este método devuelve un int resultado de la resta de numero 1 y numero2
        int numero1,            |
        int numero2)            |


    Método a Probar                 |      Entrada      |       Salida Esperada
    sumar(int a, int b)             |a = 10, b=20       |30
    sumar(int a, int b)             |a = 7, b=4         |11
    restar(int a, int b)            |a = 7, b=4         |3
    restar(int a, int b)            |a = 10, b=20       |-10
     */

    @Test
    public void calculatorNotNullTest(){
        calculator = new Calculator();
        assertNotNull(calculator, "Calculator debe ser not null");
    }




}