package com.bookstore.bookstoreapi;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test", produces = MediaType.TEXT_HTML_VALUE)
    public String testEndpoint() {
        return "<h1 style=\"color:red;\">Hello, Bookstore!</h1>";
    }

    @GetMapping("/")
    public String defaultEndpoint() {
        return "Hello to Bookstore API app";
    }
}
