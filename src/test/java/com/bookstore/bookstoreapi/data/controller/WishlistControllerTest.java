package com.bookstore.bookstoreapi.data.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.service.WishlistService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class WishlistControllerTest {

  private MockMvc mockMvc;

  @Mock private WishlistService wishlistService;

  @InjectMocks private WishlistController wishlistController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(wishlistController).build();
  }

  @Test
  void testCreateWishlist_CreateWishlist() throws Exception {
    Wishlist wishlist = new Wishlist();
    when(wishlistService.createWishlist(anyString(), anyLong())).thenReturn(Optional.of(wishlist));

    mockMvc
        .perform(
            post("/wishlist/create")
                .param("wishlistName", "wishlist")
                .param("userID", "1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  void testCreateWishlist_UserDoesNotExistOrWishlistExistsAlready() throws Exception {
    when(wishlistService.createWishlist(anyString(), anyLong())).thenReturn(Optional.empty());

    mockMvc
        .perform(
            post("/wishlist")
                .param("wishlistName", "wishlist")
                .param("userID", "1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void testAddBookToWishlist_AddBook() throws Exception {
    Wishlist wishlist = new Wishlist();
    when(wishlistService.addBookToWishlist(anyLong(), anyLong())).thenReturn(Optional.of(wishlist));

    mockMvc
        .perform(
            post("/wishlist/add-book")
                .param("bookID", "1")
                .param("wishlistID", "1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void testAddBookToWishlist_BookOrWishlistDoNotExistOrBookIsAlreadyInWishlist() throws Exception {
    when(wishlistService.addBookToWishlist(anyLong(), anyLong())).thenReturn(Optional.empty());

    mockMvc
        .perform(
            post("/wishlist/add-book")
                .param("bookID", "1")
                .param("wishlistID", "1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void testRemoveBookFromWishlist_DeleteABook() throws Exception {
    Wishlist wishlist = new Wishlist();
    when(wishlistService.removeBookFromWishlist(anyLong(), anyLong()))
        .thenReturn(Optional.of(wishlist));

    mockMvc
        .perform(
            delete("/wishlist/remove-book")
                .param("bookID", "1")
                .param("wishlistID", "1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void testRemoveBookFromWishlist_BookOrWishlistDoNotExistOrBookNotInWishlist() throws Exception {
    when(wishlistService.removeBookFromWishlist(anyLong(), anyLong())).thenReturn(Optional.empty());

    mockMvc
        .perform(
            delete("/wishlist/remove-book")
                .param("bookID", "1")
                .param("wishlistID", "1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void testListBooksFromWishlist_Success() throws Exception {
    Wishlist wishlist = new Wishlist();
    Set<Book> books = new HashSet<>();
    wishlist.setBooks(books);

    when(wishlistService.listBooksFromWishlist(anyLong())).thenReturn(Optional.of(wishlist));

    mockMvc
        .perform(
            get("/wishlist/list-books/{wishlistID}", 1L).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(books.size()))); // make sure the size matches
  }

  @Test
  void testListBooksFromWishlist_NotFound() throws Exception {
    when(wishlistService.listBooksFromWishlist(anyLong())).thenReturn(Optional.empty());

    mockMvc
        .perform(get("/wishlist/{wishlistID}/books", 1L).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
