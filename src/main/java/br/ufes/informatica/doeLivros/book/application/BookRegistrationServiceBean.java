package br.ufes.informatica.doeLivros.book.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.doeLivros.book.persistence.BookDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class BookRegistrationServiceBean implements BookRegistrationService {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;
	
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private BookDAO bookDAO;
	
	
	

	
	
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public void insertBook() {
		// FIXME: auto-generated method stub
		return;
	}
	
}