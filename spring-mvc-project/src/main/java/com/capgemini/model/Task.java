package com.capgemini.model;

import java.util.Date;

public class Task {

    public String tittle;
    public String comments;
    public Date created;
    public Date planned;
    public Date finished;
    public int user_id;
    public int category_id;

    public Task(String tittle, String comments, Date created, Date planned, Date finished, int user_id, int category_id) {
        this.tittle = tittle;
        this.comments = comments;
        this.created = created;
        this.planned = planned;
        this.finished = finished;
        this.user_id = user_id;
        this.category_id = category_id;
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

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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
}
