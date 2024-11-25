package storage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a sorted linked list of elements of type T.
 * Provides methods for adding, removing, and accessing elements.
 * Implements the Iterable interface to support iteration over the elements.
 * 
 * @author Carson Fouts
 * @version 4/2
 * 
 * @param <T> the type of elements stored in the list, must be comparable.
 */
public class SortedLinkedList 
    <T extends Comparable<? super T>> implements Iterable<T>
{
    
    private Node<T> head;
    private int length;

    /**
     * Constructs an empty sorted linked list.
     */
    public SortedLinkedList()
    {
        //No arg constructor
    }

    /**
     * Adds a new element to the sorted linked list in ascending order.
     * 
     * @param entry the element to be added
     */
    public void add(T entry)
    {
        Node<T> newNd = new Node<>(entry);
        
        if (head == null || entry.compareTo(head.getData()) < 0)
        {
            newNd.setLink(head);
            head = newNd;
        }
        else
        {
            Node<T> prev = getPrevious(entry);
            newNd.setLink(prev.getLink());
            prev.setLink(newNd);
        }
        length++;
    }

    /**
     * Removes the element at the specified position in the sorted linked list.
     * 
     * @param position the position of the element to be removed
     * @return the removed element
     */
    @SuppressWarnings("unchecked")
    public T remove(int position)
    {
        if (position < 0 || position >= length)
        {
            return null;
        }

        Node<T> removeNd;
        if (position == 0)
        {
            removeNd = head;
            head = head.getLink();
        }
        else
        {
            Node<T> prev = head;
            for (int i = 0; i < position - 1; i++)
            {
                prev = prev.getLink();
            }
            removeNd = prev.getLink();
            prev.setLink(removeNd.getLink());
        }
        length--;
        return removeNd.getData();
    }

    /**
     * Removes all elements from the sorted linked list.
     */
    public void clear()
    {
        head = null;
        length = 0;
    }

    /**
     * Gets the element at the specified position in the sorted linked list.
     * 
     * @param position the position of the element to be retrieved
     * @return the element at the specified position
     */
    public T getEntry(int position)
    {
        if (position < 0 || position >= length) 
        {
            return null; 
        }

        Node<T> currentNd = head;
        for (int i = 0; i < position; i++)
        {
            currentNd = currentNd.getLink();
        } 
        return currentNd.getData();
    }

    /**
     * Returns the position of the specified element in the sorted linked list.
     * 
     * @param entry the element to be found
     * @return the position of the element in the list
     */
    public int getPosition(T entry)
    {
        int count = 0;
        Node<T> currentNd = head;

        while (currentNd != null && entry.compareTo(currentNd.getData()) > 0)
        {
            currentNd = currentNd.getLink();
            count++;
        }

        if (currentNd == null || !entry.equals(currentNd.getData()))
        {
            throw new IllegalArgumentException("Element not found.");
        }
        return count;

    }

    /**
     * Checks if the sorted linked list contains the specified element.
     * 
     * @param entry the element to be checked for containment
     * @return true if the list contains the element, false otherwise
     */
    public boolean contains(T entry)
    {
        Node<T> currentNd = head;

        while (currentNd != null)
        {
            if (entry.equals(currentNd.getData()))
            {
                return true;
            }
            currentNd = currentNd.getLink();
        }

        return false;
    }

    /**
     * Gets the number of elements in the sorted linked list.
     * 
     * @return the number of elements in the list
     */
    public int getLength() 
    {
        return length;
    }

    /**
     * Checks if the sorted linked list is empty.
     * 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return length == 0;
    }

    /**
     * Displays the elements of the sorted linked list.
     */
    public void display()
    {
        Node<T> currentNd = head;
        while (currentNd != null)
        {
            System.out.print(currentNd.getData() + " ");
            currentNd = currentNd.getLink();
        }
    }

    /**
     * Returns an iterator over the elements in the sorted linked list.
     * 
     * @return an iterator over the elements in the list
     */
    @Override
    public Iterator<T> iterator()
    {
        return new SLLIterator(head);
    }

    /**
     * Returns the node before.
     * 
     * @param entry the element to be inserted
     * @return the node before the position where the element should be inserted
     */
    private Node<T> getPrevious(T entry)
    {
        Node<T> current = head;
        Node<T> prev = null;

        while (current != null && entry.compareTo(current.getData()) > 0)
        {
            prev = current;
            current = current.getLink();
        }
        return prev;
    }

    /**
     * Represents an iterator over the elements in the sorted linked list.
     */
    private class SLLIterator implements Iterator<T>
    {
        private boolean calledNext;
        private Node<T> prevNode;
        private Node<T> currNode;
        private Node<T> nextNode;


        /**
         * Constructs an iterator starting at the specified first node.
         * 
         * @author Carson Fouts
         * @version 4/2
         * 
         * @param firstNode the first node in the sorted linked list
         */
        public SLLIterator(Node<T> firstNode)
        {
            this.nextNode = firstNode;
        }

        /**
         * Returns true if the iterator has more elements.
         * 
         * @return true if the iterator has more elements, false otherwise
         */
        public boolean hasNext()
        {
            return nextNode != null;
        }

        /**
         * Returns the next element in the iteration.
         * 
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            prevNode = currNode;
            currNode = nextNode;
            nextNode = nextNode.getLink();
            calledNext = true;
            return currNode.getData();
        }

        /**
         * Removes the last element.
         * 
         * @throws IllegalStateException if the remove method is called 
         */
        public void remove()
        {

            if (!calledNext)
            {
                throw new IllegalStateException("Next not called");
            }
            if (prevNode == null)
            {
                head = nextNode;
            }
            else
            {
                prevNode.setLink(nextNode);
                currNode = prevNode;
            }
            length--;
            calledNext = false;
        }

    }
}
