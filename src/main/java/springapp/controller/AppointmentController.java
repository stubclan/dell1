package springapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springapp.command.AppointmentCommand;
import springapp.command.ClientCommand;
import springapp.command.PetCommand;
import springapp.domain.Appointment;
import springapp.domain.Client;
import springapp.domain.Pet;
import springapp.service.AppointmentService;
import springapp.service.PetService;


@Controller
@RequestMapping("/appointments")
public class AppointmentController {
	
	private Logger logger = LoggerFactory.getLogger(PetController.class);

	// injecting in an AppointmentService instance
    @Autowired
	AppointmentService appointmentService;
	
	@GetMapping
	public String get() {
        return "appointments/listAppointments";
   }

	 /**
     * Generates the model for rendering the specific appointment page
     * @param model the model to populate for merging with the view
     * @return the client edit page template
     */
	 @PreAuthorize("hasAuthority('SAVE_APPOINTMENT')")
	 @GetMapping("/new")
	 public String getClient(String id, Model model) {
	
		model.addAttribute("command", new AppointmentCommand(null));	
		
		return "appointments/addAppointment";
	}
	
    /**
     * Create a new appointment
     * @param command the command to get the appointment info from
     * @param redirectAttributes used to pass attributes to the get page after saving a pet
     * @return the view template to use once the save is successful
     */
	@PreAuthorize("hasAuthority('SAVE_APPOINTMENT')")
	@PostMapping
	 public String saveAppointment(AppointmentCommand command, RedirectAttributes redirectAttributes) {

        // we pass in the appointment command to the service to update or create a new appointment
        // Appointment appointment = appointmentService.saveAppointment(command);
		appointmentService.saveAppointment(command);

        redirectAttributes.addAttribute("saved", true);
        //TODO: redirectAttributes.addAttribute("clientId", appointment.getAppointmentId());
        return "redirect:/appointments/";    //TODO: +appointment.getId();

    }
	
	
	
}
