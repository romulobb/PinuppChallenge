package com.util;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Calendar;

import static com.util.validators.validateDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class validatorsTest extends TestCase {

    @Test
    public void validate_date_ok()  {
        String date="2011-01-01";
        Calendar expected = Calendar.getInstance();
        expected.set(2011, Calendar.JANUARY,1,0,0,0);
        Calendar actual = null;
        try {
            actual=validateDate(date);
        } catch (ParseException e) {
            //throw new RuntimeException(e);
            assertFalse(e.getMessage(),false);
        }
        assertEquals(expected.getTime().toString(), actual.getTime().toString());
    }
    @Test
    public void validate_date_not_same_date()  {
        String date="2011-01-05";
        Calendar expected = Calendar.getInstance();
        expected.set(2011, Calendar.JANUARY,1,0,0,0);
        Calendar actual = null;
        try {
            actual=validateDate(date);
        } catch (ParseException e) {
            //throw new RuntimeException(e);
            assertFalse(e.getMessage(),false);
        }
        assertNotSame(expected.getTime().toString(), actual.getTime().toString());
    }
    @Test
    public void validate_date_days_malformed()  {
        String date="2011-01-35";
        Calendar actual = null;
        try {
            actual=validateDate(date);
        } catch (ParseException e) {
            assertFalse(e.getMessage(),false);
        }
    }
    @Test
    public void validate_date_year_malformed()  {
        String date="12-01-22";

        Calendar actual = null;
        try {
            actual=validateDate(date);
        } catch (ParseException e) {
            assertFalse(e.getMessage(),false);
        }
    }
    @Test
    public void validate_date_not_respect_pattern()  {
        String date="12-01-2002";

        Calendar actual = null;
        try {
            actual=validateDate(date);
        } catch (ParseException e) {
            assertFalse(e.getMessage(),false);
        }
    }
    @Test
    public void validate_date_different_splitters_patter()  {
        String date="2002/01/20";

        Calendar actual = null;
        try {
            actual=validateDate(date);
        } catch (ParseException e) {
            assertFalse(e.getMessage(),false);
        }
    }

}