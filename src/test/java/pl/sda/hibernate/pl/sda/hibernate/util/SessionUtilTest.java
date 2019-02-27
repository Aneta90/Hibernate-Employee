package pl.sda.hibernate.pl.sda.hibernate.util;

import org.hibernate.Session;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNotNull;

public class SessionUtilTest {

    @Test
    public void sessionUtilTest(){
        try(Session session = SessionUtil.getSession() ){
               assertNotNull(session);
        }
     }
}
