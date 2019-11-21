package de.fs.webarch.server.tag;

import java.io.IOException;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import de.fs.webarch.server.DBSupport;
import de.fs.webarch.server.rest.KursDao;
import de.fs.webarch.server.rest.UserDao;

public class ParticipantsTag extends SimpleTagSupport {
	
	private boolean newcourse;
	private int courseid;
	private boolean editable;
	
	public void setNewcourse(boolean newcourse) {
		this.newcourse = newcourse;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();

		Integer[] teilnehmer = new Integer[0];
		if(!newcourse) teilnehmer = KursDao.instance.getKursTeilnehmer(courseid);		
		
		int i = 0;
		String check = "";
		out.append("<div class='teilnehmer_liste'>");
		for(String name : UserDao.instance.getNames()) { 
			check = "";
			
			String[] split = name.split(", ");
			if(!newcourse && i < teilnehmer.length) 
				if(DBSupport.contains(teilnehmer, Integer.parseInt(split[0]))) {
					check = "checked";
					i++;
			}	
			
			if(!editable && !check.equals("checked")) continue;
			
			out.append("<div class='teilnehmer'><input type='checkbox' value='" +  split[0] + "' name='names' " + check + " " + (editable?"":"disabled") + ">" + split[1] + ", " + split[2] + "</input></div>");

		} 
		
		out.append("</div>");
	}
}
