package springapp.controller;


import java.util.List;

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

	private Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	// injecting in an AppointmentService instance
    @Autowired
	AppointmentService appointmentService;

	@GetMapping
	public String getAppointments(Model model) {

		 List<Appointment> appointments = appointmentService.getAppointments();
			model.addAttribute("appointments", appointments);
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


		 // we could have used a different path for handling the create page but this approach uses the same
        // template for both creating a new client and editing and existing client
//	    if(id.equals("new")) {
//	        // create an empty command object to merge with the view template
//			model.addAttribute("command", new AppointmentCommand(null));
//		} else {
//	        // since we have a valid id, get the client object from the service
//			Appointment appointment = appointmentService.getAppointment(id);
//			// we create a client command that can be used when the browser sends the save object
//			model.addAttribute("command", new AppointmentCommand(appointment));
//
//			// we get the list of pets, and send those as is since we dont need a command to carry changes to the pets
//            // from this page
//			model.addAttribute("pet", appointmentService.getPet(appointment.getPetId()) );
//			model.addAttribute("client", appointmentService.getClient(appointment.getClientId()) );
//		//	model.addAttribute("client", appointmentService.getClient(appointment.getId()) );
//		}

		model.addAttribute("command", new AppointmentCommand(null));

		return "appointments/addAppointment";
	}
	 
	 @PreAuthorize("hasAuthority('GET_APPOINTMENT')")
	 @GetMapping("/edit/{id}")
		 public String getAppointment(@PathVariable("id") String id, Model model) {
		
	        // since we have a valid id, get the client object from the service
			Appointment appointment = appointmentService.getAppointment(id);
			// we create a client command that can be used when the browser sends the save object
			model.addAttribute("command", new AppointmentCommand(appointment));

			// we get the list of pets, and send those as is since we dont need a command to carry changes to the pets
            // from this page
			model.addAttribute("pet", appointmentService.getPet(appointment.getPetId()) );
			model.addAttribute("client", appointmentService.getClient(appointment.getClientId()) );
		//	model.addAttribute("client", appointmentService.getClient(appointment.getId()) );
		
		return "appointments/editAppointment";
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



}
