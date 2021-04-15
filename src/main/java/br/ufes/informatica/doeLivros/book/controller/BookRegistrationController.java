package br.ufes.informatica.doeLivros.book.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PrimefacesLazyEntityDataModel;
import br.ufes.informatica.doeLivros.book.application.BookRegistrationService;
import br.ufes.informatica.doeLivros.book.domain.Book;
import br.ufes.informatica.doeLivros.core.application.RegisterService;
import br.ufes.informatica.doeLivros.core.domain.User;
import br.ufes.informatica.doeLivros.core.application.AuthenticateService;
import br.ufes.informatica.doeLivros.people.domain.Person;

/** TODO: generated by FrameWeb. Should be documented. */
@Named @SessionScoped 
public class BookRegistrationController extends CrudController<Book> {
	
	private static final long serialVersionUID = 1L;
	
	private Person person;
	
	@EJB
	private BookRegistrationService bookRegistrationService;
	
	@EJB
	private RegisterService userRegisterService;
	
	@EJB
	private AuthenticateService authenticateService;
	
	/** Primefaces lazy data model for use with a lazy p:dataTable component. */
	protected LazyDataModel<Book> lazyListOfBooks;

	@Override
	protected CrudService<Book> getCrudService() {
		return bookRegistrationService;
	}

	@Override
	protected void initFilters() { }
	
	@Override
	protected void prepEntity() {
	
		//Buscando o usuario da sessao
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				
		User usuarioLogado = (User) session.getAttribute("user");
		this.person = usuarioLogado;
		//setando o usuario logado como doador do livro
		selectedEntity.setDonatedBy(usuarioLogado);						
	}	
	
	/**
	 * List of books donated by the user logged in
	 * @return List of Books
	 */
	public List<Book> getBookList() {
		//Buscando o usuario da sessao
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				
		User usuarioLogado = (User) session.getAttribute("user");
		this.person = usuarioLogado;
		
		return bookRegistrationService.getBookList(this.person);
	}	
	
	/**
	 * Getter for lazyEntities.
	 * Created specifically for getting books from the user logged in the system
	 * @return Primefaces lazy data model for use with a lazy p:dataTable component.
	 */
	public LazyDataModel<Book> getlazyListOfBooks() {
		if (lazyListOfBooks == null) {
			count();
			lazyListOfBooks = new PrimefacesLazyEntityDataModel<Book>(bookRegistrationService.getDAO() ) {
				/** Serialization id. */
				private static final long serialVersionUID = 1117380513193004406L;

				/**
				 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder,
				 *      java.util.Map)
				 */
				@Override
				public List<Book> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
					firstEntityIndex = first;
					lastEntityIndex = first + pageSize;
					retrieveEntities();
					return entities;
				}
			};
			lazyListOfBooks.setRowCount((int) entityCount);
		}

		return lazyListOfBooks;
	}
	
	/**
	 * Retrieves a collection of entities, respecting the selected range. Makes the collection available to the view. This
	 * method is intended to be used internally.
	 */
	protected void retrieveEntities() {
		// Checks if the last entity index is over the number of entities and correct it.
		if (lastEntityIndex > entityCount) lastEntityIndex = (int) entityCount;		
		
		//getting the books donated by the current user
		entities = getBookList();
		
		// Adjusts the last entity index.
		lastEntityIndex = firstEntityIndex + entities.size();
	}
	
}