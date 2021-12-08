package facturador.grupo_c.g23.models.service;

import java.util.List;

import facturador.grupo_c.g23.models.entity.Libro;

public interface ILibroService {
	
	public List<Libro> findAll();
	
	public Libro findById(Long Id);
	
	public Libro save(Libro libro);
	
	public void delete (Libro libro);
	
	
		

}
