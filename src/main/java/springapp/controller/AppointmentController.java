package springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppointmentController {
	
	@GetMapping("/appointments")
	public String get() {
        return "appointments/listAppointments";
   }
}
