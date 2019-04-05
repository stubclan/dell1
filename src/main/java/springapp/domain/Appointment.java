package springapp.domain;
import java.sql.Timestamp;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;

import springapp.dao.AppointmentDao;
import springapp.service.ClientService;

public class Appointment {
	
	private final Integer id;
	private final Integer petId;
	private final Integer clientId;
	private Reason reason;
	private Timestamp time;
	private Duration duration;
	private String comments;
	
	public Appointment(Integer myId, Integer myPetId, Integer myClientId, Reason myReason, Timestamp myApptTime, Duration myDuration, String myComments){
		
		this.id = myId;
		this.petId = myPetId;
		this.clientId = myClientId;		
		this.reason = myReason;
		this.time = new Timestamp(System.currentTimeMillis());
		this.duration = myDuration;
		this.comments = myComments;
	}
	
	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason apptReason) {
		this.reason = apptReason;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPetId() {
		return petId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public Duration getDuration() {
		// TODO Auto-generated method stub
		return duration;
	}	
	
	
}