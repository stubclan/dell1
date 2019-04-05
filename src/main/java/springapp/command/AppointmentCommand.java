package springapp.command;

import java.sql.Timestamp;
import java.time.Duration;

import springapp.domain.Appointment;
import springapp.domain.Reason;

/**
 * This command class is used to pass information back and force between the client and the server
 * 
 */
public class AppointmentCommand {
	
	private Integer id;
	private Integer petId;
	private Integer clientId;
	private Reason reason;
	private Timestamp time;
	private int hour;
	private int minute;
	private String comments;
	
	/**
	 * Creates a command object that has the initial values the same as the appointment passed in
	 * @param appointment the appointment to initialize the command object with
	 */
	public AppointmentCommand(Appointment appointment) {
		if(appointment != null) {
			id = appointment.getId();
			this.petId = appointment.getPetId();
			this.clientId = appointment.getClientId();
			this.reason = appointment.getReason();
			this.time = appointment.getTime();
			this.hour = (int)appointment.getDuration().toHours();
			this.minute = (int)appointment.getDuration().toMinutes();
			this.comments = appointment.getComments();
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}
	
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
