package com.ubco_oober.ashleybernhardt.ubco;

/**
 * Created by Ashley Bernhardt on 3/13/2017.
 */
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class regTest extends Register{

    @Test
    public void passwordMatch() {
        String e1 ="red";
        String e2 = "red";
        boolean x = passwordMatch(e1,e2);
        assertTrue(x);
    }

    @Test
    public void emailMatch () {
        String sEmail = "ab@alumni.ubc.ca";
        boolean x = isEmailValid(sEmail);
        assertTrue(x);
    }



}
