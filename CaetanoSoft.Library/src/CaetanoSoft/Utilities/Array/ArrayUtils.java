//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    ArrayUtils.java
//
// Description:
//          This class handles the manipulation of arrays data.
//
// Copyright:
//          © 2008-2026 José Caetano Silva / CaetanoSoft. All rights reserved.
//
// License:
//          This file is part of CaetanoSoft.Library.
//
//          CaetanoSoft.Library is free software: you can redistribute it and/or 
//          modify it under the terms of the GNU General Public License as 
//          published by the Free Software Foundation, either version 3 of the 
//          License, or (at your option) any later version.
//
//          CaetanoSoft.Library is distributed in the hope that it will be 
//          useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
//          of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//          GNU General Public License for more details.
//
//          You should have received a copy of the GNU General Public License
//          along with CaetanoSoft.Library. If not, see 
//          <https://www.gnu.org/licenses/gpl-3.0.html>.
//******************************************************************************


package CaetanoSoft.Utilities.Array;

/**
 * This class implements methods to manipulate arrays and vectors data, 
 * for example it allows remove lines from an objects vector.
 *
 * @author  José Caetano Silva
 * @version 1.02.0001, 2026-03-30
 * @since   1.00
 */
public class ArrayUtils
{
    /**
     * Default constructor.
     *
     * Not to be called by applications.
     *
     * @since   1.00
     */
    private ArrayUtils()
    {
        // Singleton pattern class, instantiation not allowed
        //super();
    }

    /**
     * Don't permit creating an object by cloning it.
     *
     * @since   1.00
     * @return  A cloned object of the class <code>Object</code>.
     * @throws  java.lang.CloneNotSupportedException
     * @see     java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        // Singleton pattern class, cloning not allowed
        //super.clone();

        throw new CloneNotSupportedException("This instance of the Singleton pattern class cannot be cloned");

        //return null;
    }

    /**
     * Removes leading and trailing whitespace from all strings in an array.
     *
     * @param astrStrings strings array to be trimed
     */
    public static final void trimStringArray(String[] astrStrings)
    {
        for(int i = 0; i < astrStrings.length; ++i)
        {
            if((astrStrings[i] != null) && !astrStrings[i].isEmpty())
            {
                astrStrings[i] = astrStrings[i].trim();
            }
        }
    }

    /**
     * Remove's one element from an array, by its order index.
     *
     * @param aobjObjects   array to be manipulated
     * @param iItemToDel    number of the item to be removed
     * @return  The same array without the referenced item.
     */
    public static final Object[] removeElement(Object[] aobjObjects, int iItemToDel)
    {
        Object[] objArrayTemp = null;

        // Creates a return object compatible with the type of the input object
        if(aobjObjects instanceof String[])
        {
            objArrayTemp = new String[aobjObjects.length - 1];
        }
        else if(aobjObjects instanceof Double[])
        {
            objArrayTemp = new Double[aobjObjects.length - 1];
        }
        else if(aobjObjects instanceof Integer[])
        {
            objArrayTemp = new Integer[aobjObjects.length - 1];
        }
        else
        {
            objArrayTemp = new Object[aobjObjects.length - 1];
        }

        // Copies the elements to be kept
        System.arraycopy(aobjObjects, 0, objArrayTemp, 0, iItemToDel);
        System.arraycopy(aobjObjects, iItemToDel + 1, objArrayTemp, iItemToDel, aobjObjects.length - 1 - iItemToDel);

        return objArrayTemp;
    }
}

