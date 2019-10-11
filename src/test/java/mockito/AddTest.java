package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AddTest {

    @InjectMocks
    private Add add;
    @Mock
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTest(){
        when(validNumber.check(3)).thenReturn(true);
        boolean checkNumber = validNumber.check(3);
        assertEquals(true, checkNumber);

        when(validNumber.check("a")).thenReturn(false);
        checkNumber = validNumber.check("a");
        assertEquals(false, checkNumber);
    }

    @Test
    public void addMockExceptionTest(){
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar cero"));
        Exception exception = null;
        try{
            validNumber.checkZero(0);
        }catch (ArithmeticException e){
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void addRealMethodTest(){
        when(validNumber.check(3)).thenCallRealMethod();
        assertEquals(true, validNumber.check(3));

        when(validNumber.check("3")).thenCallRealMethod();
        assertEquals(false, validNumber.check("3"));
    }

    @Test
    public void addDoubleToIntThenAnswerTest(){

    }

}