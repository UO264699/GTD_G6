package com.capgemini.model;

import java.util.Date;

public class Task {

	private int id;
    private String title;
    private String comments;
    private Date created;
    private Date planned;
    private Date finished;
    private int user_id;
    private int category_id;
    
    public Task() {
    	
    }

    public Task(int id,String title, String comments, Date created, Date planned, Date finished, int user_id, int category_id) {
        this.title = title;
        this.comments = comments;
        this.created = created;
        this.planned = planned;
        this.finished = finished;
        this.user_id = user_id;
        this.category_id = category_id;
        this.id = id;
    }
    

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPlanned() {
        return planned;
    }

    public void setPlanned(Date planned) {
        this.planned = planned;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
