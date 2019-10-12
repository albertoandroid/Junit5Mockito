package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class WebServiceTest {

    private WebService webService;
    @Mock
    private Callback callback;

    @BeforeEach
    public void setUp(){
        webService = new WebService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkLoginTest(){
        assertTrue(webService.checkLogin("Alberto", "1234"));
    }

    @Test
    public void checkLoginErrorTest(){
        assertFalse(webService.checkLogin("Maria", "AAAA"));
    }

    @Test
    public void loginTest(){
        webService.login("Alberto", "1234", callback);
        verify(callback).onSuccess("Usuario Correcto");
    }

    @Test
    public void loginErrorTest(){
        webService.login("Maria", "AAAA", callback);
        verify(callback).onFail("Error");
    }

}