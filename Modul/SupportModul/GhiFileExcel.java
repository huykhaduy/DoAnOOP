package Modul.SupportModul;

import DanhSach.MyArray;
import Modul.Excelable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class GhiFileExcel<U extends Excelable> {
    private MyArray<U> list = new MyArray<>();

    public GhiFileExcel(MyArray<U> value){
        list = value;
    }
    public void ghiRaFileExcel(String filename, String title){
        try {
            OutputStream os = new FileOutputStream(filename);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            pw.println(title);
            for (int i=0;i<list.getLength();i++){
                pw.println(Integer.toString(i+1) +","+removeAccent(list.getAt(i).toExecelString()));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("<!> Vui lòng đóng file trước khi ghi!");
            return;
        }
        System.out.println("<!> Tạo file excel thành công !");
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
