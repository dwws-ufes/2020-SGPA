package br.ufes.informatica.doeLivros.book.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.informatica.doeLivros.book.domain.Book;
import br.ufes.informatica.doeLivros.people.domain.Person;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface BookRegistrationService extends CrudService<Book> {

	public List<Book> getBookList(Person donatedBy);

}