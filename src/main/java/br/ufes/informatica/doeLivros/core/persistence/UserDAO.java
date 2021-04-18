package br.ufes.informatica.doeLivros.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.informatica.doeLivros.core.domain.Role;
import br.ufes.informatica.doeLivros.core.domain.User;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface UserDAO extends BaseDAO<User> {

	/** TODO: generated by FrameWeb. Should be documented. */
	public User getUserByEmail(String userEmail)
			throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;

	/** TODO: generated by FrameWeb. Should be documented. */
	public List<User> getUserList();

	/** TODO: generated by FrameWeb. Should be documented. */
	public List<User> getUserByRole(Role role);

	public User merge(User user);

}