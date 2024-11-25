package library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import storage.SortedLinkedList;

/**
 * Represents a reader for books stored in a file.
 * Provides methods for reading, processing, and managing books.
 * 
 * @author Carson Fouts
 * @version 4/2
 */
public class BookReader 
{

    private static final int PG_MAX = 300;
    private static final int PG_REMOVE = 200;

    private Scanner fileIn;
    private SortedLinkedList<Book> books;
    
    /**
     * Constructs a BookReader object with the specified filename.
     * 
     * @param filename the name of the file containing book information
     */
    public BookReader(String filename)
    {
        this.books = new SortedLinkedList<>();

        try
        {
            this.fileIn = new Scanner(new File(filename));
            setFileIn(fileIn);
            readLines();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + e);
        }
        finally
        {
            fileIn.close();
        }
    }

    /**
     * Sets the Scanner object for reading from file.
     * 
     * @param fileIn the Scanner object for reading from file
     */
    public void setFileIn(Scanner fileIn) 
    {
        this.fileIn = fileIn;
    }

    /**
     * Reads book information from the file and adds books to the list.
     */
    public void readLines()
    {
        while (fileIn.hasNextLine())
        {
            String line = fileIn.nextLine();
            String[] combLines = line.split(",");

            String author = combLines[0].trim();
            String title = combLines[1].trim();
            int numPages = Integer.parseInt(combLines[2].trim());

            Book book = new Book(author, title, numPages);
            books.add(book);

        }

    }

    /**
     * Gets the list of books.
     * 
     * @return the list of books
     */
    public SortedLinkedList<Book> getBooks() 
    {
        return books;
    }

    /**
     * Prints all books with more than 300 pages.
     */
    public void printMoreThan300()
    {
        Iterator<Book> iterateBooks = books.iterator();
        while (iterateBooks.hasNext()) 
        {
            Book book = iterateBooks.next();
            if (book.getNumPages() > PG_MAX)
            {
                System.out.println(book);
            }
        }
    }

    /**
     * Calculates the average number of pages across all books.
     * 
     * @return the average number of pages
     */
    public double averagePages()
    {
        int totalPg = 0;
        int numBooks = 0;

        Iterator<Book> iterateBooks = books.iterator();
        while (iterateBooks.hasNext())
        {
            Book book = iterateBooks.next();
            totalPg += book.getNumPages();
            numBooks++;
        }

        if (numBooks > 0)
        {
            return (double) totalPg / numBooks;
        }
        else
        {
            return 0.0;
        }
    }

    /**
     * Removes books with less than 200 pages from the list.
     */
    public void removeLessThan200()
    {
        Iterator<Book> iterateBooks = books.iterator();
        while (iterateBooks.hasNext())
        {
            Book book = iterateBooks.next();
            if (book.getNumPages() < PG_REMOVE)
            {
                iterateBooks.remove();
            }
        }

    }
}
