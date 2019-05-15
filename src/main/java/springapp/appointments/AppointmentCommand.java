package springapp.appointments;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import springapp.appointments.Appointment;
import springapp.appointments.Reason;
import springapp.service.ClientService;

/**
 * This command class is used to pass information back and force between the client and the server
 * 
 */
public class AppointmentCommand {
	
	private Integer id;
	private Integer petId;
	private Integer clientId;
	private Reason reason;
	private Date dateTime;
	private String duration;
	private String comments;
	
	private String clientName;
	private String petName;
	

	
	

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
			this.dateTime = appointment.getTime();
			this.duration = appointment.getDuration().toString();
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
	
	public Date getDateTime() {
				
		return dateTime;
		
	}
	
	public String getDateTimeString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm aaa");
		return formatter.format(dateTime);
		
		
	}


	public void setDateTime(Date time) {
		this.dateTime = time;
	}

	public String getDuration() {
		int hour = Integer.parseInt(duration) / 60;
		int minute = Integer.parseInt(duration) % 60;

		
		String durationString = hour + "hrs " + minute + "mins.";
		return durationString;
	}
	
	public Integer getDurationInt() {
		return Integer.parseInt(duration);
	}

	public void setDuration(Integer duration) {
		this.duration = duration.toString();
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getClientName() {
		return this.clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getPetName() {
		return this.petName;
	}
	
	public void setPetName(String petName) {
		this.petName = petName;
	}
	
}
