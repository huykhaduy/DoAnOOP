package Modul.SupportModul;

import DanhSach.MyArray;
import Modul.NhanVien;

import java.io.*;



public class DocGhiFile<U extends Serializable> {
    private MyArray<U> list = new MyArray<>();

    public DocGhiFile() {
    }

    public DocGhiFile(MyArray<U> value){
        list = value;
    }

    public void ghiFileVaoThuMuc(String name){
        try {
            FileOutputStream filein = new FileOutputStream(name);
            ObjectOutputStream fileobj = new ObjectOutputStream(filein);
            for (int i = 0; i < list.getLength(); i++) {
                U data = list.getAt(i);
                fileobj.writeObject(data);
            }
            fileobj.close();
        } catch (IOException e) {
            System.out.println("<!> Lỗi ghi vào file " + name);
            if (e instanceof NotSerializableException) {
                System.out.println("<!> Không có Serilize ");
            }
            if (e instanceof InvalidClassException) {
                System.out.println("<!> Invalid class " + name);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public MyArray<U> docFileTuThuMuc(String name){
        U data;
        FileInputStream fis;
        ObjectInputStream fileobj = null;
        try{
            fis = new FileInputStream(name);
            fileobj = new ObjectInputStream(fis);
            while (true){
                data = (U) fileobj.readObject();
                if (data != null)
                    list.push(data);
            }
        } catch (IOException | ClassNotFoundException ignored) {
        } finally {
            try {
                if (fileobj != null) {
                    fileobj.close();
                }
            } catch (IOException e) {
//                System.out.println("");
            }
        }
        return list;
    }
}
