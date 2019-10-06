package junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;


/**
 * Created by albertopalomarrobledo on 2/10/19.
 */
class CalculatorTest {

    private Calculator calculator;
    private Calculator calculatorNull;
    private static Calculator calculatorStatic;

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

    @BeforeAll
    public static void beforeAllTests(){
        calculatorStatic = new Calculator();
        System.out.println("@BeforeAll -> beforeAllTests()");
    }

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
        System.out.println("@BeforeEach -> setUp()");
    }

    @AfterEach
    public void tearDown(){
        calculator = null;
        System.out.println("@AfterEach -> tearDown()");
    }

    @AfterAll
    public static void afterAllTests(){
        calculatorStatic = null;
        System.out.println("@AfterAll -> afterAllTests()");
    }

    @Test
    public void calculatorNotNullTest(){
        assertNotNull(calculatorStatic, "Calculator debe ser not null");
        assertNotNull(calculator, "Calculator debe ser not null");
        System.out.println("@Test -> calculatorNotNullTest()");
    }

    @Test
    public void calculatorNullTest(){
        assertNull(calculatorNull);
        System.out.println("@Test -> calculatorNullTest()");
    }

    /*
    Método a Probar                 |      Entrada      |       Salida Esperada
    add(int a, int b)               |a = 10, b=20       |30
     */

    @Test
    public void addAssertTest(){
        //1.- SetUp
        int resultadoEsperado = 30;
        int resultadoActual;
        //2.- Action
        resultadoActual = calculator.add(10,20);
        //3.- Assert
        assertEquals(resultadoEsperado, resultadoActual);
        System.out.println("@Test -> addAssertTest()");
    }

    @Test
    public void addTest(){
        assertEquals(30, calculator.add(10,20));
    }

    @Test
    public void assertTypesTest(){
        assertTrue(1 == 1);
        //assertTrue(1 == 2);

        assertNull(calculatorNull);
        assertNotNull(calculator);

        Calculator calculator1 = new Calculator();
        Calculator calculator2 = new Calculator();
        Calculator calculator3 = calculator1;

        assertSame(calculator1, calculator3);
       // assertSame(calculator1, calculator2);

        assertNotSame(calculator1, calculator2);
        //assertNotSame(calculator1, calculator3);

        assertEquals("alberto", "alberto");
       // assertEquals("alberto", "albert", "Ha fallado nuestro metodo String");

        assertEquals(1, 1.4, 0.5);
       // assertEquals(1, 1.6, 0.5);
    }

    @Test
    public void add_ValidInput_ValidExpected_Test(){
        assertEquals(11, calculator.add(7,4));
    }

    @Test
    public void subtract_ValidInput_ValidExpected_Test(){
        assertEquals(11, calculator.subtract(15,4));
    }

    @Test
    public void subtract_ValidInput_ValidNegativeResultExpected_Test(){
        assertEquals(-10, calculator.subtract(10,20));
    }

    @Test
    public void divide_ValidInput_ValidResultExpected_Test(){
        assertEquals(2, calculator.divide(10,5));
    }

    @Test
    public void divide_InValidInput_Test(){
        fail("Fallo detectado manualmente - No se puede dividir por cero");
        calculator.divide(10,0);
    }

    @Test
    public void divide_InValidInput_ExpectedException_Test(){
        assertThrows(ArithmeticException.class, ()->calculator.divideByZero(2,0), "No se puede dividir por cero");
    }

    @Disabled("Disabled until bug 23 be resolved")
    @Test
    public void divide_InvalidInput_Test(){
        assertEquals(2, calculator.divide(5,0));
    }

    @Test
    @DisplayName("Metodo Dividir -> Funciona")
    public void divide_ValidInput_ValidResultExpected_Name_Test(){
        assertEquals(2, calculator.divide(10,5));
    }

    @Test
    public void add_Assert_All_Test(){
        assertAll(
                ()-> assertEquals(31, calculator.add(11,20)),
                ()-> assertEquals(20, calculator.add(10,20)),
                ()-> assertEquals(2, calculator.add(1,1))
        );
    }

    @Nested
    class AddTest{
        @Test
        public void add_Positive_Test(){
            assertEquals(30, calculator.add(15,15));
        }

        @Test
        public void add_Negative_Test(){
            assertEquals(-30, calculator.add(-15,-15));
        }

        @Test
        public void add_Zero_Test(){
            assertEquals(0, calculator.add(0,0));
        }
    }

    /*
    Ejemplo en nuestra división queremos hacer 5 pruebas
    Positivo / Positivo = Positivo
    Positivo / Negativo = Negativo
    Negativo / Positivo = Negativo
    Negativo / Negativo = Positivo
    Positivo / 0 = Excepción
    Método a Probar                 |      Entrada      |       Salida Experarada
    dividir(int a, int b)           |a = 6, b=2         |3
    dividir(int a, int b)           |a = 6, b=-2        |-3
    dividir(int a, int b)           |a = -6, b=2        |-3
    dividir(int a, int b)           |a = -6, b=-2       |3
    dividir(int a, int b)           |a = -6, b=0        |Excepción
     */

    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("addProviderData")
    public void addParameterizedTest(int a, int b, int sum){
        assertEquals(sum, calculator.add(a, b));
    }

    private static Stream<Arguments> addProviderData(){
        return Stream.of(
                Arguments.of(6,2,8),
                Arguments.of(-6,-2,-10),
                Arguments.of(6,-2,4),
                Arguments.of(-6,2,-4),
                Arguments.of(6,0,6)
        );
    }

    @Test
    public void timeOut_Test(){
        assertTimeout(Duration.ofMillis(2000), () ->{
            calculator.longTaskOperation();
        });
    }

}