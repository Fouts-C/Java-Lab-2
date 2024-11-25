package storage;

/**
 * Represents a node in a linked list, storing data of type T.
 * 
 * @author Carson Fouts
 * @version 4/2
 * 
 * @param <T> the type of data stored in the node
 */
public class Node<T> 
{

    private T data;
    private Node<T> link;

    /**
     * Constructs a node with the specified data and link.
     * 
     * @param data the data to be stored in the node
     * @param link the reference to the next node
     */
    public Node(T data, Node<T> link)
    {
        this.data = data;
        this.link = link;
    }

    /**
     * Constructs a node with the specified data and null link.
     * 
     * @param data the data to be stored in the node
     */
    public Node(T data)
    {
        this(data, null);
    }

    /**
     * Constructs an empty node with null data and link.
     */
    public Node()
    {
        //No arg constructor
    }

    /**
     * Gets the reference to the next node.
     * 
     * @return the reference to the next node
     */

    public Node<T> getLink() 
    {
        return link;
    }

    /**
     * Gets the data stored in the node.
     * 
     * @return the data stored in the node
     */
    public T getData() 
    {
        return data;
    }

    /**
     * Sets the reference to the next node.
     * 
     * @param link the reference to the next node
     */
    public void setLink(Node<T> link) 
    {
        this.link = link;
    }

    /**
     * Sets the data stored in the node.
     * 
     * @param data the data to be stored in the node
     */
    public void setData(T data) 
    {
        this.data = data;
    }

}
