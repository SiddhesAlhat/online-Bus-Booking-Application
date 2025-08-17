package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Route 
{
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	  private String source;
	  private String destination;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Route() 
	{
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
	}
	  
	
	
	
}
