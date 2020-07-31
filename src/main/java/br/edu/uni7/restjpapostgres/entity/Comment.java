package br.edu.uni7.restjpapostgres.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String text;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar timestamp;
	
	@ManyToOne
	@JoinColumn(name = "serie_id", referencedColumnName = "id")
	private Serie serie;
	
	public Comment() {
		this(null, "");
	}

	public Comment(Long id, String text) {
		this.id = id;
		this.text = text;
		this.timestamp = new GregorianCalendar();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}
	
	public Serie getSerie() {
		return serie;
	}
	
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
}
