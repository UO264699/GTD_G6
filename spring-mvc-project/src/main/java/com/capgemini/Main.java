package com.capgemini;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.capgemini.services.UsersService;
public class Main {
	

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SQLException {
		UsersService usersService = new UsersService();
		
		usersService.getUsers().forEach((p) -> System.out.println(p.getEmail()));

	}

}
