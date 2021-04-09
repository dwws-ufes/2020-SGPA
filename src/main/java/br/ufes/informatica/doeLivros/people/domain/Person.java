package br.ufes.informatica.doeLivros.people.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.informatica.doeLivros.book.domain.Book;

/** TODO: generated by FrameWeb. Should be documented. */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person extends PersistentObjectSupport implements Comparable<Person> {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull
	@Size(max = 100)
	private String firstName;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull
	@Size(max = 100)
	private String lastName;

	/** TODO: generated by FrameWeb. Should be documented. true */
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	/** TODO: generated by FrameWeb. Should be documented. true */
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	/** TODO: generated by FrameWeb. Should be documented. */
	@OneToOne
	private Address Target;

	/** TODO: generated by FrameWeb. Should be documented. */
	@ManyToMany
	private Set<Book> isInterestedOn;

	/** TODO: generated by FrameWeb. Should be documented. */
	@OneToMany(mappedBy = "donor")
	private Set<Book> donatedBooks;

	/** TODO: generated by FrameWeb. Should be documented. */
	@OneToMany(mappedBy = "donatedBy")
	private Set<Book> donatedBook;

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
	public Address getTarget() {
		return Target;
	}

	/** Setter for Target. */
	public void setTarget(Address Target) {
		this.Target = Target;
	}

	/** Getter for isInterestedOn. */
	public Set<Book> getIsInterestedOn() {
		return isInterestedOn;
	}

	/** Setter for isInterestedOn. */
	public void setIsInterestedOn(Set<Book> isInterestedOn) {
		this.isInterestedOn = isInterestedOn;
	}

	/** Getter for donatedBooks. */
	public Set<Book> getDonatedBooks() {
		return donatedBooks;
	}

	/** Setter for donatedBooks. */
	public void setDonatedBooks(Set<Book> donatedBooks) {
		this.donatedBooks = donatedBooks;
	}

	/** Getter for donatedBook. */
	public Set<Book> getDonatedBook() {
		return donatedBook;
	}

	/** Setter for donatedBook. */
	public void setDonatedBook(Set<Book> donatedBook) {
		this.donatedBook = donatedBook;
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Person o) {
		// FIXME: auto-generated method stub
		return super.compareTo(o);
	}
}