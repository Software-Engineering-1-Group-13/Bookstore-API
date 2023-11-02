package com.bookstore.bookstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BookstoreApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookstoreApiApplication.class, args);
  }
}

@RestController
class TestController {

  @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
  public String testEndpoint() {

    return "<html>"
        + "<head><title>Bookstore API Test</title></head>"
        + "<body>"
        + "<h1 style=\"color:blue;\">Welcome to the Bookstore API!</h1>"
        + "<p>Below are some resources to help you get started:</p>"
        + "<ul>"
        + "<li><a href=\"https://github.com/Software-Engineering-1-Group-13/Bookstore-API/blob/master/postman_collections/wishlist-api-calls.json\" target=\"_blank\">Postman Collection</a></li>"
        + "<li><a href=\"https://github.com/Software-Engineering-1-Group-13/Bookstore-API/tree/master/docs\" target=\"_blank\">Documentation & Workflow</a></li>"
        + "</ul>"
        + "</body>"
        + "</html>";
  }
}
