package library;

import java.util.Objects;

/**
 * Represents a book with author, title, and number of pages.
 * Implements Comparable interface for sorting books.
 * 
 * @author Carson Fouts
 * @version 4/2 
 */
public class Book implements Comparable<Book> 
{

    private String author;
    private String title; 
    private int numPages;

    /**
     * Constructs a Book object with the given author, title, and numPg.
     * 
     * @param author   the author of the book
     * @param title    the title of the book
     * @param numPages the number of pages in the book
     */
    public Book(String author, String title, int numPages) 
    {
        this.author = author;
        this.title = title;
        this.numPages = numPages;
    }

    /**
     * Gets the author of the book.
     * 
     * @return the author of the book
     */
    public String getAuthor() 
    {
        return author;
    }

    /**
     * Gets the title of the book.
     * 
     * @return the title of the book
     */
    public String getTitle() 
    {
        return title;
    }

    /**
     * Gets the number of pages in the book.
     * 
     * @return the number of pages in the book
     */
    public int getNumPages() 
    {
        return numPages;
    }

    /**
     * Checks if this book is equal to another object.
     * Two books are considered equal if they have the same author and title.
     * 
     * @param obj the object to compare with
     * @return true if the books are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) 
        {
            return false;
        }
        Book book = (Book) obj;
        return Objects.equals(author, book.author) 
            && Objects.equals(title, book.title);
    }

    /**
     * Returns a string representation of the book.
     * 
     * @return a string representation of the book
     */
    @Override
    public String toString() 
    {
        return author + ", " + title + ", " + numPages;

    }

    /**
     * Compares this book with another book based on author and then title.
     * 
     * @param book the book to compare with
     * @return a negative integer, zero, or a positive integer
     */
    @Override
    public int compareTo(Book book) 
    {
        int authorComparison = this.author.compareTo(book.author);
        if (authorComparison != 0) 
        {
            return authorComparison;
        }
        return this.title.compareTo(book.title);
    }
    
}
