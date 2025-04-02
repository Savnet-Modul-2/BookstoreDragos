package com.example.SpringBookstore.unitTest.service;

import com.example.SpringBookstore.BookCategory;
import com.example.SpringBookstore.entity.Book;
import com.example.SpringBookstore.entity.Library;
import com.example.SpringBookstore.entityDTO.BookDTO;
import com.example.SpringBookstore.entityDTO.LibraryDTO;
import com.example.SpringBookstore.repository.BookRepository;
import com.example.SpringBookstore.repository.LibraryRepository;
import com.example.SpringBookstore.service.BookService;
import com.example.SpringBookstore.service.LibraryService;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private LibraryService libraryService;
    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private BookService bookService;
    private Book testBook;
    private List<Book> testBookList;

    @BeforeEach
    public void setup() {
        testBook = new Book();

        testBook.setID(1L);
        testBook.setTitle("testTitle");
        testBook.setAuthor("testAuthor");
        testBook.setReleaseDate(LocalDate.now());
        testBook.setNumberOfPages(100);
        testBook.setCategory(BookCategory.ACTION);
        testBook.setLanguage("English");

        testBookList = List.of(testBook);
    }

    @Test
    public void givenBook_CreateBook_ReturnBook() {
        testBook.setID(null);
        bookService.create(testBook);
        testBook.setID(1L);

        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        Mockito.verify(bookRepository).save(bookArgumentCaptor.capture());
        Book capturedBook = bookArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedBook).isEqualTo(testBook);
    }

    @Test
    public void givenBookWithID_CreateBook_ThrowException() {
        Assertions.assertThatThrownBy(() -> bookService.create(testBook))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void givenBookID_FindByID_ReturnBook() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testBook));

        Book foundBook = bookService.findByID(testBook.getID());

        AssertionsForClassTypes.assertThat(foundBook).isEqualTo(testBook);

        Mockito.verify(bookRepository, Mockito.times(1)).findById(testBook.getID());
    }

    @Test
    public void givenWrongBookID_FindByID_ThrowException() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> bookService.findByID(testBook.getID()))
                .isInstanceOf(EntityNotFoundException.class);

        Mockito.verify(bookRepository, Mockito.times(1)).findById(testBook.getID());
    }

    @Test
    public void givenNothing_ListAll_VerifyCalledMethod() {
        bookService.listAll();

        Mockito.verify(bookRepository).findAll();
    }

    @Test
    public void givenNumberAndSize_ListPaginated_VerifyCalledMethod() {
        Pageable testPage = PageRequest.of(0, testBookList.size());

        Mockito.when(bookRepository.findAll(testPage))
                .thenReturn(new PageImpl<>(testBookList, testPage, testBookList.size()));

        bookService.listPaginated(0, testBookList.size());

        Mockito.verify(bookRepository).findAll(testPage);
    }

    @Test
    public void givenNumber_ListPaginated_VerifyCalledMethod() {
        bookService.listPaginated(0, null);

        Mockito.verify(bookRepository).findAll(Pageable.unpaged());
    }

    @Test
    public void givenSize_ListPaginated_VerifyCalledMethod() {
        bookService.listPaginated(null, testBookList.size());

        Mockito.verify(bookRepository).findAll(Pageable.unpaged());
    }

    @Test
    public void givenNothing_ListPaginated_VerifyCalledMethod() {
        bookService.listPaginated(null, null);

        Mockito.verify(bookRepository).findAll(Pageable.unpaged());
    }

    @Test
    public void givenPageable_FindAll_ReturnIsNotEmpty() {
        Pageable testPage = PageRequest.of(0, testBookList.size());

        Mockito.when(bookRepository.findAll(testPage))
                .thenReturn(new PageImpl<>(testBookList, testPage, testBookList.size()));

        Page<Book> page = bookRepository.findAll(testPage);

        Assertions.assertThat(page).isNotEmpty();

        Mockito.verify(bookRepository).findAll(testPage);
    }

    @Test
    public void givenBook_UpdateBook_ReturnBook() {
        BookDTO newBookDTO = new BookDTO();

        newBookDTO.setID(1L);
        newBookDTO.setTitle("newTestTitle");
        newBookDTO.setAuthor("newTestAuthor");
        newBookDTO.setLibraryDTO(null);

        Mockito.when(bookRepository.findById(testBook.getID())).thenReturn(Optional.of(testBook));

        bookService.update(newBookDTO.getID(), newBookDTO);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        Mockito.verify(bookRepository).save(bookArgumentCaptor.capture());
        Book capturedBook = bookArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedBook).isEqualTo(testBook);
    }

    @Test
    public void givenBookWithLibrary_UpdateBook_ReturnBook() {
        BookDTO newBookDTO = new BookDTO();
        LibraryDTO newLibraryDTO = new LibraryDTO();

        newBookDTO.setID(1L);
        newBookDTO.setTitle("newTestTitle");
        newBookDTO.setAuthor("newTestAuthor");
        newBookDTO.setLibraryDTO(newLibraryDTO);

        Mockito.when(bookRepository.findById(testBook.getID())).thenReturn(Optional.of(testBook));

        bookService.update(testBook.getID(), newBookDTO);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        Mockito.verify(bookRepository).save(bookArgumentCaptor.capture());
        Book capturedBook = bookArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedBook).isEqualTo(testBook);
    }

    @Test
    public void givenBookID_DeleteByID_VerifyCalledMethod() {
        Mockito.when(bookRepository.existsById(testBook.getID())).thenReturn(true);

        bookService.delete(testBook.getID());

        Mockito.verify(bookRepository).deleteById(testBook.getID());
    }

    @Test
    public void givenWrongBookID_DeleteByID_ThrowException() {
        Assertions.assertThatThrownBy(() -> bookService.delete(testBook.getID()))
                .isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(libraryRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void givenLibraryID_AddBookToLibrary_ReturnBook() {
        Library testLibrary = new Library();
        testLibrary.setID(1L);

        Mockito.when(bookRepository.findById(testBook.getID())).thenReturn(Optional.of(testBook));
        Mockito.when(libraryService.findByID(testLibrary.getID())).thenReturn(testLibrary);

        bookService.addBook(testBook.getID(), testLibrary.getID());

        ArgumentCaptor<Library> libraryArgumentCaptor = ArgumentCaptor.forClass(Library.class);
        Mockito.verify(libraryRepository).save(libraryArgumentCaptor.capture());

        Library capturedLibrary = libraryArgumentCaptor.getValue();

        Assertions.assertThat(capturedLibrary.getBooks()).contains(testBook);
    }

    @Test
    public void givenWrongLibraryID_AddBookToLibrary_ThrowException() {
        Assertions.assertThatThrownBy(() -> bookService.addBook(testBook.getID(), Mockito.anyLong()))
                .isInstanceOf(EntityNotFoundException.class);

        Mockito.verify(libraryRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void givenLibraryID_RemoveBookToLibrary_ReturnBook() {
        Library testLibrary = new Library();
        testLibrary.setID(1L);

        Mockito.when(bookRepository.findById(testBook.getID())).thenReturn(Optional.of(testBook));
        Mockito.when(libraryService.findByID(testLibrary.getID())).thenReturn(testLibrary);

        bookService.removeBook(testBook.getID(), testLibrary.getID());

        ArgumentCaptor<Library> libraryArgumentCaptor = ArgumentCaptor.forClass(Library.class);
        Mockito.verify(libraryRepository).save(libraryArgumentCaptor.capture());

        Library capturedLibrary = libraryArgumentCaptor.getValue();

        Assertions.assertThat(capturedLibrary.getBooks()).doesNotContain(testBook);
    }

    @Test
    public void givenWrongLibraryID_RemoveBookFromLibrary_ThrowException() {
        Assertions.assertThatThrownBy(() -> bookService.removeBook(testBook.getID(), Mockito.anyLong()))
                .isInstanceOf(EntityNotFoundException.class);

        Mockito.verify(libraryRepository, Mockito.never()).save(Mockito.any());
    }
}
