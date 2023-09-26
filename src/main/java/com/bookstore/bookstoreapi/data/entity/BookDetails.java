package src.main.java.com.bookstore.bookstoreapi.data.entity;

public class BookDetails {
        private String ISBN;
        private String Name;
        private String Description;
        private double Price;
        private String Author;
        private String Genre;
        private String Publisher;
        private int yearPublished;
        private int copiesSold;

        public BookDetails(String ISBN, String Name, String Description, double Price, String Author, String Genre,
                    String Publisher, int yearPublished, int copiesSold) {
            this.ISBN = ISBN;
            this.Name = Name;
            this.Description = Description;
            this.Price = Price;
            this.Author = Author;
            this.Genre = Genre;
            this.Publisher = Publisher;
            this.yearPublished = yearPublished;
            this.copiesSold = copiesSold;
        }

        public String toString() {
            return "ISBN:" + ISBN + ", Name: " + Name + ", Description: " + Description + ", Price: " +
                    Price + ", Author: " + Author + ", Genre: " + Genre + ", Publisher: " + Publisher +
                    ", Year Published: " + yearPublished + ", Copies Sold: " + copiesSold;
        }
    }

