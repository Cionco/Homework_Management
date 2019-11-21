package de.fs.webarch.server;

import java.util.ArrayList;

public class TypedArrayList<E> extends ArrayList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6749832831554354681L;

	public int getInt(int columnIndex) {
		try {
			return Integer.parseInt((String) this.get(columnIndex));
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean getBoolean(int columnIndex) {
		return Boolean.parseBoolean((String) this.get(columnIndex));
	}
	
	public String getString(int columnIndex) {
		return (String) this.get(columnIndex);
	}
	
	public byte getByte(int columnIndex) {
		try {
			return Byte.parseByte((String) this.get(columnIndex));
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public double getDouble(int columnIndex) {
		try {
			return Double.parseDouble((String) this.get(columnIndex));
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
