package com.example.rewise;

import java.util.ArrayList;


public class Course {
	String _id;
	String code;
	String name;
	private boolean selected;
	int backimg;
	private ArrayList<Quiz> children;
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public int getBack() {
		return backimg;
	}
	
	public void setBack(int code) {
		this.backimg = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Quiz> getChildren()
    {
        return children;
    }
     
    public void setChildren(ArrayList<Quiz> children)
    {
        this.children = children;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
	
}
