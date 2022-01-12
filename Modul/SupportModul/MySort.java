package Modul.SupportModul;

import DanhSach.MyArray;
import Modul.MyCompare;

import java.io.Serializable;

public class MySort<U extends MyCompare<U>> {
    public void sort(MyArray<U> list,int type,boolean isIncrease){
        for (int i=0;i<list.getLength()-1;i++){
            int k = i;
            for (int j=i+1;j< list.getLength();j++){
                if (list.getAt(k).compareTo(list.getAt(j),type)>0==isIncrease)
                    k=j;
            }
            U temp = list.getAt(k);
            list.setAt(list.getAt(i),k);
            list.setAt(temp,i);
        }
//        for (int i=0;i<list.getLength();i++){
//            System.out.println(list.getAt(i).toString());
//        }
    }
}
