package src.main.java.com.bookstore.bookstoreapi.data.configuration;

import src.main.java.com.bookstore.bookstoreapi.data.entity.BookDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class CreateABook {
        public static void main(String[] args) {
            List<BookDetails> books = new ArrayList<>();
            Scanner scnr = new Scanner(System.in);

            System.out.println("Add Book to Database");
            System.out.println("Input the following information:");

            String ISBN;
            while (true) {
                System.out.print("ISBN:");
                ISBN = scnr.nextLine();
                if (ISBN.length() == 13 && ISBN.matches("[0-9]+")) {
                    break;
                } else {
                    System.out.println("Invalid Input!");
                    System.out.println("Please enter a 13-digit number.");
                }
            }

            System.out.println("Name:");
            String Name = scnr.nextLine();

            System.out.println("Description:");
            String Description = scnr.nextLine();

            double Price;
            while (true) {
                System.out.println("Price:");
                try {
                    Price = Double.parseDouble(scnr.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input!");
                }
            }

            System.out.println("Author:");
            String Author = scnr.nextLine();

            System.out.println("Genre:");
            String Genre = scnr.nextLine();

            System.out.println("Pubisher:");
            String Publisher = scnr.nextLine();

            int yearPublished;
            while (true) {
                System.out.print("Year Published:");
                try {
                    yearPublished = Integer.parseInt(scnr.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input!");
                }
            }

            int copiesSold;
            while (true) {
                System.out.print("Copies Sold:");
                try {
                    copiesSold = Integer.parseInt(scnr.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input!");
                }
            }

            BookDetails book = new BookDetails(ISBN, Name, Description, Price, Author, Genre, Publisher, yearPublished, copiesSold);
            books.add(book);

            for (BookDetails b : books) {
                System.out.println(b);
            }
            scnr.close();
        }
    }
