/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataConverter;

import java.io.File;



/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args) {
//        File f = new File("text.txt");
//        File f1 = new File("text1.txt");
//        f.createNewFile();
//        f1.createNewFile();
//        System.out.println(f.exists());
        
        DataConverter convert = new DataConverter();
        
        try {
            System.out.println("--------------------");
            System.out.println("Сумма чисел в текстовом файле: " + convert.getSum("getsum.txt"));
            convert.toBinary("INT.txt", "ToBinnary.bin", "UTF-8");
            convert.toText("ToBinnary.bin", "ToText.txt", "UTF-8");
        } catch (ConverterException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
