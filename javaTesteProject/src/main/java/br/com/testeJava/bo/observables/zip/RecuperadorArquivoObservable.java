package br.com.testeJava.bo.observables.zip;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.zip.ZipOutputStream;

import br.com.testeJava.bo.observer.zip.EnviarArquivoOutput;

public class RecuperadorArquivoObservable extends Observable {
	
	private Observer observer;
	private final String SOURCE_FOLDER;
	
	public RecuperadorArquivoObservable(String source, final String SOURCE_FOLDER, ZipOutputStream zos, boolean flush, byte[] buffer) {
		this.SOURCE_FOLDER = SOURCE_FOLDER;
		this.observer =  new EnviarArquivoOutput(source,this.SOURCE_FOLDER,zos,flush,buffer);
	}

	 public void generateFileList(File node) {
        // add file only
        if (node.isFile()) {
        	notifyObservers(generateZipEntry(node.toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename: subNote) {
                generateFileList(new File(node, filename));
            }
        }
    }
	 
	 private String generateZipEntry(String file) {
		 return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	 }
	 
	 @Override
	public void notifyObservers(Object arg) {
		 observer.update(this, arg);
	}
}
