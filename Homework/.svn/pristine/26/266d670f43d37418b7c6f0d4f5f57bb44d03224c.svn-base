package de.fs.webarch.server.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

public class PortalResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	
	public PortalResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Portal getPortal() {
		Portal Portal = PortalDao.instance.getModel().get(id);
		if(Portal==null)
			throw new RuntimeException("Get: Portal with " + id +  " not found");
		return Portal;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Portal getPortalHTML() {
		Portal Portal = PortalDao.instance.getModel().get(id);
		if(Portal==null)
			throw new RuntimeException("Get: Portal with " + id +  " not found");
		return Portal;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putPortal(JAXBElement<Portal> Portal) {
		Portal c = Portal.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deletePortal() {
		Portal c = PortalDao.instance.getModel().remove(id);
		if(c==null)
			throw new RuntimeException("Delete: Portal with " + id +  " not found");
	}

	private Response putAndGetResponse(Portal Portal) {
		Response res;
		if(PortalDao.instance.getModel().containsKey(Portal.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		PortalDao.instance.getModel().put(Portal.getId(), Portal);
		return res;
	}



}
