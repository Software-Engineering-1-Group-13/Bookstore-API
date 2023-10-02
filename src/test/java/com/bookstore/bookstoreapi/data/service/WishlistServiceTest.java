package com.bookstore.bookstoreapi.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import com.bookstore.bookstoreapi.data.repository.CustomerRepository;
import com.bookstore.bookstoreapi.data.repository.WishlistRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class WishlistServiceTest {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  @InjectMocks private WishlistService wishlistService;
  @Mock private WishlistRepository wishlistRepository;
  @Mock private BookRepository bookRepository;
  @Mock private CustomerRepository customerRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateWishlist_CustomerDoesNotExist() {

    Wishlist dummyWishlist = new Wishlist();
    dummyWishlist.setName("My Cool Books");

    when(customerRepository.findById(1L)).thenReturn(Optional.empty());

    Optional<Wishlist> result = wishlistService.createWishlist(dummyWishlist.getName(), 1L);

    assertFalse(result.isPresent());
  }

  @Test
  void testCreateWishlist_WishlistAlreadyExists() {

    Wishlist wishlist = new Wishlist();
    wishlist.setName("My Cool Books");
    Customer user = new Customer();
    user.getWishlists().add(wishlist);

    List<Wishlist> wishlistList = new ArrayList<>();
    wishlistList.add(wishlist);

    when(customerRepository.findById(1L)).thenReturn(Optional.of(user));
    when(wishlistRepository.findByCustomerAndName(user, wishlist.getName()))
        .thenReturn(wishlistList);

    Optional<Wishlist> result = wishlistService.createWishlist(wishlist.getName(), 1L);

    assertFalse(result.isPresent());
  }

  @Test
  void testCreateWishlist_CreateWishlist() {

    Customer user = new Customer();
    when(customerRepository.findById(1L)).thenReturn(Optional.of(user));

    Optional<Wishlist> result = wishlistService.createWishlist("My Cool Books", 1L);

    assertTrue(result.isPresent());
  }

  @Test
  void testAddBookToWishlist_BookOrWishlistDoesNotExist() {

    when(bookRepository.findById(1L)).thenReturn(Optional.empty());
    when(wishlistRepository.findById(1L)).thenReturn(Optional.empty());

    Optional<Wishlist> result = wishlistService.addBookToWishlist(1L, 1L);

    assertFalse(result.isPresent());
  }

  @Test
  void testAddBookToWishlist_BookAlreadyInWishlist() {

    Book book = new Book();
    Wishlist wishlist = new Wishlist();
    wishlist.getBooks().add(book);

    when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));

    Optional<Wishlist> result = wishlistService.addBookToWishlist(1L, 1L);

    assertFalse(result.isPresent());
  }

  @Test
  void testAddBookToWishlist_AddingBookToWishlist() throws ParseException {

    Book book = new Book();
    book.setTitle("A Journey to the Center of the Earth");
    book.setIsbn("978-1503215153");
    book.setPublishingDate(DATE_FORMAT.parse("1864-01-01"));
    book.setPrice(12.99);
    book.setStockCount(50);
    Wishlist wishlist = new Wishlist();

    when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));

    Optional<Wishlist> result = wishlistService.addBookToWishlist(1L, 1L);

    assertTrue(result.isPresent());
  }

  @Test
  void testRemoveBookFromWishlist_BookOrWishlistDoesNotExist() {

    when(bookRepository.findById(1L)).thenReturn(Optional.empty());
    when(wishlistRepository.findById(1L)).thenReturn(Optional.empty());

    Optional<Wishlist> result = wishlistService.removeBookFromWishlist(1L, 1L);

    assertFalse(result.isPresent());
  }

  @Test
  void testRemoveBookFromWishlist_BookInWishlist() {

    Book book = new Book();
    Wishlist wishlist = new Wishlist();
    wishlist.getBooks().add(book);

    when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));

    Optional<Wishlist> result = wishlistService.removeBookFromWishlist(1L, 1L);

    assertTrue(result.isPresent());
    assertFalse(result.get().getBooks().contains(book));
  }

  @Test
  void testRemoveBookFromWishlist_BookNotInWishlist() {

    Book book = new Book();
    Wishlist wishlist = new Wishlist();

    when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));

    Optional<Wishlist> result = wishlistService.removeBookFromWishlist(1L, 1L);

    assertFalse(result.isPresent());
  }

  @Test
  void testListBooksFromWishlist_WishlistExists() {

    Wishlist dummyWishlist = new Wishlist();
    dummyWishlist.setName("My Cool Books");

    when(wishlistRepository.findById(1L)).thenReturn(Optional.of(dummyWishlist));

    Optional<Wishlist> result = wishlistService.listBooksFromWishlist(1L);

    assertTrue(result.isPresent());
    assertEquals("My Cool Books", result.get().getName());
  }

  @Test
  void testListBooksFromWishlist_WishlistDoesNotExist() {

    when(wishlistRepository.findById(1L)).thenReturn(Optional.empty());

    Optional<Wishlist> result = wishlistService.listBooksFromWishlist(1L);

    assertFalse(result.isPresent());
  }
}
