package com.dh.vestidobank.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dh.vestidobank.model.entity.Usuario;
import com.dh.vestidobank.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	private UsuarioRepository usuarioRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = this.usuarioRepository.findByUsername(username)
		 .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + "não encontrado"));;
		
		
		return usuario;
	}

}
