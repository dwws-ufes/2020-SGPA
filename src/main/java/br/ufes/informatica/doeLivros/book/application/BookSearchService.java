package br.ufes.informatica.doeLivros.book.application;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.ufes.informatica.doeLivros.book.domain.Book;

// Interface responsável pelo serviço de busca de livros. Implementada pela BookSearchServiceBean
@Local
public interface BookSearchService extends Serializable {

	// declaração do método de busca de livros. Irá ser chamado pelo controlador e
	// irá entrar em contato com o DAO
	// para recuperar a lista de livros de acordo com os parâmetros informados.
	public List<Book> getBookList(String title, String author, String editor, Integer publicationYear, String genre,
			Date availabilityDate, String donorName);

}