package com.manifestcorp.techreads.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

class BookTest {

    @Test
    void testBook() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Book.class);
    }
}