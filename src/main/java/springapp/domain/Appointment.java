package springapp.domain;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointment {
	private final Integer id;
	private final Integer petId;
	private final Integer clientId;
	private Reason reason;
	private Timestamp time;
	private Integer duration;
	private String comments;
	
	public Appointment(Integer myId, Integer myPetId, Integer myClientId, Reason myReason, Timestamp myApptTime, Integer myDuration, String myComments){
		
		this.id = myId;
		this.petId = myPetId;
		this.clientId = myClientId;		
		this.reason = myReason;
		this.time = myApptTime;
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

	public void setTime(Timestamp apptDateTime) {
		this.time = apptDateTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer apptDuration) {
		this.duration = apptDuration;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	
	
}
