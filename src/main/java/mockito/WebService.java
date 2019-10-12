package mockito;

public class WebService {

    public boolean checkLogin(String user, String password){
        if(user.equals("Alberto") && password.equals("1234")){
            return true;
        }
        return false;
    }
}
