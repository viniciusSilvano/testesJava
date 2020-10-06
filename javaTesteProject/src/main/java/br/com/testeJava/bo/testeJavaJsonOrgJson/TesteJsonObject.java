package br.com.testeJava.bo.testeJavaJsonOrgJson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.testeJava.util.FileUtils;
import protostream.com.google.gson.JsonObject;

public class TesteJsonObject {

	public static void main(String[] args) {
		Path path = Paths.get("C:\\Desenv\\projetos\\teste\\backend\\testesJava\\javaTesteProject\\src\\main\\java\\br\\com\\testeJava\\bo\\testeJavaJsonOrgJson\\arquivos\\objetoJson1.txt");
		try {
			JSONObject json = new JSONObject(FileUtils.readFileAsString(path.toString(), StandardCharsets.UTF_8));
			
			JsonObject meuObjetoInterno = null;
			String nome = json.getJSONObject("meuObjetoInterno").getString("nome");
			System.out.println(nome);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
