package br.ufes.informatica.doeLivros.book.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.informatica.doeLivros.people.domain.Person;

/** TODO: generated by FrameWeb. Should be documented. */
@Entity
public class Book extends PersistentObjectSupport implements Comparable<Book> {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull
	@Size(max = 300)
	private String title;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull
	@Size(max = 200)
	private String author;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@Size(max = 100)
	private String genre;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@Min(1)
	@Max(9999)
	@Column(name = "publicationYear")
	private Integer publicationYear;

	/** TODO: generated by FrameWeb. Should be documented. false */

	private Integer edition;

	/** TODO: generated by FrameWeb. Should be documented. false */

	private Integer volume;

	/** TODO: generated by FrameWeb. Should be documented. false */
	@NotNull
	@Size(max = 150)
	private String editor;

	/** TODO: generated by FrameWeb. Should be documented. true */
	@NotNull
	@FutureOrPresent
	@Temporal(TemporalType.DATE)
	private Date availabilityDate;

	/** TODO: generated by FrameWeb. Should be documented. */
	@ManyToOne
	private Person donatedBy;

	/** TODO: generated by FrameWeb. Should be documented. */
	@ManyToMany(mappedBy = "isInterestedOn")
	private Set<Person> interestedPersons;

	/** Getter for title. */
	public String getTitle() {
		return title;
	}

	/** Setter for title. */
	public void setTitle(String title) {
		this.title = title;
	}

	/** Getter for author. */
	public String getAuthor() {
		return author;
	}

	/** Setter for author. */
	public void setAuthor(String author) {
		this.author = author;
	}

	/** Getter for genre. */
	public String getGenre() {
		return genre;
	}

	/** Setter for genre. */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/** Getter for publicationYear. */
	public Integer getPublicationYear() {
		return publicationYear;
	}

	/** Setter for publicationYear. */
	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	/** Getter for edition. */
	public Integer getEdition() {
		return edition;
	}

	/** Setter for edition. */
	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	/** Getter for volume. */
	public Integer getVolume() {
		return volume;
	}

	/** Setter for volume. */
	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	/** Getter for editor. */
	public String getEditor() {
		return editor;
	}

	/** Setter for editor. */
	public void setEditor(String editor) {
		this.editor = editor;
	}

	/** Getter for availabilityDate. */
	public Date getAvailabilityDate() {
		return availabilityDate;
	}

	/** Setter for availabilityDate. */
	public void setAvailabilityDate(Date availabilityDate) {
		this.availabilityDate = availabilityDate;
	}

	/** Getter for donatedBy. */
	public Person getDonatedBy() {
		return donatedBy;
	}

	/** Setter for donatedBy. */
	public void setDonatedBy(Person donatedBy) {
		this.donatedBy = donatedBy;
	}

	/** Getter for interestedPersons. */
	public Set<Person> getInterestedPersons() {
		return interestedPersons;
	}

	/** Setter for interestedPersons. */
	public void setInterestedPersons(Set<Person> interestedPersons) {
		this.interestedPersons = interestedPersons;
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Book o) {
		return title.compareTo(o.title);// super.compareTo(o);
	}
}