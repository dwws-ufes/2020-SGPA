package br.ufes.informatica.doeLivros.core.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.informatica.doeLivros.core.domain.User;
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
	public User authenticate(String email, String password) {
		try {
			 User user = this.userDAO.getUserByEmail(email);
			 if (password.compareTo(user.getPassword()) == 0)
				 return user;
			 else
				 return null;
		} catch (PersistentObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MultiplePersistentObjectsFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}