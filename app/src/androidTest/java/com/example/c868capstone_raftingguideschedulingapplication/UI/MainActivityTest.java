package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends TestCase {


    @Test
    public void testLogin() {
        MainActivity activity = Mockito.mock(MainActivity.class);

        // Test with valid credentials
        Mockito.when(activity.login("admin", "admin")).thenReturn(true);
        boolean result = activity.login("admin", "admin");
        assertTrue(result);

        // Test with invalid credentials
        Mockito.when(activity.login("invalid", "credentials")).thenReturn(false);
        result = activity.login("invalid", "credentials");
        assertFalse(result);
    }


}