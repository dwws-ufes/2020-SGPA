package br.ufes.informatica.doeLivros.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.informatica.doeLivros.core.domain.User;
import br.ufes.informatica.doeLivros.core.exceptions.EmailInUseException;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface RegisterService extends Serializable {

	/** TODO: generated by FrameWeb. Should be documented. */
	public void register(User user) throws EmailInUseException;

}