
package DanhSach;//This class made by Duy, still have error

import java.io.*;
import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class MyArray<U> implements Serializable{
    private int length;
    private U[] a;

    public MyArray() {
    }

    public MyArray(int length) {
        setLength(length);
    }

    public void setLength(int length) {
        if (length > 0) {
            this.length = length;

            a = (U[]) new Object[length];
//            a = (U[]) Array.newInstance(a.getClass(),length);
        }
    }

    public int getLength() {
        return length;
    }

    public U getAt(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        return a[index];
    }

    public void setAt(U value,int index){
        if (index<0 || index>= length){
            throw new IndexOutOfBoundsException();
        }
        a[index] = value;
    }
    
    public void addAt(int index, U value) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException();
        }
        if (length == 0) {
            a = (U[]) new Object[1];
//            a = (U[]) Array.newInstance(a.getClass(),1);
            a[0] = value;
            length = 1;
            return;
        }
        a = copyArray(length + 1);
        length++;
        for (int i = length - 1; i > index; i--) {
            a[i] = a[i - 1];
        }
        a[index] = value;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < length - 1; i++) {
            a[i] = a[i + 1];
        }
        if (length <= 1) {
            a = null;
            length = 0;
            return;
        }
        a = copyArray(length - 1);
        length--;
    }

    public void push(U value) {
        addAt(length, value);
    }

    public U pop() {
        U temp = a[length - 1];
        removeAt(length - 1);
        return temp;
    }

    public U[] copyArray(int length) {
        U[] copy = (U[]) new Object[length];
//        copy = (U[]) Array.newInstance(a.getClass(),length);
        int size = length > this.length ? this.length : length;
        for (int i = 0; i < size; i++) {
            copy[i] = a[i];
        }
        return copy;
    }

    public U[] getArray(){
        U[] copy = (U[]) new Object[length];
//        copy = (U[]) Array.newInstance(a.getClass(),length);
        for (int i = 0; i < length; i++) {
            copy[i] = a[i];
        }
        return copy;
    }

    public MyArray<U> copy(int length) {
        MyArray<U> copy = new MyArray<U>(length);
        copy.a = copyArray(length);
        return copy;
    }

    public int indexOf(U temp){
        for (int i=0;i<length;i++){
            if (temp == a[i]){
                return i;
            }
        }
        return -1;
    }
}

