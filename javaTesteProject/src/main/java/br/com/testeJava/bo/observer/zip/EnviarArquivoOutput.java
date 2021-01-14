package br.com.testeJava.bo.observer.zip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class EnviarArquivoOutput implements Observer {
	
	private String source;
	private final String SOURCE_FOLDER;
	private ZipOutputStream zos;
	private boolean flush;
	byte[] buffer;
	public EnviarArquivoOutput(String source, final String SOURCE_FOLDER, ZipOutputStream zos, boolean flush, byte[] buffer) {
		this.source = source;
		this.SOURCE_FOLDER = SOURCE_FOLDER;
		this.zos = zos;
		this.flush = flush;
		this.buffer = buffer;
	}

	@Override
	public void update(Observable o, Object arg) {
		String file = (String) arg;
	    System.out.println("File Added : " + file);
	    BufferedInputStream in = null;
	    ZipEntry ze = new ZipEntry(source + File.separator + file);
	    try {
	    	zos.putNextEntry(ze);
	        in = new BufferedInputStream(new FileInputStream(SOURCE_FOLDER + File.separator + file));
	        int len;
	        while ((len = in .read(buffer)) > 0) {
	            zos.write(buffer, 0, len);
	            if(flush) {
	            	zos.flush();
	            }
	        }
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    finally {
	        try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		
	}

}
