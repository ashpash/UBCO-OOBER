package com.ubco_oober.ashleybernhardt.ubco;

import com.ubco_oober.ashleybernhardt.ubco.LoginActivity;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class loginTest extends LoginActivity {


    @Test
    public void emailMatch () {
        String sEmail = "ab@alumni.ubc.ca";
        boolean x = isEmailValid(sEmail);
        assertTrue(x);
    }

    @Test
    public void validMatch () {
        String sEmail = "ab@alumni.ubc.ca";
        boolean x = validAt(sEmail);
        assertTrue(x);
    }


}
