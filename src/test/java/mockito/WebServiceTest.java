package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebServiceTest {

    private WebService webService;

    @BeforeEach
    public void setUp(){
        webService = new WebService();
    }

    @Test
    public void checkLoginTest(){
        assertTrue(webService.checkLogin("Alberto", "1234"));
    }

    @Test
    public void checkLoginErrorTest(){
        assertFalse(webService.checkLogin("Maria", "AAAA"));
    }

}