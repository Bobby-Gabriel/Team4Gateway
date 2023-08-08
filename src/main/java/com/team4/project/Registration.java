package com.team4.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REGISTRATIONS")
public class Registration {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String event_id, customer_id, notes;
	private java.util.Date registration_date;

	public Registration() {
		
	}
	
	public Registration(String event_id, String customer_id, java.util.Date registration_date, String notes) {
		this.event_id = event_id;
		this.customer_id = customer_id;
		this.registration_date = registration_date;
		this.notes = notes;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}


	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	
	public String getEvent_id() {
		return event_id;
	}


	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getCustomer_id() {
		return customer_id;
	}

	public void setRegistration_date(java.util.Date registration_date) {
		this.registration_date = registration_date;
	}
	
	public java.util.Date getRegistration_date() {
		return registration_date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	
	
	
}
