package de.fs.webarch.server.tag;

import java.io.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import de.fs.webarch.server.DBSupport;
import de.fs.webarch.server.rest.KursDao;

public class HeaderTag extends SimpleTagSupport {
	private boolean newcourse;
	private int courseid;
	private boolean editable;
	
	public void setNewcourse(boolean newcourse) {
		this.newcourse = newcourse;
	}
	
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	
	public void seteditable(boolean editable) {
		this.editable = editable;
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		String[] kurs = new String[0];
		String string;
		if(!newcourse) kurs = KursDao.instance.getKursProperties(courseid);

		out.append("<div class=\"Header_fields\">\n");
			string = !newcourse?kurs[0]:"";
			out.append("	<input type=\"text\" placeholder=\"Kürzel\" name=\"abb\" value=\"" + string + "\"" + (editable?"":"readonly") + "/>\n");
			string = !newcourse?kurs[1]:"";
			out.append("	<input type=\"text\" placeholder=\"Titel\" name=\"title\" value=\"" + string + "\"" + (editable?"":"readonly") + "/>\n"); 
		out.append("</div>");
	}
}
