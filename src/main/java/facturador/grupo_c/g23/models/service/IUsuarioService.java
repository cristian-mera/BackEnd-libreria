package facturador.grupo_c.g23.models.service;

import facturador.grupo_c.g23.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

}
