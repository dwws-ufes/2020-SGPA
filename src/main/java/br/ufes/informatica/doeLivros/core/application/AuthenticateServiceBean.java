package br.ufes.informatica.doeLivros.core.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.doeLivros.core.persistence.UserDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class AuthenticateServiceBean implements AuthenticateService {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;
	
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private UserDAO userDAO;
	
	
	

	
	
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public void authenticate(String email, String password) {
		// FIXME: auto-generated method stub
		return;
	}
	
}