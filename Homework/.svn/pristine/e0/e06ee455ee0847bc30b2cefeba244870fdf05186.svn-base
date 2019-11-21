package de.fs.webarch.server.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("mobile")
public class MobileClientService {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String serverVersion() {
		return "123";
	}

	// This method is called if TEXT_PLAIN is request
	// mobile/
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String checkClientVersion(@FormParam(value="platform") String platform, @FormParam(value="clientVersion") int clientVersion) {
		if("Android".equals(platform) && clientVersion>10) return ""; 
		return "<HTML><TITLE></TITLE><BODY>Supported Platforms:<ol id=\"platforms\"><li>Android</li></ol><BODY></HTML>";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("platforms.html")
	public String downloadConfig() {
		return "<HTML><TITLE></TITLE><BODY>Supported Platforms:<ol id=\"platforms\"><li>Android</li><li>Android</li></ol><BODY></HTML>";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("config")
	public String downloadConfig(@QueryParam(value = "platform") String platform) {
		return "<?xml version=\"1.0\"?>" + "<config><env key=\"schluessel\">Wert</env></config>";
	}


}
