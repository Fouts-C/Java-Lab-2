package storage;

/**
 * A class that implements a double linked sequence.
 * 
 * @author Carson Fouts
 * @version 3/20
 */
public class DoubleLinkedSeq implements Cloneable 
{

    private int manyNodes;
    private DoubleNode tail;
    private DoubleNode head;
    private DoubleNode precursor;
    private DoubleNode cursor;

    /**
     * Constructs an empty DoubleLinkedSeq.
     */
    public DoubleLinkedSeq() 
    {
        manyNodes = 0;
        tail = null;
        head = null;
        precursor = null;
        cursor = null;
    }

    /**
     * Returns the number of elements in the sequence.
     *
     * @return the size of the sequence
     */
    public int size() 
    {
        return manyNodes;
    }

    /**
     * Adds a new node with the given data after the cursor.
     *
     * @param data the data to be added
     */
    public void addAfter(double data) 
    {
        if (manyNodes == 0) 
        {
            head = new DoubleNode(data);
            tail = head;
            cursor = head;
        } 
        else if (manyNodes == 1) 
        {
            DoubleNode newNd = new DoubleNode(data);
            head.setLink(newNd);
            tail = newNd;
            cursor = tail;
            precursor = head;
        } 
        else if (cursor == head) 
        {
            DoubleNode newNd = new DoubleNode(data, cursor.getLink());
            head.setLink(newNd);
            cursor = cursor.getLink();
            precursor = head;
        } 
        else if (cursor != head && precursor != null) 
        {
            DoubleNode newNd = new DoubleNode(data, cursor.getLink());
            cursor.setLink(newNd);
            if (cursor == tail) 
            {
                tail = tail.getLink();
            }
            cursor = cursor.getLink();
            precursor = precursor.getLink();
        } 
        else if (!isCurrent()) 
        {
            DoubleNode newNd = new DoubleNode(data);
            tail.setLink(newNd);
            precursor = tail;
            tail = tail.getLink();
            cursor = tail;
        }
        manyNodes++;
    }

    /**
     * Adds a new node with the given data before the cursor.
     *
     * @param data the data to be added
     */
    public void addBefore(double data) 
    {
        if (head == null && tail == null) 
        {
            head = new DoubleNode(data);
            tail = head;
            cursor = head;
        } 
        else if (cursor == null) 
        {
            head = new DoubleNode(data, head);
            cursor = head;
        } 
        else if (precursor == null) 
        {
            head = new DoubleNode(data, head);
            cursor = head;
        } 
        else if (cursor != null && precursor != null) 
        {
            DoubleNode newNd = new DoubleNode(data, cursor);
            precursor.setLink(newNd);
            cursor = newNd;
        }
        manyNodes++;
    }

    /**
     * Adds all elements from another DoubleLinkedSeq to this sequence.
     *
     * @param addend the sequence to be added
     */
    public void addAll(DoubleLinkedSeq addend) 
    {
        if (this == addend)
        {
            addend = addend.clone();
        }
        if (addend.head == null)
        {
            return;
        }
        if (this.head == null)
        {
            this.head = addend.head;
            this.tail = addend.tail;
        }
        else
        {
            this.tail.link = addend.head;
            this.tail = addend.tail;
        }
        this.manyNodes += addend.manyNodes;
    }

    /**
     * Checks if there is a current element.
     *
     * @return true if there is a current element, otherwise false
     */
    public boolean isCurrent() 
    {
        return cursor != null;
    }

    /**
     * Moves the cursor to the beginning of the sequence.
     */
    public void start() 
    {
        cursor = head;
        precursor = null;
    }

    /**
     * Moves the cursor forward one element.
     *
     * @throws IllegalStateException if there is no current element
     */
    public void advance() 
    {
        if (cursor == null) 
        {
            throw new IllegalStateException("No cursor");
        }
        precursor = cursor;
        cursor = cursor.getLink();
    }

    /**
     * Gets the data of the current element.
     *
     * @return the data of the current element
     * @throws IllegalStateException if there is no current element
     */
    public double getCurrent() 
    {
        if (!isCurrent()) 
        {
            throw new IllegalStateException("No current element");
        }
        return cursor.getData();
    }

    /**
     * Removes the current element from the sequence.
     *
     */
    public void removeCurrent() 
    {
        if (!isCurrent()) 
        {
            throw new IllegalStateException("No current element");
        } 
        else 
        {
            if (head == tail) 
            {
                head = null;
                tail = null;
                cursor = null;
            } 
            else if (cursor == head && precursor == null) 
            {
                head = head.getLink();
                cursor = head;
            } 
            else if (cursor != tail && precursor != null) 
            {
                cursor = cursor.getLink();
                precursor.setLink(cursor);
            } 
            else if (cursor == tail && precursor != null) 
            {
                cursor = null;
                precursor.setLink(null);
                precursor = null;
            }
            manyNodes--;
        }
    }

    /**
     * Creates a deep copy of the DoubleLinkedSeq.
     *
     * @return a clone of the DoubleLinkedSeq
     */
    @Override
    public DoubleLinkedSeq clone() 
    {
        DoubleLinkedSeq cop;
        try 
        {
            cop = (DoubleLinkedSeq) super.clone();
        } 
        catch (CloneNotSupportedException e) 
        {
            throw new RuntimeException("Implement cloneable");
        }
        if (cursor == null)
        {
            DoubleNode[] ndArray = DoubleNode.listCopyWithTail(head);
            cop.head = ndArray[0];
            cop.tail = ndArray[1];
        } 
        else if (cursor == head) 
        {
            DoubleNode[] ndArray = DoubleNode.listCopyWithTail(head);
            cop.head = ndArray[0];
            cop.tail = ndArray[1];
            cop.precursor = null;
            cop.cursor = cop.head;
        } 
        else if (cursor != null && precursor != null) 
        {
            DoubleNode[] tempArray = DoubleNode.listPart(head, precursor);
            cop.head = tempArray[0];
            cop.precursor = tempArray[1];
            DoubleNode[] ndArray = DoubleNode.listPart(cursor, tail);
            cop.cursor = ndArray[0];
            cop.tail = ndArray[1];
            cop.precursor.setLink(cop.cursor);
        }
        return cop;
    }

    /**
     * Returns a string representation of the DoubleLinkedSeq.
     *
     * @return a string representation of the DoubleLinkedSeq
     */
    @Override
    public String toString() 
    {
        String seqString = "";
        if (!isCurrent()) 
        {
            if (head == null) 
            {
                seqString += "<>";
            } else if (manyNodes == 1) 
            {
                seqString += "<" + head.getData() + ">";
            } else if (manyNodes > 1) 
            {
                seqString += "<[" + head.getData() + "]>";
            }
        } 
        else 
        {
            if (manyNodes == 1) 
            {
                seqString += "<[" + head.getData() + "]>";
            } 
            else if (manyNodes > 1) 
            {
                seqString += "<";

                for (DoubleNode i = head; i != null; i = i.getLink()) 
                {
                    if (i == cursor) 
                    {
                        seqString += "[" + i.getData() + "]";
                    } 
                    else 
                    {
                        seqString += "" + i.getData();
                    }
                    if (i.getLink() != null) 
                    {
                        seqString += ", ";
                    }
                }
                seqString += ">";
            }
        }
        return seqString;
    }

    /**
     * Checks whether this DoubleLinkedSeq is equal to another object.
     *
     * @param other the object to compare to
     * @return true if the sequences are equal, otherwise false
     */
    @Override
    public boolean equals(Object other) 
    {
        boolean flag = true;
        DoubleLinkedSeq seqComp = (DoubleLinkedSeq) other;
        if (manyNodes == seqComp.manyNodes) 
        {
            for (DoubleNode i = head, j = seqComp.head; i != null; 
                i = i.getLink(), j = j.getLink()) 
            {
                if (i.getData() != j.getData()) 
                {
                    flag = false;
                    break;
                }
                if (i == cursor && j != seqComp.cursor) 
                {
                    flag = false;
                    break;
                }
                if (j == seqComp.cursor && i != cursor) 
                {
                    flag = false;
                    break;
                }
            }
        } 
        else 
        {
            flag = false;
        }
        return flag;
    }

    /**
     * Concatenates two DoubleLinkedSeq objects.
     *
     * @param s1 the first sequence
     * @param s2 the second sequence
     * @return a new DoubleLinkedSeq containing elements of both sequences
     */
    public static DoubleLinkedSeq 
        concatenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2) 
    {
        DoubleLinkedSeq cop2 = new DoubleLinkedSeq();

        DoubleNode i = s1.head;
        while(i != null)
        {
            cop2 .addAfter(i.getData());
            cop2.advance();
            i = i.link;
        }

        i = s2.head;
        while(i != null)
        {
            cop2.addAfter(i.getData());
            cop2.advance();
            i = i.link;
        }
        cop2.cursor = null;
        return cop2;
    }
}
