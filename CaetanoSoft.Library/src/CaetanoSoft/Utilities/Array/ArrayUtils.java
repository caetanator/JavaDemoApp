//******************************************************************************
// Project: CaetanoSoft.Library
// URL:     https://github.com/caetanator/JavaDemoApp/
// File:    ArrayUtils.java
//
// Description:
//          This class handles the manipulation of arrays data.
//
// Copyright (C) 2008-2022:
//          José Caetano Silva / CaetanoSoft
//
// License:
//          This file is part of CaetanoSoft.Library.
//
//          CaetanoSoft.Library is free software: you can redistribute it and/or modify
//          it under the terms of the GNU General Public License as published by
//          the Free Software Foundation, either version 3 of the License, or
//          (at your option) any later version.
//
//          CaetanoSoft.Library is distributed in the hope that it will be useful,
//          but WITHOUT ANY WARRANTY; without even the implied warranty of
//          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//          GNU General Public License for more details.
//
//          You should have received a copy of the GNU General Public License
//          along with CaetanoSoft.Library.  If not, see <http://www.gnu.org/licenses/>.
//******************************************************************************

package CaetanoSoft.Utilities.Array;

/**
 * Esta classe implementa métodos para a manipulação de dados de matrizes e
 * vectores, como por exemplo, de remoção de linhas de um vector de objectos.
 *
 * @author  José Caetano Silva (jcaetano@eugeniobranco.pt)
 * @version 1.0.2, 2016-09-16
 * @since   1.0
 */
public class ArrayUtils
{
    /**
     * Singleton pattern, constructor is not allow.
     */
    private ArrayUtils()
    {
        // Classe padrão Singleton, a instanciação não é permitida.
    }

    /**
     * Singleton pattern, cloning is not allow.
     *
     * @return  A cloned object of the class <i>Object</i>
     * @throws  java.lang.CloneNotSupportedException
     * @see     java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        // Classe padrão Singleton, a clonagem não é permitida.
        //super.clone();

        throw new CloneNotSupportedException("This singleton pattern class instance cannot be cloned");

        //return null;
    }

    /**
     * Elimina os espaços em branco do início e fim das strings do array.
     *
     * @param astrStrings array de strings para eliminar os caracteres brancos de todos os elementos
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
     * Remove um elemento do array.
     *
     * @param aobjObjects   array a manipular
     * @param iItemToDel    número do item a remover
     * @return  Um array de strings sem o item referido.
     */
    public static final Object[] removeElement(Object[] aobjObjects, int iItemToDel)
    {
        Object[] objArrayTemp = null;

        // Cria um objecto de retorno compatível com o tipo do objecto de entrada
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

        // Copia os elementos a manter
        System.arraycopy(aobjObjects, 0, objArrayTemp, 0, iItemToDel);
        System.arraycopy(aobjObjects, iItemToDel + 1, objArrayTemp, iItemToDel, aobjObjects.length - 1 - iItemToDel);

        return objArrayTemp;
    }
}
