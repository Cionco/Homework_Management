package de.fs.webarch.server.rest;

import java.util.HashMap;
import java.util.Map;

public class PortalDao {
	static PortalDao instance=new PortalDao();

	private Map<String, Portal> contentProvider = new HashMap<>();

	private PortalDao() {

		Portal todo = new Portal("1", "West-Portal");
		todo.setDescription("Ein schönes Portal in der Abendsonne");
		contentProvider.put("1", todo);
		todo = new Portal("2", "Ost-Portal");
		todo.setDescription("Portal am Morgen bringt keine Sorgen");
		contentProvider.put("2", todo);

	}
	public Map<String, Portal> getModel(){
		return contentProvider;
	}


}
