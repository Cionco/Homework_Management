package de.fs.webarch.server;

public class LoginBean {	
	public String username;
	public String password;
	
	/*public LoginBean(String json) {
		String[] keyvalues = json.substring(1, json.length() - 1).split(", ");
		for(String keyvalue : keyvalues) System.out.println(keyvalue);
		for(String keyvalue : keyvalues) {
			if(keyvalue.split(":")[0].equals("\"username\"")) username = keyvalue.split(":")[1].substring(1, keyvalue.split(":")[1].length() - 1);
			else if(keyvalue.split(":")[0].equals("\"password\"")) password = keyvalue.split(":")[1].substring(1, keyvalue.split(":")[1].length() - 1);
		}
	}*/
	
	public LoginBean() {
		
	}

	public boolean checkLogin() {
		return ContextListener.isUserAuthentic(username, password);
	}
}
