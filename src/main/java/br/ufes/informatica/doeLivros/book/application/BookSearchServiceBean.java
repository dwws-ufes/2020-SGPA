package br.ufes.informatica.doeLivros.book.application;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.doeLivros.book.domain.Book;
import br.ufes.informatica.doeLivros.book.persistence.BookDAO;

// Implementação da interface de busca de livros. 
@Stateless
public class BookSearchServiceBean implements BookSearchService {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	// instância do DAO que será injetada para termos acesso ao DAO.
	@EJB
	private BookDAO bookDAO;

	// Método de busca de livros. Irá ser chamado pelo controlador, e irá solicitar
	// ao DAO a lista
	// de livros de acordo com os parâmetros informados. A lista será retornada para
	// o controlador.
	@Override
	public List<Book> getBookList(String title, String author, String editor, Integer publicationYear, String genre,
			Date availabilityDate, String donorName) {
		return this.bookDAO.getBookListWithParams(author, donorName, genre, title, availabilityDate, editor,
				publicationYear);
	}

}