		package springapp.appointments;


import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springapp.appointments.AppointmentCommand;
import springapp.command.PetCommand;
import springapp.appointments.Appointment;
import springapp.domain.Client;
import springapp.domain.Pet;
import springapp.appointments.Reason;
import springapp.appointments.AppointmentService;
import springapp.service.ClientService;
import springapp.service.PetService;


@Controller
@RequestMapping("/appointments")
public class AppointmentController {

	private Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	// injecting in an AppointmentService instance
    @Autowired
	AppointmentService appointmentService;
    @Autowired
	ClientService clientService;
    @Autowired
	PetService petService;

	@GetMapping
	public String getAppointments(Model model) {

//		 List<Appointment> appointments = appointmentService.getAppointments();
			
			List<AppointmentCommand> appointmentCommands = new ArrayList<AppointmentCommand>();
			 if (appointmentService.getAppointments() != null) {
				 for (Appointment appointment: appointmentService.getAppointments()) {
		 			 
					 AppointmentCommand command = new AppointmentCommand(appointment);	
					 command.setPetName(petService.getPet(appointment.getPetId()).getName());

					 command.setClientName(clientService.getClient(appointment.getClientId()).getName());
					 
					 appointmentCommands.add(command);
				 }
				 
			 }
			 
				model.addAttribute("appointments", appointmentCommands);
				model.addAttribute("nullApptCommand", new AppointmentCommand(null));

			
        return "appointments/listAppointments";

   }

	 /**
     * Generates the model for rendering the specific appointment page
     * @param model the model to populate for merging with the view
     * @return the client edit page template
     */
	 @PreAuthorize("hasAuthority('SAVE_APPOINTMENT')")
	 @GetMapping("/new")
		 public String addAppointment(Model model) {
		 
		 	int[] hours = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			int[] minutes = {0, 15, 30, 45};		
			
			model.addAttribute("hours", hours);
			
			model.addAttribute("minutes", minutes);
		 
		 List<String> reasons = new ArrayList<String>();
		 
		 for (Reason reason: Reason.values()) {
			 reasons.add(reason.toString());
		 }
		 
		 
		 
		model.addAttribute("command", new AppointmentCommand(null));
//		
		model.addAttribute("reasons", reasons);
		
		//All this carpet just to get Client name displayed. How to make it concise?
		 
		 List<PetCommand> petCommands = new ArrayList<PetCommand>();
		 
		 for (Pet pet : appointmentService.getPets()) {
			 			 
			 PetCommand command = new PetCommand(pet);
			 Client client = clientService.getClient(command.getClientId());		 
			 //logger.error(client.name);
			 command.setClient(client);			 
			 
			 petCommands.add(command);
		 }
//			
		 if (!petCommands.isEmpty()) {
			 model.addAttribute("petCommands", petCommands);
		 }		
		
		return "appointments/addAppointment";
	}	 

	 @PreAuthorize("hasAuthority('GET_APPOINTMENT')")
	 @GetMapping("/edit/{id}")
		 public String getAppointment(@PathVariable("id") String id, Model model) throws ParseException {

	        // since we have a valid id, get the appointment object from the service
			Appointment appointment = appointmentService.getAppointment(id);
			
			//Code to add reasons enum to EDIT appointment
			List<String> reasons = new ArrayList<String>();
			 
			 for (Reason reason: Reason.values()) {
				 reasons.add(reason.toString());
			 }
					
			model.addAttribute("reasons", reasons);
			
			// we create a appointment command that can be used when the browser sends the save object
			model.addAttribute("command", new AppointmentCommand(appointment));
			model.addAttribute("petName", petService.getPet(appointment.getPetId()).getName());
			model.addAttribute("clientName", clientService.getClient(appointment.getClientId()).getName());
			
			
			
			model.addAttribute("date","asdd" );
			
			model.addAttribute("time",appointment.getTime() );

			

		return "appointments/editAppointment";
	}


    /**
     * Create a new appointment
     * @param command the command to get the appointment info from
     * @param redirectAttributes used to pass attributes to the get page after saving a pet
     * @return the view template to use once the save is successful
     * @throws ParseException 
     */
	@PreAuthorize("hasAuthority('SAVE_APPOINTMENT')")
	@PostMapping
	 public String saveAppointment(@RequestParam(value = "time") String time, @RequestParam(value = "date") String date, 
			 @RequestParam(value = "hour") int hour, @RequestParam(value = "minute") int minute,
			 AppointmentCommand command, RedirectAttributes redirectAttributes) throws ParseException {
		
		command.setDateTime(processTime(time, date));	
		command.setDuration(hour*60 + minute);
		
		appointmentService.saveAppointment(command);
        redirectAttributes.addAttribute("saved", true);
        //TODO: redirectAttributes.addAttribute("clientId", appointment.getAppointmentId());
        return "redirect:/appointments/";    //TODO: +appointment.getId();

    }

	@PreAuthorize("hasAuthority('DELETE_APPOINTMENT')")
	 @GetMapping("/delete/{id}")
	 public String deleteAppointment(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        // NOTE to handle exceptions, we would wrap the following code in a try/catch
        // and in the catch forward to a different page

        // send the id passed in to the client service
		logger.debug(id.toString() + " delete this item.");
        appointmentService.deleteAppointment(id);

        // add an attribute to the list page, so a nice message can be shown
        redirectAttributes.addFlashAttribute("deleted", true);

        // redirect to list clients path/page
        return "redirect:/appointments";
   }
	
	private Date processTime(String time, String date) {
		
		String timeDate = date + " " + time.toUpperCase();
		DateFormat outputFormat;
		
		/*if (time.indexOf('a') > -1) {
			outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:01", Locale.US);
		}
		else {
			outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:00", Locale.US);
		}*/
		
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aaa", Locale.US);
		

		Date dateMod = null;
		try {
			dateMod = inputFormat.parse(timeDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.error("OUTPUT " + dateMod.toString());

				
		
		// make sure the seconds are set before parsing
				
		return dateMod;
	}



}
