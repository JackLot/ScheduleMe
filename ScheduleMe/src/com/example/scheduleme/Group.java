package com.example.scheduleme;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Group {
	private String name;
	private ArrayList<Person> people;
	private String scheduleUrl;
	private Bitmap pic;
	private String description;
	
	public Group(String name, ArrayList<Person> people, String scheduleUrl, Bitmap pic, String description) {
		this.name = name;
		this.people = people;
		this.scheduleUrl = scheduleUrl;
		this.pic = pic;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}

	public String getScheduleUrl() {
		return scheduleUrl;
	}

	public void setScheduleUrl(String scheduleUrl) {
		this.scheduleUrl = scheduleUrl;
	}

	public Bitmap getPic() {
		return pic;
	}

	public void setPic(Bitmap pic) {
		this.pic = pic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
