package facturador.grupo_c.g23.controllers;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import facturador.grupo_c.g23.models.entity.Libro;
import facturador.grupo_c.g23.models.service.ILibroService;

@CrossOrigin(origins= {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api")
public class LibroRestController {

	@Autowired
	private ILibroService libroService;
	
	@GetMapping("/libros")
	public List<Libro> index(){
		return libroService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/libro/{Id}")
	public Libro show(@PathVariable Long Id) {
		return libroService.findById(Id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/libros")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Libro libro, BindingResult result) {
		//libro.setCreateAt(Date);
		Libro libroNew=null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
			.stream()
			.map(err -> "El campo "+ err.getField()+" "+err.getDefaultMessage())
			.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
		libroNew = this.libroService.save(libro);
		}catch(DataAccessException e) {
			response.put("mesaje","Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje","Libro guardado con exito" );
		response.put("libro", libroNew);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/libros/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id){
		
		Map<String, Object> response = new HashMap<>();
		try {
			Libro libro = this.libroService.findById(id);
			this.libroService.delete(libro);
			
		}catch(DataAccessException e) {
			response.put("mesaje","Error al eliminar el Libro");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mesaje","Libro Eliminado ): ");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);

	}
	
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("libro/{Id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Libro libro, @PathVariable Long Id , BindingResult result) {
		Libro currentLibro = this.libroService.findById(Id);
		
		Libro libroUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
			.stream()
			.map(err -> "El campo "+ err.getField()+" "+err.getDefaultMessage())
			.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (currentLibro  == null) {
			response.put("mensaje", "No se puede editar el libro: ".concat(Id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		
		}
		try {
			currentLibro.setTitulo(libro.getTitulo());
			currentLibro.setISBN(libro.getISBN());
			currentLibro.setNumPaginas(libro.getNumPaginas());
			currentLibro.setEstado(libro.isEstado());
			this.libroService.save(currentLibro);
		}catch(DataAccessException e) {
			response.put("mesaje","Error al actualizar el Libro");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje","Libro actualizado con exito" );
		response.put("libro", libroUpdate);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

		
		
		
	}
}

