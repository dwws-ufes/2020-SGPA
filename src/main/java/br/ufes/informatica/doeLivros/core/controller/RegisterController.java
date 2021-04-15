package br.ufes.informatica.doeLivros.core.controller;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.doeLivros.core.application.RegisterService;
import br.ufes.informatica.doeLivros.core.domain.User;
import br.ufes.informatica.doeLivros.core.exceptions.EmailInUseException;
import br.ufes.informatica.doeLivros.people.domain.Address;

/** TODO: generated by FrameWeb. Should be documented. */
@Model
public class RegisterController extends JSFController {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private RegisterService registerService;

	@Inject
	private AuthenticateController authenticateController;
	
	/** TODO: generated by FrameWeb. Should be documented. */
	private User user = new User();

	/** TODO: generated by FrameWeb. Should be documented. */
	private String confirmPassword;

	public void checkAdminCreated() {
		registerService.checkAdminCreated();
	}
	/** TODO: generated by FrameWeb. Should be documented. */
	public String register() {
		// FIXME: auto-generated method stub
		// user.setTarget(null);
		// Se um dos 4 campos for nulo, jogamos user.target pra nulo, e lá no serviço só salvamos se não for nulo.
		// Podemos retornar a página de cadastro pedindo pra preencher tudo ou apagar tudo. (um faces message, igual abaixo)
		if (confirmPassword.equals(user.getPassword())) {
			try {
				registerService.register(user);
			    } catch (EmailInUseException e) {
			      FacesContext.getCurrentInstance().addMessage(null,
			               new FacesMessage(FacesMessage.SEVERITY_ERROR, "Esse email já está registrado", ""));
			      return null;
			    }
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas digitadas não correspondem", ""));
			return null;
		}
			authenticateController.setUser(user);
			
		return "/index.xhtml?faces-redirect=true";
	}

	/** Getter for user. */
	public User getUser() {
		return user;
	}

	/** Setter for user. */
	public void setUser(User user) {
		this.user = user;
	}

	/** Getter for confirmPassword. */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/** Setter for confirmPassword. */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String updateUser() {
		User user = this.registerService.updateUser(this.authenticateController.getUser());
		this.authenticateController.setUser(user);
//		this.authenticateController.setUser(this.authenticateController.);
		return "updateUser.xhtml?faces-redirect=true";
	}
	
	public void checkAddress() {
		if (this.authenticateController.getUser().getTarget() == null)
			this.authenticateController.getUser().setTarget(new Address());
	}
	
	public String clearAddress() {
//		System.out.println("imhere");
		this.authenticateController.getUser().setTarget(null);
		return "updateUser.xhtml?faces-redirect=true";
	}
	
}