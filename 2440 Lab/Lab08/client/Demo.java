import library.Book;
import library.BookReader;

/**
 * Demo used to manually test the program.
 * 
 * @author Carson Fouts
 * @version 4/2
 */
public class Demo
{
	/**
     * Runs the demo.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) 
    {
        // add your manual tests here.
        BookReader bR = new BookReader("data/bookdata.txt");
        for (Book b : bR.getBooks())
        {
            System.out.println(b.toString());
        }
        bR.printMoreThan300();

    }
}
