package com.acme.is;

import java.io.File;


public class FileOutHandler {

	public String handleString(String input) {
		System.out.println("Copying text:\n" + input);
		return input.toUpperCase();
	}
	
	public File handleFile(File input) {
		System.out.println("Copying file:\n" + input.getAbsolutePath());
		return input;
	}

}