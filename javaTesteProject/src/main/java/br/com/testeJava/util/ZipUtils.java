package br.com.testeJava.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

@Stateless
public class ZipUtils {
	private List <String> fileList;
    private static final String OUTPUT_ZIP_FILE = "C:\\Folder.zip";
    private static final String OUTPUT_ZIP_FILE_NAME = "Folder.zip";
    private static final String SOURCE_FOLDER = "C:\\Desenv2"; // SourceFolder path

    public ZipUtils() {
        fileList = new ArrayList < String > ();
    }

    public static void main(String[] args) {
        ZipUtils appZip = new ZipUtils();
        appZip.generateFileList(new File(SOURCE_FOLDER));
        
        System.out.println("Iniciando compressão");
        Long inicioCompressao = System.currentTimeMillis();
        appZip.zipIt(OUTPUT_ZIP_FILE);
        System.out.println("Compressão finalizada em: " + (System.currentTimeMillis() - inicioCompressao));
        
    }

    public void zipIt(String zipFile) {
        byte[] buffer = new byte[1024];
        String source = new File(SOURCE_FOLDER).getName();
        try( ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
           
            initZip(zipFile, buffer, source, zos, false);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Response zipItLive() {
        byte[] buffer = new byte[1024];
        String source = new File(SOURCE_FOLDER).getName();
        Long inicioCompressao = System.currentTimeMillis();
        return Response.ok((StreamingOutput) output -> {
        	 try( ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(output))) {
        		 System.out.println("Iniciando compressão");
        		 this.generateFileList(new File(SOURCE_FOLDER));
                 initZip(OUTPUT_ZIP_FILE_NAME, buffer, source, zos, true);
                 System.out.println("Compressão finalizada em: " + (System.currentTimeMillis() - inicioCompressao));
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
        },MediaType.APPLICATION_OCTET_STREAM_TYPE)
        .header("Content-Disposition", " attachment; filename=" + OUTPUT_ZIP_FILE_NAME)
        .header("Transfer-Encoding", "chunked")
        .build();
       
    }

	private void initZip(String zipFile, byte[] buffer, String source, ZipOutputStream zos, boolean flush)
			throws IOException, FileNotFoundException {
		zos.setLevel(Deflater.NO_COMPRESSION);

		System.out.println("Output to Zip : " + zipFile);
		BufferedInputStream in = null;

		for (String file: this.fileList) {
		    System.out.println("File Added : " + file);
		    ZipEntry ze = new ZipEntry(source + File.separator + file);
		    zos.putNextEntry(ze);
		    try {
		        in = new BufferedInputStream(new FileInputStream(SOURCE_FOLDER + File.separator + file));
		        int len;
		        while ((len = in .read(buffer)) > 0) {
		            zos.write(buffer, 0, len);
		        }
		        if(flush) {
		        	zos.flush();
		        }
		    } finally {
		        in.close();
		    }
		}

		zos.closeEntry();
		System.out.println("Folder successfully compressed");
	}

    public void generateFileList(File node) {
        // add file only
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.toString()));
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
}
