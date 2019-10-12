package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LoginTest {

    @InjectMocks
    private Login login;

    @Mock
    private WebService webService;

    @Captor
    private ArgumentCaptor<Callback> callbackArgumentCaptor;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doLoginTest(){
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String user = (String)invocationOnMock.getArguments()[0];
                assertEquals("Alberto", user);
                String password = (String)invocationOnMock.getArguments()[1];
                assertEquals("12345", password);
                Callback callback = (Callback)invocationOnMock.getArguments()[2];
                callback.onSuccess("OK");
                return null;
            }
        }).when(webService).login(anyString(), anyString(), any(Callback.class));

        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
        assertEquals(login.isLogin, true);
    }

    @Test
    public void doLoginErrorTest(){
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String user = (String)invocationOnMock.getArguments()[0];
                assertEquals("Alberto", user);
                String password = (String)invocationOnMock.getArguments()[1];
                assertEquals("12345", password);
                Callback callback = (Callback)invocationOnMock.getArguments()[2];
                callback.onFail("Error");
                return null;
            }
        }).when(webService).login(anyString(), anyString(), any(Callback.class));

        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
        assertEquals(login.isLogin, false);
    }

    @Test
    public void doLoginCaptorTest(){
        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), callbackArgumentCaptor.capture());
        assertEquals(login.isLogin, false);

        Callback callback = callbackArgumentCaptor.getValue();
        callback.onSuccess("OK");
        assertEquals(login.isLogin, true);

        callback.onFail("Error");
        assertEquals(login.isLogin, false);

    }

}