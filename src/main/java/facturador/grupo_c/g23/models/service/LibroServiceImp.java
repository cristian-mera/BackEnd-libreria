package facturador.grupo_c.g23.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import facturador.grupo_c.g23.models.dao.ILibroDao;
import facturador.grupo_c.g23.models.entity.Libro;

@Service
public class LibroServiceImp implements ILibroService{

	@Autowired
	private ILibroDao libroDao;
	
	
	@Override 
	@Transactional(readOnly=true)
	public List<Libro> findAll() {
		
		return (List<Libro>) libroDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Libro findById(Long Id) {
		return libroDao.findById(Id).orElse(null);
	}

	@Override
	@Transactional
	public Libro save(Libro libro) {
		return libroDao.save(libro);		
	}

	@Override
	@Transactional
	public void delete(Libro libro) {
		libroDao.delete(libro);		
	}
}
