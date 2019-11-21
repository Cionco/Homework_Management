package de.fs.webarch.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestAlive {

	public static String getResponse(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		InputStreamReader in = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
		BufferedReader rd = new BufferedReader(in);
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}

	public static void main(String[] args) throws Exception {
		String json = getResponse("http://localhost:8080/DynWebProject/status/alive.json");
		System.out.println(json);
		
		ObjectMapper mapper=new ObjectMapper();
		AliveBean bean=mapper.readValue(json,AliveBean.class);
		
		System.out.println((bean.isAlive?"":"not ") +"alive at "+new Date(bean.time));
	}

}
