package br.ufes.informatica.doeLivros.book.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.doeLivros.book.application.BookSearchService;
import br.ufes.informatica.doeLivros.book.domain.Book;

// Controlador responsável pela página de busca de livros do software doeLivros. 
// O FrameWeb gerou com a anotação @Model, e foi necessário substituir para @Named 
// e @SessionScoped para que a página consiga ter acesso o controlador.
@Named
@SessionScoped
public class BookSearchController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	// O atributo que será injetado para acessarmos o serviço correspondente
	// a busca de livros do software
	@EJB
	private BookSearchService bookSearchService;

	// A lista de livros que será preenchida pelo método de busca de livros e
	// apresentada na página correspondente
	private List<Book> bookList;

	// O parâmetro de busca título do livro. Pode ser nulo.
	private String title;

	// O parâmetro de busca editor do livro. Pode ser nulo.
	private String editor;

	// O parâmetro de busca ano de publicação do livro. Pode ser nulo.
	private Integer publicationYear;

	// O parâmetro de busca gênero do livro. Pode ser nulo.
	private String genre;

	// O parâmetro de busca donorName do livro. Pode ser nulo.
	private String donorName;

	// O parâmetro de busca availabilityDate do livro. Pode ser nulo.
	private Date availabilityDate;

	// O parâmetro de busca autor do livro. Pode ser nulo.
	private String author;

	// O método principal do controlador; ao ser chamado na página xhtml, o
	// controlador irá se comunicar
	// com o serviço correspondente de busca de livros, que por sua vez irá entrar
	// em contato com o
	// DAO, receberá a lista de livros correspondente do DAO, e retornará a lista de
	// livros para o controlador
	// Assim, a lista de livros é preenchida e o controlador retorna a página que o
	// software deve apresentar .
	// No caso, é a mesma página de origem.
	public String getBookListPage() {
		this.bookList = this.bookSearchService.getBookList(title, author, editor, publicationYear, genre,
				availabilityDate, donorName);
		return "index.xhtml?faces-redirect=true";
	}

	// Getters e setters. Apenas a lista de livro não tem setter, pois ela será
	// preenchida no método de busca de livors.
	// OS demais precisam de getter e setter para troca de informações com a página
	// web.

	/** Getter for bookList. */
	public List<Book> getBookList() {
		return bookList;
	}

	/** Getter for title. */
	public String getTitle() {
		return title;
	}

	/** Setter for title. */
	public void setTitle(String title) {
		this.title = title;
	}

	/** Getter for editor. */
	public String getEditor() {
		return editor;
	}

	/** Setter for editor. */
	public void setEditor(String editor) {
		this.editor = editor;
	}

	/** Getter for publicationYear. */
	public Integer getPublicationYear() {
		return publicationYear;
	}

	/** Setter for publicationYear. */
	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	/** Getter for genre. */
	public String getGenre() {
		return genre;
	}

	/** Setter for genre. */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/** Getter for donorName. */
	public String getDonorName() {
		return donorName;
	}

	/** Setter for donorName. */
	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	/** Getter for availabilityDate. */
	public Date getAvailabilityDate() {
		return availabilityDate;
	}

	/** Setter for availabilityDate. */
	public void setAvailabilityDate(Date availabilityDate) {
		this.availabilityDate = availabilityDate;
	}

	/** Getter for author. */
	public String getAuthor() {
		return author;
	}

	/** Setter for author. */
	public void setAuthor(String author) {
		this.author = author;
	}

	private int selectedId;

	public String getDescription(int id) {
		selectedId = id;
//		System.out.println(">>>>" + id);
		return "description.xhtml?faces-redirect=true";
	}


	public String getAbstract() {
		String bookName = bookList.get(selectedId).getTitle();
		String init = "Showing abstract for the book entitled: ";

		if (bookName != null && bookName.length() > 3) {
			String query = "PREFIX dbp: <http://dbpedia.org/property/>\n"
					+ "PREFIX dbo: <http://dbpedia.org/ontology/>\n"
					+ "PREFIX bibo: <http://purl.org/ontology/bibo/>\n"
					+ "PREFIX schema: <http://schema.org/>\n"
					+ "SELECT DISTINCT ?abstract ?name \n" 
					+ "WHERE {\n"
					+ "VALUES ?type {dbo:Book bibo:Book schema:Book dbp:Book}\n"
					+ "?book a ?type;\n"
					+ "dbp:name ?name;\n"
					+ "dbo:abstract ?abstract.\n"
					+ "FILTER (regex(?name,\"^" +
					bookName 
					+ ".*\", \"i\") && langMatches(lang(?abstract), \"EN\"))\n"
					+ "}";
			QueryExecution queryExecution =
					QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql",
							query);
			ResultSet results = queryExecution.execSelect();
			if (results.hasNext()) {
				QuerySolution querySolution = results.next();
				Literal abstractLiteral = querySolution.getLiteral("abstract");
				Literal nameLiteral = querySolution.getLiteral("name");
				return init + nameLiteral + "\n\n" + abstractLiteral.getValue();
			}	
			else
				return "Abstract not found";
		}
		return "Abstract not found";

	}

}