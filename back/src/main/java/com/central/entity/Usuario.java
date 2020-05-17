package com.central.entity;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.boot.SpringApplication;

import com.central.Application;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.net.ObjectWriter;


@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
            }),
            @UniqueConstraint(columnNames = {
                "email"
            })
    })
public class Usuario extends AuditModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	@NotBlank
	@Size(min = 1, max = 100)
	private String name;
	
	@Column(name = "username")
	@NotBlank
	@Size(min=3, max = 50)
	private String username;
	
	@Column(name = "password")
	@NotBlank
	@Size(min = 1, max = 100)
	private String password;
	
	@Column(name = "email")
	@NotBlank
	@Size(min = 1, max = 100)
	@Email
	private String email;
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy = "usuario")
    private Set<Erro> erros;
    
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
      joinColumns = @JoinColumn(name = "id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
		
	public Usuario() {
		
	}
	
	public Usuario(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getUsername() {
	    return username;
	}
	 
	public void setUsername(String username) {
	    this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	 public Set<Role> getRoles() {
	        return roles;
	    }
	 
	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }
//	    
//		public static void main(String[] args) throws JsonProcessingException {
//			
//		Usuario user1 = new Usuario("lar", "lari", "123", "oi@oi.com");
//			System.out.println(toJson(user1));
//		}
//		
//
//		private static String toJson(Usuario user1) throws JsonProcessingException {
//			com.fasterxml.jackson.databind.ObjectWriter ow =  new ObjectMapper().writer().withDefaultPrettyPrinter();
//			String json = ow.writeValueAsString(user1);
//			System.out.println(json);
//			return json;
//		}


}
