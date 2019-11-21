package de.fs.webarch.server.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("portals")
public class PortalsResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    // Return the list of Portals to the user in the browser
//    @GET
//    @Path("text")
//    @Produces(MediaType.TEXT_XML)
//    public List<Portal> getPortalsBrowser() {
//        List<Portal> Portals = new ArrayList<Portal>();
//        Portals.addAll(PortalDao.instance.getModel().values());
//        return Portals;
//    }

    // Return the list of Portals for applications
    @GET
    @Path("xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Portal> getXmlPortals() {
        List<Portal> portals = new ArrayList<Portal>();
        portals.addAll(PortalDao.instance.getModel().values());
        return portals;
    }
    
    // Return the list of Portals for applications
    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Portal> getJsonPortals() {
        List<Portal> portals = new ArrayList<Portal>();
        portals.addAll(PortalDao.instance.getModel().values());
        return portals;
    }
    
    @GET
    @Path("html")
    @Produces(MediaType.TEXT_HTML)
    public String getHtmlPortals() {
        List<Portal> portals = new ArrayList<Portal>();
        portals.addAll(PortalDao.instance.getModel().values());
        StringBuffer buf=new StringBuffer();
        buf.append("<html><head><meta ></head><body><ol id=\"portal-list\">");
        for (Portal portal : portals) {
        	 buf.append("<li>"+portal.getTitle()+"</li>");
		}
        buf.append("</ol></body></html>");
        return buf.toString();
    }


    // retuns the number of Portals
    // Use http://localhost:8080/DynWebApp/rest/Portals/count
    // to get the total number of records
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = PortalDao.instance.getModel().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    // see https://docs.oracle.com/cd/E24329_01/web.1211/e24983/secure.htm#RESTF256
    //@RolesAllowed("ADMIN") 
    public String newPortal(@FormParam("id") String id,
            @FormParam("title") String title,
            @FormParam("summary") String summary,
            @FormParam("description") String description,
            @Context HttpServletResponse servletResponse) throws IOException {
        Portal Portal = new Portal(id, title);
        if (description != null) {
            Portal.setDescription(description);
        }
        if (summary != null) {
            Portal.setDescription(summary);
        }
        PortalDao.instance.getModel().put(id, Portal);

        //servletResponse.sendRedirect("../portallist.jsp"); // Serverside Redirect
        // Clientside Redirect:
        return "<html><head><meta http-equiv=\"refresh\" content=\"5; URL=../index.jsp\"></head><body>OK</body></html>";
    }

    // Defines that the next path parameter after Portals is
    // treated as a parameter and passed to the PortalResources
    // Allows to type http://localhost:8080/com.vogella.jersey.Portal/rest/Portals/1
    // 1 will be treaded as parameter Portal and passed to PortalResource
    @Path("{Portal}")
    public PortalResource getPortal(@PathParam("Portal") String id) {
        return new PortalResource(uriInfo, request, id);
    }

}
