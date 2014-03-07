/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package com.jfinal.sog.typeconverter.impl;


import com.jfinal.sog.kit.common.CsvKit;
import com.jfinal.sog.typeconverter.TypeConverter;
import com.jfinal.sog.typeconverter.TypeConverterManagerBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Converts given object to an array. This converter is specific, as it
 * is not directly registered to a type; but created when needed.
 * Conversion rules:
 * <ul>
 * <li><code>null</code> value is returned as <code>null</code></li>
 * <li>source non-array value is checked for <code>Collections</code></li>
 * <li>if non-array element can't be resolved, it is converted to single element array</li>
 * <li>source array is converted to target array, by converting each element</li>
 * </ul>
 */
@SuppressWarnings("unchecked")
public class ArrayConverter<T> implements TypeConverter<T[]> {

    protected final TypeConverterManagerBean typeConverterManagerBean;
    protected final Class<T>                 targetComponentType;

    public ArrayConverter(TypeConverterManagerBean typeConverterManagerBean, Class<T> targetComponentType) {
        this.typeConverterManagerBean = typeConverterManagerBean;
        this.targetComponentType = targetComponentType;
    }

    public T[] convert(Object value) {
        if (value == null) {
            return null;
        }

        Class valueClass = value.getClass();

        if (!valueClass.isArray()) {
            // source is not an array
            return convertValueToArray(value);
        }

        // source is an array
        return convertArrayToArray(value);
    }

    /**
     * Converts type using type converter manager.
     */
    protected T convertType(Object value) {
        return typeConverterManagerBean.convertType(value, targetComponentType);
    }

    /**
     * Creates new array of target component type.
     * Default implementation uses reflection to create
     * an array of target type. Override it for better performances.
     */
    protected T[] createArray(int length) {
        return (T[]) Array.newInstance(targetComponentType, length);
    }

    /**
     * Creates an array with single element.
     */
    protected T[] convertToSingleElementArray(Object value) {
        T[] singleElementArray = createArray(1);

        singleElementArray[0] = convertType(value);

        return singleElementArray;
    }

    /**
     * Converts non-array value to array. Detects various
     * collection types and iterates them to make conversion
     * and to create target array.
     */
    protected T[] convertValueToArray(Object value) {
        if (value instanceof List) {
            List list = (List) value;
            T[] target = createArray(list.size());

            for (int i = 0; i < list.size(); i++) {
                Object element = list.get(i);
                target[i] = convertType(element);
            }

            return target;
        }

        if (value instanceof Collection) {
            Collection collection = (Collection) value;
            T[] target = createArray(collection.size());

            int i = 0;
            for (Object element : collection) {
                target[i] = convertType(element);
                i++;
            }

            return target;
        }

        if (value instanceof Iterable) {
            Iterable iterable = (Iterable) value;
            List<T> list = new ArrayList<T>();

            for (Object element : iterable) {
                list.add(convertType(element));
            }

            T[] target = createArray(list.size());
            return list.toArray(target);
        }

        if (value instanceof CharSequence) {
            String[] strings = CsvKit.toStringArray(value.toString());
            return convertArrayToArray(strings);
        }

        // everything else:
        return convertToSingleElementArray(value);
    }

    /**
     * Converts array value to array.
     */
    protected T[] convertArrayToArray(Object value) {
        Class valueComponentType = value.getClass().getComponentType();

        if (valueComponentType == targetComponentType) {
            // equal types, no conversion needed
            return (T[]) value;
        }

        T[] result;

        if (valueComponentType.isPrimitive()) {
            // convert primitive array to target array
            result = convertPrimitiveArrayToArray(value, valueComponentType);
        } else {
            // convert object array to target array
            Object[] array = (Object[]) value;
            result = createArray(array.length);

            for (int i = 0; i < array.length; i++) {
                result[i] = convertType(array[i]);
            }
        }

        return result;
    }

    /**
     * Converts primitive array to target array.
     */
    protected T[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
        T[] result = null;

        if (primitiveComponentType == int.class) {
            int[] array = (int[]) value;
            result = createArray(array.length);
            for (int i = 0; i < array.length; i++) {
                result[i] = convertType(array[i]);
            }
        } else if (primitiveComponentType == long.class) {
            long[] array = (long[]) value;
            result = createArray(array.length);
            for (int i = 0; i < array.length; i++) {
                result[i] = convertType(array[i]);
            }
        } else if (primitiveComponentType == float.class) {
            float[] array = (float[]) value;
            result = createArray(array.length);
            for (int i = 0; i < array.length; i++) {
                result[i] = convertType(array[i]);
            }
        } else if (primitiveComponentType == double.class) {
            double[] array = (double[]) value;
            result = createArray(array.length);
            for (int i = 0; i < array.length; i++) {
                result[i] = convertType(array[i]);
            }
        } else if (primitiveComponentType == short.class) {
            short[] array = (short[]) value;
            result = createArray(array.length);
            for (int i = 0; i < array.length; i++) {
                result[i] = convertType(array[i]);
            }
        } else if (primitiveComponentType == byte.class) {
            byte[] array = (byte[]) value;
            result = createArray(array.length);
            for (int i = 0; i < array.length; i++) {
                result[i] = convertType(array[i]);
            }
        } else if (primitiveComponentType == char.class) {
            char[] array = (char[]) value;
            result = createArray(array.length);
            for (int i = 0; i < array.length; i++) {
                result[i] = convertType(array[i]);
            }
        } else if (primitiveComponentType == boolean.class) {
            boolean[] array = (boolean[]) value;
            result = createArray(array.length);
            for (int i = 0; i < array.length; i++) {
                result[i] = convertType(array[i]);
            }
        }
        return result;
    }

}