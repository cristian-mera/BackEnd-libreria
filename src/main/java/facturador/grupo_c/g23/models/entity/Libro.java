package facturador.grupo_c.g23.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="libros")
public class Libro implements Serializable{

//Atributos
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@NotEmpty(message="no puede estar vacio")
	
	private String titulo;
	
	@Column(name="isbn")
	private String ISBN;
	
	
	@Column(name="num_paginas")
	private Integer numPaginas;
	
	@Column(name="estado")
	private boolean estado = true;
	
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	//Encapsulamiento
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	public Integer getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(Integer numPaginas) {
		this.numPaginas = numPaginas;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
