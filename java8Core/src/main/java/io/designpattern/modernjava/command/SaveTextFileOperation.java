package io.designpattern.modernjava.command;

public class SaveTextFileOperation implements TextFileOperation {
    
    private final TextFile textFile;
    
    public SaveTextFileOperation(TextFile textFile) {
        this.textFile = textFile;
    }
    
    @Override
    public String execute() {
        return textFile.save();
    }
}