package com.manifestcorp.techreads;

import com.manifestcorp.techreads.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BootStrapTest {

    private BootStrap bootStrap;
    private BookRepository mockBookRepository;

    @BeforeEach
    void setUp() throws Exception {
        bootStrap = new BootStrap();
        mockBookRepository = mock(BookRepository.class);
        bootStrap.bookRepository = mockBookRepository;
    }

    @Test
    void testCreationOf3BooksIfNoneInDatabase() {
        when(mockBookRepository.count()).thenReturn(0L);

        bootStrap.onApplicationEvent(null);

        verify(mockBookRepository, times(3)).save(any());
    }

    @Test
    void testNoBookSavesIfBooksInDatabaseIsGreaterThan2() {
        when(mockBookRepository.count()).thenReturn(3L);

        bootStrap.onApplicationEvent(null);

        verify(mockBookRepository, times(0)).save(any());
    }
}
