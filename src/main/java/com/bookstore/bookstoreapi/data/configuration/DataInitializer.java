package com.bookstore.bookstoreapi.data.configuration;

import com.bookstore.bookstoreapi.BookstoreConstants;
import com.bookstore.bookstoreapi.data.entity.Author;
import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Cart;
import com.bookstore.bookstoreapi.data.entity.Comment;
import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.entity.Rating;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.repository.AuthorRepository;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import com.bookstore.bookstoreapi.data.repository.CartRepository;
import com.bookstore.bookstoreapi.data.repository.CommentRepository;
import com.bookstore.bookstoreapi.data.repository.CustomerRepository;
import com.bookstore.bookstoreapi.data.repository.RatingRepository;
import com.bookstore.bookstoreapi.data.repository.WishlistRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

  @Bean
  public CommandLineRunner initData(
      CustomerRepository customerRepository,
      WishlistRepository wishlistRepository,
      BookRepository bookRepository,
      CommentRepository commentRepository,
      RatingRepository ratingRepository,
      CartRepository cartRepository,
      AuthorRepository authorRepository) {
    return args -> {
      Customer john = new Customer();
      john.setFirstName("John");
      john.setLastName("Doe");
      john.setEmail("john.doe@example.com");
      john.setAddress("12345 Random st");
      customerRepository.save(john);

      Customer jane = new Customer();
      jane.setFirstName("Jane");
      jane.setLastName("Smith");
      jane.setEmail("jane.smith@example.com");
      jane.setAddress("12345 non-Random st");
      customerRepository.save(jane);

      Wishlist johnWishList = new Wishlist();
      johnWishList.setName("John's Favorites");
      johnWishList.setCustomer(john);
      wishlistRepository.save(johnWishList);

      Author author1 = new Author();
      author1.setFirstName("Jules");
      author1.setLastName("Verne");
      author1.setBiography(
          "Jules Verne, (born February 8, 1828, Nantes, France—died March 24, 1905, Amiens), prolific French author whose writings laid much of the foundation of modern science fiction.");
      author1.setPublisher("Simon & Schuster");
      authorRepository.save(author1);

      Author author2 = new Author();
      author2.setFirstName("H. G.");
      author2.setLastName("Wells");
      author2.setBiography(
          "H.G. Wells, in full Herbert George Wells, (born September 21, 1866, Bromley, Kent, England—died August 13, 1946, London), English novelist, journalist, sociologist, and historian.");
      author2.setPublisher("Simon & Schuster");
      authorRepository.save(author2);

      Author author3 = new Author();
      author3.setFirstName("Aldous");
      author3.setLastName("Huxley");
      author3.setBiography(
          "Aldous Huxley, in full Aldous Leonard Huxley, (born July 26, 1894, Godalming, Surrey, England—died November 22, 1963, Los Angeles, California, U.S.), English novelist and critic notable for their wit and pessimistic satire.");
      author3.setPublisher("Harper & Brothers");
      authorRepository.save(author3);

      Author author4 = new Author();
      author4.setFirstName("Colleen");
      author4.setLastName("Hoover");
      author4.setBiography(
          "Colleen Hoover, (born December 11, 1979, Sulphur Springs, Texas, U.S.), American author who became a publishing phenomenon in the early 21st century and is known for hugely popular books that typically feature romance and dramatic plot twists.");
      author4.setPublisher("Simon & Schuster");
      authorRepository.save(author4);

      Author author5 = new Author();
      author5.setFirstName("Rachel");
      author5.setLastName("Hawkins");
      author5.setBiography(
          "Rachel Hawkins is the author of Rebel Belle and the New York Times bestselling series Hex Hall. Born in Virginia and raised in Alabama, Rachel taught high school English for three years before becoming a full-time writer. ");
      author5.setPublisher("Penguin Random House");
      authorRepository.save(author5);

      Author author6 = new Author();
      author6.setFirstName("Delia");
      author6.setLastName("Owens");
      author6.setBiography(
          "Delia Owens is the co-author of three internationally bestselling nonfiction books about her life as a wildlife scientist in Africa including Cry of the Kalahari.");
      author6.setPublisher("Penguin Random House");
      authorRepository.save(author6);

      Book book1 = new Book();
      book1.setTitle("A Journey to the Center of the Earth");
      book1.setIsbn("978-1503215153");
      book1.setPublishingDate(LocalDate.parse(("1864-01-01"), BookstoreConstants.DATE_FORMAT));
      book1.setTitle("A Journey to the Center of the Earth");
      book1.setAuthor(author1);
      book1.setGenre(BookstoreConstants.SCIENCE_FICTION);
      book1.setDescription(
          "A geology professor and his nephew discover and decode an ancient document that"
              + " shows that a dormant volcano holds a secret entrance to a subterranean world at the earth's center.");
      book1.setPublisher("Simon & Shuster");
      book1.setPublishingDate(LocalDate.parse(("1864-11-25"), BookstoreConstants.DATE_FORMAT));
      book1.setPrice(12.99);
      book1.setStockCount(50);
      book1.setCopiesSold(1000000);

      Book book2 = new Book();
      book2.setTitle("The Time Machine");
      book2.setIsbn("978-0451530707");
      book2.setTitle("The Time Machine");
      book2.setAuthor(author2);
      book2.setGenre(BookstoreConstants.SCIENCE_FICTION);
      book2.setDescription(
          "A nameless scientist builds a time machine, travels to the year 802,701 AD and there encounters humanity’s descendants ");
      book2.setPublisher("William Heinemann (UK) Henry Holt (US)");
      book2.setPublishingDate(LocalDate.parse(("1895-05-07"), BookstoreConstants.DATE_FORMAT));
      book2.setPrice(10.50);
      book2.setStockCount(30);
      book2.setCopiesSold(1000000);

      Book book3 = new Book();
      book3.setTitle("Brave New World");
      book3.setIsbn("978-0060850524");
      book3.setTitle("Brave New World");
      book3.setAuthor(author3);
      book3.setGenre(BookstoreConstants.SCIENCE_FICTION);
      book3.setDescription(
          "A futuristic society, called the World State, that revolves around science and efficiency.");
      book3.setPublisher("HarperCollins");
      book3.setPublishingDate(LocalDate.parse(("1932-08-15"), BookstoreConstants.DATE_FORMAT));
      book3.setPrice(15.20);
      book3.setStockCount(20);
      book3.setCopiesSold(1000000);

      Book book4 = new Book();
      book4.setIsbn("978-3423230124");
      book4.setTitle("Verity");
      book4.setAuthor(author4);
      book4.setGenre("Thriller");
      book4.setDescription(
          "Lowen Ashleigh is a struggling writer who accepts the job offer of a lifetime. "
              + "Jeremy Crawford, husband of bestselling author Verity Crawford, has hired "
              + "Lowen to complete the remaining books in a successful series his injured wife is unable to finish.");
      book4.setPublisher("Grand Central Publishing");
      book4.setPublishingDate(LocalDate.parse(("2018-12-07"), BookstoreConstants.DATE_FORMAT));
      book4.setPrice(12.81);
      book4.setStockCount(15);
      book4.setCopiesSold(3000000);

      Book book5 = new Book();
      book5.setIsbn("978-1250245496");
      book5.setTitle("The Wife Upstairs");
      book5.setAuthor(author5);
      book5.setGenre("Suspense");
      book5.setDescription(
          "A timeless tale of forbidden romance, ill-advised attraction, and a wife who just won't stay buried");
      book5.setPublisher("St. Martin's Press");
      book5.setPublishingDate(LocalDate.parse(("2021-01-05"), BookstoreConstants.DATE_FORMAT));
      book5.setPrice(9.99);
      book5.setStockCount(11);
      book5.setCopiesSold(235169);

      Book book6 = new Book();
      book6.setIsbn("978-3446264199");
      book6.setTitle("Where the Crawdads Sing");
      book6.setAuthor(author6);
      book6.setGenre("Mystery");
      book6.setDescription(
          "A woman who raised herself in the marshes of the Deep South becomes a suspect in the murder of a man with whom she was once involved.");
      book6.setPublisher("St. Martin's Press");
      book6.setPublishingDate(LocalDate.parse(("2018-08-14"), BookstoreConstants.DATE_FORMAT));
      book6.setPrice(14.67);
      book6.setStockCount(21);
      book6.setCopiesSold(12000000);

      Book book7 = new Book();
      book7.setIsbn("978-7458695231");
      book7.setTitle("It Ends With Us");
      book7.setAuthor(author4);
      book7.setGenre("Romance");
      book7.setDescription(
          "Atlas and Lily attempt to rekindle the love they felt for each other as teenagers, but must deal with the repercussions their love may have now as adults.");
      book7.setPublisher("Atria Books");
      book7.setPublishingDate(LocalDate.parse(("2016-02-29"), BookstoreConstants.DATE_FORMAT));
      book7.setPrice(11.99);
      book7.setStockCount(56);
      book7.setCopiesSold(19000000);

      bookRepository.save(book1);
      bookRepository.save(book2);
      bookRepository.save(book3);
      bookRepository.save(book4);
      bookRepository.save(book5);
      bookRepository.save(book6);
      bookRepository.save(book7);

      Cart johnCart = new Cart();
      johnCart.setCustomer(john);
      johnCart.getBooks().add(book1);
      johnCart.getBooks().add(book2);
      johnCart.getBooks().add(book3);

      Cart janeCart = new Cart();
      janeCart.setCustomer(jane);

      cartRepository.save(johnCart);
      cartRepository.save(janeCart);

      book1.getCarts().add(johnCart);
      book2.getCarts().add(johnCart);
      book3.getCarts().add(johnCart);

      johnWishList.getBooks().add(book1);
      book1.getWishlists().add(johnWishList);

      johnWishList.getBooks().add(book2);
      book2.getWishlists().add(johnWishList);

      wishlistRepository.save(johnWishList);

      Rating rating1 = new Rating();
      rating1.setCustomer(john);
      rating1.setBook(book1);
      rating1.setRating(3);

      Comment comment1 = new Comment();
      comment1.setCustomer(john);
      comment1.setBook(book1);
      comment1.setComment("Wow! Super awesome book!");

      ratingRepository.save(rating1);
      commentRepository.save(comment1);

      john.getRatings().add(rating1);
      book1.getRatings().add(rating1);
      john.getComments().add(comment1);
      book1.getComments().add(comment1);

      johnWishList.getBooks().add(book2);
      book2.getWishlists().add(johnWishList);
      wishlistRepository.save(johnWishList);

      ratingRepository.save(rating1);
      john.getRatings().add(rating1);
      book1.getRatings().add(rating1);
      customerRepository.save(john);
      bookRepository.save(book1);
      bookRepository.save(book2);
      bookRepository.save(book3);
    };
  }
}
