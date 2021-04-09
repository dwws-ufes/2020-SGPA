package br.ufes.informatica.doeLivros.book.persistence;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.informatica.doeLivros.book.domain.Book;
import br.ufes.informatica.doeLivros.people.domain.Person;

// Implementação do DAO de livros do software doeLivros
@Stateless
public class BookDAOJPA extends BaseJPADAO<Book> implements BookDAO {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	// gerenciador de entidades injetado
	@PersistenceContext
	private EntityManager entityManager;

	//getter para o entityManager
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	// Um dos métodos do DAO. recupera livro pelo título informado
	@Override
	public List<Book> getBookByTitle(String title) {
		// FIXME: auto-generated method stub
		return null;
	}

	// Um dos métodos do DAO. recupera livro pelo autor informado
	@Override
	public List<Book> getBookByAuthor(String author) {
		// FIXME: auto-generated method stub
		return null;
	}

	// Um dos métodos do DAO. recupera todos os livros do sistema.
	@Override
	public List<Book> getBookList() {
		// FIXME: auto-generated method stub
		return null;
	}

	// Um dos métodos do DAO. recupera livro pela combinação dos parâmetros fornecidos
	@Override
	public List<Book> getBookListWithParams(String author, String donorName, String genre, String title,
			Date availabilityDate, String editor, Integer publicationYear) {
		
		// Pra usar a Criteria API, é necessário usar os seguintes comandos para tratar de objetos do tipo Book
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);
				
		// Nesse bloco é contado quantos predicados a query terá. Isso depende do número de campos não nulos recebidos 
		// pelo método, ou seja, o número de atributos preenchidoa na página. (Os atributos vem sendo passados desde o 
		// controlador, que recebe eles da página).
		int count = 0;
		count = (author!=null) ? count+1 : count;
		count = (donorName!=null) ? count+1 : count;
		count = (genre!=null) ? count+1 : count;
		count = (title!=null) ? count+1 : count;
		count = (availabilityDate!=null) ? count+1 : count;
		count = (editor!=null) ? count+1 : count;
		count = (publicationYear!=null) ? count+1 : count;
		
		// Daí, descobertos a quantidade de predicados, vamos começar a construí-los
		Predicate[] predicates = new Predicate[count];

		
		// Como a ordem não importa, iremos preencher de acordo com que eles vão aparecendo. 
		// No caso do donorName, precisamos fazer um join com a tabela de doação de livros, para saber o nome do doados do livro.
		// A tabela Book só tem acesso ao id do doador, e não ao seu nome. Portanto é necessário fazer um join referente a relação
		// "donatedBy", para que uma tabela seja criada com os parâmetros do livro e o nome do donor. Após essa tabela ser criada, 
		// o jpa irá buscar aquelas linhas cuja concatenação do nome + sobrenome contém a substring "donorName".
		// a consulta é mais ou menos a seguinte: nome + sobrenome "like" %donorName%.
		// Os demais predicados comparam se o campo é exatamente igual ao informado pela página.
		// como se fosse um WHERE campo='campo'.
		if (author != null) 
			predicates[--count] = cb.equal(root.get("author"), author); 
		if (donorName!=null) {
			Join<Book, Person> joinBookPerson = root.join("donatedBy");
			predicates[--count] = cb.like(cb.concat(joinBookPerson.get("firstName"), joinBookPerson.get("lastName")),String.format("%c%s%c",'%',donorName,'%'));
		} 
		if (genre!=null)
			predicates[--count] = cb.equal(root.get("genre"), genre); 
		if (title!=null)
			predicates[--count] = cb.equal(root.get("title"), title); 
		if (availabilityDate!=null)
			predicates[--count] = cb.equal(root.get("availabilityDate"), availabilityDate); 
		if (editor!=null)
			predicates[--count] = cb.equal(root.get("editor"), editor); 
		if (publicationYear!=null)
			predicates[--count] = cb.equal(root.get("publicationYear"), publicationYear); 

		// Após isso, faz a query de fato, com todos os predicados definidos acima, se existirem.
		cq.select(root).where(predicates);
		Query query = entityManager.createQuery(cq); 
		
		// E finalmente recuperamos a lista de resultados da query acima. Essa função fica com um warning de tipo unchecado
		// Em vários locais da internet é sugerido suprimir esse warning, como é feito abaixo. 
		// Foram encontradas soluções sugeridas, mas que não removiam o warning completamente, então foi optado por suprimir o warning.
		@SuppressWarnings("unchecked")
		List<Book> results = query.getResultList(); // Isso dá warning
		
		//retornando a lista recuperada acima
		return results;
	}

}