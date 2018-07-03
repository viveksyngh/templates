package com.openfaas;

import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {
    @Test public void testSomeLibraryMethod() {
        Handler sut = new Handler();
        assertTrue("Hello world!".equals(sut.Handle("test")), "Output of test handler was not correct");
    }
}
