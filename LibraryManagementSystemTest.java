package LibraryManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;



class Book {
    private String author;
    private String title;
    
    Book(){
   	 this.author = null;
   	 this.title = null;
    }
    
    Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    
    public String getAuthor() {
   	    return author;
   	}

   	public void setAuthor(String author) {
   	    this.author = author;
   	}

   	public String getTitle() {
   	    return title;
   	}

   	public void setTitle(String title) {
   	    this.title = title;
   	}

    
    @Override
    public String toString() {
        return title + ";" + author;
    }
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return title.hashCode() + author.hashCode();
    }

	
}

class Library{

	private ArrayList<Book> books = new ArrayList<>();
	private HashSet<String> titles = new HashSet<>();
	FileWriter w;

	public void addBook(Book book) {
	    if (titles.contains(book.getTitle())) {
	            System.out.println("This book already exists: " + book.getTitle());
	        } else {
	            books.add(book);
	            titles.add(book.getTitle());
	            System.out.println("Book added: " + book.getTitle());
	        }
	  }
	
	public void removeBook(Book book) {
	    if (!titles.contains(book.getTitle())) {
	            System.out.println("This book don't exists");
	        } else {
	            books.remove(book);
	            titles.remove(book.getTitle());
	            System.out.println("Book removed");
	        }
	  }
	
	public void listBooks() {
	    for(Book i: books) {
	    	System.out.println(i.toString());
	    }
	  }
	
	public void fileBook() {
		try (FileWriter writer = new FileWriter("Library.txt")) {
	        for (Book i : books)
	            writer.write(i.toString() + "\n");
	        System.out.println("Books saved to file.");
	    } catch (IOException e) {
	        System.out.println("An error occurred while writing the file.");
	        e.printStackTrace();
	    }
	  }
	
    public void loadBooksFromFile() {
        File file = new File("Library.txt");
        if (!file.exists()) {
            System.out.println("No existing library file found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Assuming each line is in format: Title;Author
                String[] parts = line.split(";");
                if (parts.length != 2) continue; // skip bad lines

                String title = parts[0].trim();
                String author = parts[1].trim();

                if (!titles.contains(title)) {
                    Book book = new Book(title, author);
                    books.add(book);
                    titles.add(title);
                }
            }

            System.out.println("Books loaded from file.");

        } catch (IOException e) {
            System.out.println("Error while loading books from file.");
            e.printStackTrace();
        }
    }

	
	
}

public class LibraryManagementSystemTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        library.loadBooksFromFile();

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View All Books");
            System.out.println("4. Save Books to File");
            System.out.println("5. Load Books from File");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                    break;

                case 2:
                    System.out.print("Enter title of the book to remove: ");
                    String t = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String a = scanner.nextLine();
                    
                    Book bookToRemove = new Book(t, a);
                    library.removeBook(bookToRemove);
                    break;

                case 3:
                    library.listBooks();
                    break;

                case 4:
                    library.fileBook();
                    break;

                case 5:
                    library.loadBooksFromFile();
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

