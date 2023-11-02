package com.bookstore.bookstoreapi;

import java.time.format.DateTimeFormatter;

public final class BookstoreConstants {

  private BookstoreConstants() {}

  public static final String SCIENCE_FICTION = "Science Fiction";
  public static final String SIMON_AND_SCHUSTER = "Simon & Schuster";
  public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
