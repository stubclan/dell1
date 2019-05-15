package springapp.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority{
	LIST_PETS,
	DELETE_PET,
	SAVE_PET,
	GET_PET,
	
	LIST_CLIENTS,
	DELETE_CLIENT,
	SAVE_CLIENT,
	GET_CLIENT,
	
	DELETE_SINGLE_CLIENT, 
	GET_SINGLE_CLIENT, 
	SAVE_SINGLE_CLIENT,
	
	LIST_APPOINTMENTS,
	DELETE_APPOINTMENT,
	SAVE_APPOINTMENT,
	GET_APPOINTMENT;

	@Override
	public String getAuthority() {
		return toString();
	}
	
}
