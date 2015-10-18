
/**
 * A replicated version of the Java ArrayList class.
 * @author Teddy Todorov
 * @version 9.18.14
 */
public class StringArrayList
{

    private int size;
    private String[] myString;

    /**
     * Constructor for the ArrayList.
     */
    public StringArrayList()
    {
        size = 0;
        myString = new String[10];
    }
    
    /**
     * Constructs an ArrayList with a specified size.
     * @param woohoo int to be the size of the arrayList.
     */
    public StringArrayList( int woohoo )
    {
        size = 0;
        if ( woohoo > 0 ) {
            myString = new String[woohoo];
        }
        else
        {
            myString = new String[10];
        }
    }

    /**
     * Returns the amount of accessible slots.
     * @return the amount of accessible slots.
     */
    public int size()
    {
        return size;
    }

    /**
     * Inserts specified String to the next available slot.
     * @param tString String to be inserted at the last slot of List.
     * @return true
     */
    public boolean add( String tString )
    {
        if ( size < myString.length )
        {
            myString[size] = tString;
            size++;
        }
        else
        {
            doubleMyArray();
            add(tString);
        }
        return true;
    }

    /**
     * Inserts specified String in specified index. 
     * Moves proceeding values down.
     * @param index Slot at which String will be inserted.
     * @param tString String to be inserted.
     */
    public void add( int index, String tString )
    {
        if ( index >= 0  && index <= size)
        {
            if ( size < myString.length)
            {
                if ( index == size )
                {
                    add(tString);
                }
                else {
                    for ( int i = size; i > index; i-- )
                    {
                        String temp = myString[i - 1];
                        myString[i] = temp;
                    }
                    myString[index] = tString;
                    size++;
                }
            }
            else
            {
                doubleMyArray();
                add(index, tString);
            }
        }
    }

    /**
     * Doubles the background array.
     * Does not double if the potential new array is
     * over the Integer.MAX_VALUE.
     */
    private void doubleMyArray()
    {
        String[] tempString = new String[1];
        if ( myString.length > 0 )
        {
            tempString = new String[myString.length * 2];
        }
        
        for ( int i = 0; i < myString.length; i++ )
        {
            tempString[i] = myString[i];
        }
        myString = tempString;
    }

    /**
     * Removes a String at the specified slot.
     * @param index Slot's value to be removed.
     * @return returns true.
     */
    public String remove( int index )
    {
        String valRem = null;
        if ( index < size && index >= 0 )
        {
            valRem = myString[index];
            if ( index != size - 1)
            {
                for ( int i = index; i < size - 1 ; i++ )
                {
                    String temp = myString[i + 1];
                    myString[i] = temp;
                }
                size--;
            }
            else
            {
                myString[index] = null;
                size--;
            }
            return valRem;
        }
        return null;
    }

    /**
     * returns the String at the specified index.
     * @param index Slot of the String to be obtained.
     * @return the String at the specified index.
     */
    public String get( int index )
    {
        if ( index >= 0 && index < size )
        {
            return myString[index];
        }
        else
        {
            return null;
        }
    }

    /**
     * Replaces the specified index's String to the specified String.
     * @param index Slot of the String to replace.
     * @param temp String to replace.
     * @return the String that was replaced.
     */
    public String set( int index, String temp )
    {
        if ( index < size && index >= 0 )
        {
            String tReturn = myString[index];
            myString[index] = temp;
            return tReturn;
        }
        return null;
    }

    /**
     * Returns the first occurrence of the specified String
     * or -1 if String is not in List.
     * @param temp Exact string to search for.
     * @return the first occurrence of the specified string.
     */
    public int indexOf( String temp )
    {
        for ( int i = 0; i < size; i++ )
        {
            if ( myString[i].equals(temp) )
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Empties list of values.
     */
    public void clear()
    { 
        String[] temp = new String[10]; 
        myString = temp;
        size = 0;
    } 
    
    /**
     * Returns true is the specified String is 
     * within the list, returns false if it is not.
     * @param temp String to search for.
     * @return true if list contains specified String.
     */
    public boolean contains( String temp )
    {
        return indexOf(temp) >= 0;
    }

    /**
     * Returns true if list has no values.
     * @return returns if the list has no values.
     */
    public boolean isEmpty()
    {
        return size <= 0;
    }

    /**
     * Returns the last occurrence of the specified 
     * String or -1 if String not in List.
     * @param temp Exact string to search for.
     * @return the last occurrence of the specified string.
     */
    public int lastIndexOf( String temp )
    {
        int tracker = -1;
        for ( int i = 0; i < size; i++ )
        {
            if ( myString[i].equals(temp) )
            {
                tracker = i;
            }
        }
        return tracker;
    }

    /**
     * Removes the first occurrence of the specified String.
     * @param temp String to remove.
     * @return returns attempted removed String.
     */
    public boolean remove( String temp )
    { 
        if ( indexOf(temp) >= 0 )
        {
            remove(indexOf(temp));
            return true;
        }
        return false;
    } 

    /**
     * Returns an array containing all values within the List. 
     * This does not affect the List used on. 
     * (From first to last)
     * @return an array with all values in sequential order.
     */
    public String[] toArray()
    {
        if ( size > 0 )
        {
            String [] result = new String[size];
            for ( int i = 0; i < size; i++ )
            {
                result[i] = myString[i];
            }
            return result;
        }
        return null;
    }

    /**
     * Returns a the List's contents in String format.
     * @return a String format of the List.
     */
    public String toString() { 
        if ( size > 0)
        {
            String result = "[";
            for ( int i = 0; i < size; i++ )
            {
                result += myString[i];
                if ( i != size - 1 )
                {
                    result += ", ";
                }
                else
                {
                    result += "]";
                }
            }
            return result;
        }
        return "[]";
    }

    /**
     * Reduces the List's size to the lists current length.
     */
    public void trimToSize()
    { 
        String[] temp = new String[size];
        for ( int i = 0; i < size; i++ )
        {
            temp[i] = myString[i];
        }
        myString = temp;
    }
    
    /**
     * Returns the myString[] length.
     * Used to test various methods.
     * @return the myString[] length.
     */
    public int lengthThing()
    { 
        return myString.length;
    }

}