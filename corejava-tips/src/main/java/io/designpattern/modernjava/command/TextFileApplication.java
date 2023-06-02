package io.designpattern.modernjava.command;

public class TextFileApplication {
    
    public static void main(String[] args) {
        
        TextFileOperation openTextFileOperation = new OpenTextFileOperation(new TextFile("file1.txt"));
        TextFileOperation saveTextFileOperation = new SaveTextFileOperation(new TextFile("file2.txt"));
        TextFileOperationExecutor textFileOperationExecutor = new TextFileOperationExecutor();
        System.out.println(textFileOperationExecutor.executeOperation(openTextFileOperation));
        System.out.println(textFileOperationExecutor.executeOperation(saveTextFileOperation));
        
        // implement without TextFileOperation class 
        // using Lambda expression
        System.out.println(textFileOperationExecutor.executeOperation(() ->"Opening file file3.txt"));
        textFileOperationExecutor.executeOperation(() -> "Saving file file1.txt");
        
        // using Method Reference
        TextFile textFile = new TextFile("file4.txt");
        System.out.println( textFileOperationExecutor.executeOperation(textFile::open));
        
    }   
}