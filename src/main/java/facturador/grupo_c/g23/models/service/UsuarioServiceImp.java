package facturador.grupo_c.g23.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import facturador.grupo_c.g23.models.dao.IUsuarioDao;
import facturador.grupo_c.g23.models.entity.Usuario;


@Service
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService {

	
	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImp.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
		
		
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario=usuarioDao.findByUsername(username);
		
		if (usuario == null) {
			logger.error("error en el login; el usuario "+username+" no existe");
			throw new UsernameNotFoundException("error en el login; el usuario "+username+" no existe");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role-> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority->logger.info("Role: "+authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(),usuario.getPassword(),usuario.getEnabled(),true,true,true,authorities);
		
		
	
	}

}
