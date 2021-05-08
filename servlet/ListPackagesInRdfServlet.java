package br.ufes.informatica.doeLivros.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

import br.ufes.informatica.doeLivros.book.domain.Book;

@WebServlet(urlPatterns = { "/data/tourpackages" })
public class ListPackagesInRdfServlet extends HttpServlet {
/**
	 * 
	 */   
	private static final long serialVersionUID = 1L;
private static final DateFormat df = new SimpleDateFormat("yyyy-MMdd'T'HH:mm:ss");
	@EJB
	private br.ufes.informatica.doeLivros.book.persistence.BookDAO bookDAO;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/xml");
		List<Book> books = bookDAO.retrieveAll();
		Model model = ModelFactory.createDefaultModel();
		String myNS = "http://localhost:8080/doeLivros/book/list";
		String grNS = "http://purl.org/goodrelations/v1#";
		model.setNsPrefix("gr", grNS);
		Resource grOffering = ResourceFactory.createResource(grNS + "Offering");
		//Resource grPriceSpecification = ResourceFactory.createResource(grNS + "PriceSpecification");
		Property gravailabilityStarts = ResourceFactory.createProperty(grNS + "availabilityStarts");
		//Property gravailabilityEnds = ResourceFactory.createProperty(grNS + "availabilityEnds");
		//Property grhasPriceSpecification = ResourceFactory.createProperty(grNS + "hasPriceSpecification");
		//Property grhasCurrencyValue = ResourceFactory.createProperty(grNS + "hasCurrencyValue");
		for (Book pack : books) {
			model.createResource(myNS + pack.getId()).addProperty(RDF.type, grOffering)
					.addProperty(RDFS.label, pack.getTitle())
					.addLiteral(gravailabilityStarts,
							ResourceFactory.createTypedLiteral(df.format(pack.getAvailabilityDate()), XSDDatatype.XSDdateTime));
			/*
					.addLiteral(XSD.gYear,
							ResourceFactory.createTypedLiteral(df.format(pack.getPublicationYear()), XSDDatatype.XSDint)); */
		}
			
		
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");

		}
	}
}
