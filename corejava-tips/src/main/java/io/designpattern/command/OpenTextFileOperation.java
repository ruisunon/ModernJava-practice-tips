package io.designpattern.command;

public class OpenTextFileOperation implements TextFileOperation {

	 private TextFile textFile;
	    	    
	 public OpenTextFileOperation(TextFile textFile) {
		super();
		this.textFile = textFile;
	 }



	 @Override
	 public String execute() {
	    return textFile.open();
	 }
}
