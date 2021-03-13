package br.ufes.informatica.doeLivros.people.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.informatica.doeLivros.book.domain.Book;
import br.ufes.informatica.doeLivros.core.domain.User;

/** TODO: generated by FrameWeb. Should be documented. */
@Entity
public class Person extends PersistentObjectSupport implements Comparable<Person> {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;



	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull @Size(max = 100) 
	private String firstName;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull @Size(max = 100) 
	private String lastName;

	/** TODO: generated by FrameWeb. Should be documented. false */
	  
	private Date birthDate;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull  
	private Date registrationDate;




		
		/** TODO: generated by FrameWeb. Should be documented. */
		@OneToOne
		private Address addressTarget;
		
	

		
		/** TODO: generated by FrameWeb. Should be documented. */
		@ManyToMany
		private Set<Book> bookTarget;
		
	

		
		/** TODO: generated by FrameWeb. Should be documented. */
		@OneToMany(mappedBy="donatorSource")
		private Set<Book> Target;
		
	

		
		/** TODO: generated by FrameWeb. Should be documented. */
		@OneToOne(mappedBy="userTarget")
		private User userSource;
		
	




	/** Getter for firstName. */
	public String getFirstName() {
		return firstName;
	}
	
	/** Setter for firstName. */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** Getter for lastName. */
	public String getLastName() {
		return lastName;
	}
	
	/** Setter for lastName. */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** Getter for birthDate. */
	public Date getBirthDate() {
		return birthDate;
	}
	
	/** Setter for birthDate. */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/** Getter for registrationDate. */
	public Date getRegistrationDate() {
		return registrationDate;
	}
	
	/** Setter for registrationDate. */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}




		
		/** Getter for Target. */
		public Address getAdressTarget() {
			return addressTarget;
		}
		
		/** Setter for Target. */
		public void setAdressTarget(Address Target) {
			this.addressTarget = Target;
		}
		
	

		
		/** Getter for Target. */
		public Set<Book> getBookTarget() {
			return bookTarget;
		}
		
		/** Setter for Target. */
		public void setBookTarget(Set<Book> Target) {
			this.bookTarget = Target;
		}
		
	

		
		/** Getter for Target. */
		public Set<Book> getTarget() {
			return Target;
		}
		
		/** Setter for Target. */
		public void setTarget(Set<Book> Target) {
			this.Target = Target;
		}
		
	

		
		/** Getter for Source. */
		public User getSource() {
			return userSource;
		}
		
		/** Setter for Source. */
		public void setSource(User Source) {
			this.userSource = Source;
		}
		
	





	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Person o) {
		// FIXME: auto-generated method stub		
		return super.compareTo(o);
	}
}