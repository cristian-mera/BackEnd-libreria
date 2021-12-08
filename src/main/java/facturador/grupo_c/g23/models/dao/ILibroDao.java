package facturador.grupo_c.g23.models.dao;

import org.springframework.data.repository.CrudRepository;

import facturador.grupo_c.g23.models.entity.Libro;

public interface ILibroDao extends CrudRepository <Libro, Long> {

}
