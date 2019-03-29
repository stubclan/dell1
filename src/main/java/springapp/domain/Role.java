package springapp.domain;

import static springapp.domain.Permission.LIST_CLIENTS;
import static springapp.domain.Permission.LIST_PETS;

import static springapp.domain.Permission.GET_SINGLE_CLIENT;
import static springapp.domain.Permission.SAVE_SINGLE_CLIENT;
import static springapp.domain.Permission.DELETE_SINGLE_CLIENT;



import java.util.ArrayList;
import java.util.List;


public enum Role {

	SUPER_ADMIN(Permission.values()),
	READ_ONLY(LIST_PETS, LIST_CLIENTS),
	CLIENT_ONLY(GET_SINGLE_CLIENT, SAVE_SINGLE_CLIENT, DELETE_SINGLE_CLIENT);
	
	private final Permission[] permissions;
	
	Role(Permission ... permissions) {
		this.permissions = permissions;
	}
	
	public List<Permission> getPermissions(){
		List<Permission> list = new ArrayList<>();
		for(Permission p: permissions) {
			list.add(p);
		}
		return list;
	}
	
}
