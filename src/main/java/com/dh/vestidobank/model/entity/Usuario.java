package com.dh.vestidobank.model.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Getter
	@Setter
	private Long id;
	
	@Column(length=60, nullable = false, unique = true)
	@Getter
	@Setter
	private String username;
	
	@Column(length=100, nullable = false)
	@Getter
	@Setter
	private String senha;
	
	
	private int role;
	
	@Builder
	public Usuario(Long id, String username, String senha, RoleEnum role) {
		this.id = id;
		this.username = username;
		this.senha =senha;
		
		this.role = role.getId();
	}
	
	
	public RoleEnum getRole() {
		return RoleEnum.fromId(this.role);
	}
	
	public void setRole(RoleEnum role) {
		this.role = role.getId();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> roles = new HashSet<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(this.getRole().getRoleName()));
		
		return roles;
	}


	@Override
	public String getPassword() {
		return this.senha;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}

}
