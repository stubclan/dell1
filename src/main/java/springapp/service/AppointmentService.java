package springapp.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.command.AppointmentCommand;
import springapp.command.PetCommand;
import springapp.dao.AppointmentDao;
import springapp.dao.PetDao;
import springapp.domain.Appointment;
import springapp.domain.Client;
import springapp.domain.Pet;


@Service
public class AppointmentService {

	@Autowired 
	AppointmentDao appointmentDao;
	
	//TODO: Get Appointments List

	//TODO: Delete Appointment

	//TODO: List Appointment
	
	public Appointment saveAppointment(AppointmentCommand command) {
		Appointment newAppointment = new Appointment(command.getId(), command.getPetId(), command.getClientId(), command.getReason(), command.getTime(), command.getDuration(), command.getComments());
		return appointmentDao.save(newAppointment);
	}
}
