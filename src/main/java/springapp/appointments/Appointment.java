package springapp.appointments;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import springapp.appointments.AppointmentDao;
import springapp.service.ClientService;

public class Appointment {
	
	private final Integer id;
	private final Integer petId;
	private final Integer clientId;
	private Reason reason;
	private Date dateTime;
	private Integer duration;
	private String comments;

	public Appointment(Integer myId, Integer myPetId, Integer myClientId, Reason myReason, Date dateTime, Integer myDuration, String myComments){
		
		this.id = myId;
		this.petId = myPetId;
		this.clientId = myClientId;		
		this.reason = myReason;
		this.dateTime = dateTime;
		this.duration = myDuration;
		this.comments = myComments;
	}
	
	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason apptReason) {
		this.reason = apptReason;
	}

	public Date getTime() {
		return dateTime;
	}

	public void setTime(Timestamp time) {
		this.dateTime = time;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setDuration(Integer duration) {
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

	public Integer getDuration() {
		// TODO Auto-generated method stub
		return duration;
	}	
	
	
}