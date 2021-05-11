package br.ufes.informatica.doeLivros.libraries.application;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import br.ufes.informatica.doeLivros.libraries.domain.Library;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class PublicLibrariesSearchServiceBean implements PublicLibrariesSearchService {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	
	@Override
	public List<Library> callSearchAPI() throws ClientProtocolException {
			
		List<Library> lista = new ArrayList<Library>();
		Library library = null;

		javax.ws.rs.client.Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://bibliotecas.cultura.gov.br/api/space/find?@select=id,name,location");
        
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header("Authorization", "Basic ");
        Response response = invocationBuilder.get();

        String output = response.readEntity(String.class);
        JSONArray dataArray = new JSONArray(output);
               		
        for (int i = 0; i < dataArray.length(); i++) {
        	JSONObject everyJb = dataArray.getJSONObject(i);
        	library = new Library();
        	
        	library.setName(everyJb.getString("name"));
        	
        	lista.add(i, library);
        }
        
	    return lista;
	}	
}