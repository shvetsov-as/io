/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataConverter;

/**
 *
 * @author User
 */
public interface IFileConverter {
    public String toBinary(String inputFileName, String outputFileName, String charSet);
    public String toText(String inputFileName, String outputFileName, String charSet);
    public double getSum(String fileName) throws ConverterException;
}
