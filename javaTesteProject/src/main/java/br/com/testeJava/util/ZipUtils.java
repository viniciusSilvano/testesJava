package br.com.testeJava.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.util.zip.Deflater;
import java.util.zip.ZipOutputStream;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import br.com.testeJava.bo.observables.zip.RecuperadorArquivoObservable;

@Stateless
public class ZipUtils {
    private static final String OUTPUT_ZIP_FILE_NAME = "Folder.zip";
    private static final String SOURCE_FOLDER = "C:/Users/vinic/Downloads/PASTA"; // SourceFolder path
    
    public Response zipItLive() {
        byte[] buffer = new byte[1024];
        String source = new File(SOURCE_FOLDER).getName();
        Long inicioCompressao = System.currentTimeMillis();
        return Response.ok((StreamingOutput) output -> {
        	ZipOutputStream zos = null;
        	 try{
        		 zos = new ZipOutputStream(new BufferedOutputStream(output));
        		 System.out.println("Iniciando compressão");
        		 zos.setLevel(Deflater.NO_COMPRESSION);
        		 System.out.println("Output to Zip : " + OUTPUT_ZIP_FILE_NAME);
        		 new RecuperadorArquivoObservable(source,SOURCE_FOLDER,zos,true,buffer).generateFileList(new File(SOURCE_FOLDER));
        		 
                 System.out.println("Compressão finalizada em: " + (System.currentTimeMillis() - inicioCompressao));
             }finally {
            	 zos.close();
             }
        },MediaType.APPLICATION_OCTET_STREAM_TYPE)
        .header("Content-Disposition", " attachment; filename=" + OUTPUT_ZIP_FILE_NAME)
        .header("Transfer-Encoding", "chunked")
        .build();
       
    }
}
