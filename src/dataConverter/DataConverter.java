/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataConverter;

import java.io.*;
import java.util.*;

/**
 *
 * @author User
 */
public class DataConverter implements IFileConverter {

    public DataConverter() {
    }

    @Override
    public String toBinary(String inputFileName, String outputFileName, String charSet) {
        if (inputFileName != null) {
            File inFile = new File(inputFileName);
            if (inFile.isFile() & inFile.length() != 0 & inFile.canRead()) {
                File outFile = new File(outputFileName);
                try ( BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile), charSet));  DataOutputStream dos = new DataOutputStream(new FileOutputStream(outFile))) {

                    StringBuilder sb = new StringBuilder();
                    int i;
                    while ((i = br.read()) != -1) {
                        sb.append((char) i);
                    }
                    String[] strWords = sb.toString().split(" ");
                    String[] strWordsNew = new String[strWords.length - 1];
                    System.arraycopy(strWords, 1, strWordsNew, 0, strWords.length - 1);

                    for (String s : strWordsNew) {
                        if (s.matches("\\d+")) {
                            dos.writeInt(Integer.parseInt(s));
                        } else if (s.matches("\\d+.\\d+")) {
                            dos.writeDouble(Double.parseDouble(s));
                        } else {
                            dos.writeUTF(s);
                        }

                    }
                    return outFile.getAbsolutePath();

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            return "Error";
        }
        return "Error";

    }

    @Override
    public String toText(String inputFileName, String outputFileName, String charSet) {
        if (inputFileName != null) {
            File inFile = new File(inputFileName);
            if (inFile.isFile() & inFile.length() != 0 & inFile.canRead()) {
                File outFile = new File(outputFileName);
                try ( DataInputStream dis = new DataInputStream(new FileInputStream(inFile));
                        PrintWriter pw = new PrintWriter(outFile, charSet);
                        DataInputStream dis2 = new DataInputStream(new FileInputStream(inFile));
                        DataInputStream dis3 = new DataInputStream(new FileInputStream(inFile))) {

                    Double resDBL = 0d;
                    Integer resINT = 0;
                    String resTXT = "";

                    String testDBL = "";
                    String testINT = "";
                    String testTXT = "";

                    resDBL = dis.readDouble();
                    resINT = dis2.readInt();
                    resTXT = dis3.readUTF();

                    testDBL = resDBL.toString();
                    testINT = resINT.toString();
                    testTXT = resTXT;

                    if (resDBL.toString().matches("\\d+.\\d+")) {
                        while (dis.available() > 0) {
                            resDBL = dis.readDouble(); //Double
                            testDBL = testDBL + " " + String.valueOf(resDBL);
                        }
                        pw.write(testDBL);
                        pw.write(" ");
                        System.out.println(testDBL);

                    } 
                    
                    else if (resINT.toString().matches("\\d+") & !resTXT.matches("\\D+")) {
                        while (dis2.available() > 0) {
                            resINT = dis2.readInt();
                            testINT = testINT + " " + String.valueOf(resINT);
                        }
                        pw.write(testINT);
                        pw.write(" ");
                        System.out.println(testINT);

                    } else {

                        while (dis3.available() > 0) {
                            resTXT = dis3.readUTF();
                            testTXT = testTXT + " " + resTXT;
                        }
                        pw.write(testTXT);
                        pw.write(" ");
                        System.out.println(testTXT);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            return "Error";
        }
        return "Error";

    }

    @Override
    public double getSum(String fileName) throws ConverterException {
        if (fileName == null) {
            throw new ConverterException("File name is NULL");
        }
        File fSum = new File(fileName);
        if (fSum.length() == 0) {
            throw new ConverterException("Empty file");
        }
        Integer intRes = 0;
        Double dRes = 0d;
        Double result = 0d;
        try ( BufferedReader br = new BufferedReader(new FileReader(fSum))) {
            String[] strBuf = new String[3];
            for (int i = 0; i < strBuf.length; i++) {
                strBuf[i] = br.readLine();
                if (strBuf[i].isEmpty()) {
                    break;
                }
                System.out.println(strBuf[i]);
                String strFull = strBuf[i];
                String[] strWords = strFull.split(" ");
                for (String s : strWords) {
                    if (s.matches("\\d+")) {
                        intRes = intRes + Integer.parseInt(s);
                    } else if (s.matches("\\d+.\\d+")) {
                        dRes = dRes + Double.parseDouble(s);
                    }
                    result = (double) intRes + dRes;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("--------------------");
        return result;
    }
}
