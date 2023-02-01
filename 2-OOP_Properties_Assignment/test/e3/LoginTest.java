package e3;
import e3.Mfa.Authenticator;
import e3.Mfa.Sms;
import e3.Mfa.MfaStrategy;
import e3.Mfa.Alphanumeric;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private static final MfaStrategy aut = new Authenticator();
    private static final MfaStrategy sms = new Sms();
    private static final MfaStrategy alp = new Alphanumeric();

    private static final Login login1 = new Login ("phone");
    private static final User u1 = new User ("001188800","cabell8", sms);
    private static final User u2 = new User ("obviouslynotafakeaddress@jmail.es","enteryourpassword", alp);
    private static final User u3 = new User ("98765432B","soymayordeedad", aut);
    private static final User u4 = new User ("01234567Z","soymenor", sms);


    @BeforeAll
    static void InsertUsers() {
        login1.newUser(u1);
        login1.newUser(u2);
        login1.newUser(u3);
        login1.newUser(u4);
    }
    @Test
    void getMfa(){
        assertEquals(sms, u1.getMfaUser());
        assertEquals(alp, u2.getMfaUser());
        assertEquals(aut, u3.getMfaUser());
    }
    @Test
    void getId(){
        assertEquals("001188800",u1.getId());
        assertEquals("obviouslynotafakeaddress@jmail.es",u2.getId());
        assertEquals("98765432B",u3.getId());

    }
    @Test
    void getPassword(){
        assertEquals("cabell8",u1.getPassword());
        assertEquals("enteryourpassword",u2.getPassword());
        assertEquals("soymayordeedad",u3.getPassword());

    }
    @Test
    void inList(){
        assertTrue(login1.inList("98765432B"));
        assertTrue(login1.inList("001188800"));
        assertTrue(login1.inList("obviouslynotafakeaddress@jmail.es"));
    }
    @Test
    void validateId(){
        assertTrue(login1.def.validateId(u1.getId()));
        assertFalse(login1.def.validateId(u2.getId()));
        assertFalse(login1.def.validateId(u3.getId()));
        login1.setLoginStrategy("email");
        assertFalse(login1.def.validateId(u1.getId()));
        assertTrue(login1.def.validateId(u2.getId()));
        assertFalse(login1.def.validateId(u3.getId()));
        login1.setLoginStrategy("dni");
        assertFalse(login1.def.validateId(u1.getId()));
        assertFalse(login1.def.validateId(u2.getId()));
        assertTrue(login1.def.validateId(u3.getId()));

        assertFalse(login1.def.validateId("ClarkKent"));

    }
    @Test
    void authenticatePassword(){
        assertTrue(login1.def.authenticatePassword(u1,u1.getPassword()));
        assertTrue(login1.def.authenticatePassword(u2,u2.getPassword()));
        assertTrue(login1.def.authenticatePassword(u3,u3.getPassword()));

        assertFalse(login1.def.authenticatePassword(u1,"1234"));
    }
    @Test
    void getMfaCode(){
        login1.setLoginStrategy("phone");
        assertEquals(6,login1.mfaCode("001188800","cabell8").length());
        assertThrows(IllegalArgumentException.class, ()->login1.mfaCode("juan","ok"));
        assertThrows(NoSuchElementException.class, ()->login1.mfaCode("999999999","ok"));
        assertThrows(IllegalArgumentException.class, ()->login1.mfaCode("001188800","ok"));
        login1.setLoginStrategy("email");
        assertEquals(9,login1.mfaCode("obviouslynotafakeaddress@jmail.es","enteryourpassword").length());
        assertThrows(IllegalArgumentException.class, ()->login1.mfaCode("juan","ok"));
        assertThrows(NoSuchElementException.class, ()->login1.mfaCode("juan@dominguez.es","ok"));
        assertThrows(IllegalArgumentException.class, ()->login1.mfaCode("obviouslynotafakeaddress@jmail.es","ok"));
        login1.setLoginStrategy("dni");
        assertTrue(6<=login1.mfaCode("98765432B","soymayordeedad").length() && login1.mfaCode("98765432B","soymayordeedad").length()<=8);
        assertThrows(IllegalArgumentException.class, ()->login1.mfaCode("juan","ok"));
        assertThrows(NoSuchElementException.class, ()->login1.mfaCode("00000000A","ok"));
        assertThrows(IllegalArgumentException.class, ()->login1.mfaCode("98765432B","ok"));
        login1.setMfaStrategy(u4,alp);
        assertEquals(9,login1.mfaCode("01234567Z","soymenor").length());
        assertNotEquals(8,login1.mfaCode("01234567Z","soymenor").length());
        login1.setMfaStrategy(u4,alp);


    }
}
