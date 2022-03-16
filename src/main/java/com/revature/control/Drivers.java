package com.revature.control;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.models.Items;

import io.javalin.Javalin;

public class Drivers {

	private static List<Items> items = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(8081);
		

		
		

	
	}
}
