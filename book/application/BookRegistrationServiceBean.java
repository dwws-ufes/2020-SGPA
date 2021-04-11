package br.ufes.informatica.doeLivros.book.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.doeLivros.book.domain.Book;
import br.ufes.informatica.doeLivros.book.persistence.BookDAO;


@Stateless @PermitAll
public class BookRegistrationServiceBean extends CrudServiceBean<Book> implements BookRegistrationService {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private BookDAO bookDAO;

	@Override
	public BaseDAO<Book> getDAO() {
			return bookDAO;
	}

}