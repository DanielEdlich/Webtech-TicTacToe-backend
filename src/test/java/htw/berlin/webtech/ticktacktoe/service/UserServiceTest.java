package htw.berlin.webtech.ticktacktoe.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class UserServiceTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private  UserService service;

//    @Autowired
//    PasswordEncoder encoder;

    @Test
    @Disabled
    void comparePassword() {
        String password = "sCds)#5X6c'.";

        String encodedPassword = "" ;

        assertEquals("Passwords not equal",true, service.comparePassword(encodedPassword, password));

    }
}
