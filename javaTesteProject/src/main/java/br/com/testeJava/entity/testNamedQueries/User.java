package br.com.testeJava.entity.testNamedQueries;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@NamedQuery(
    name = "User.findAll",
    query = "SELECT u FROM User u",
    hints = {
        @QueryHint(name = "org.hibernate.cacheable", value = "true")
    }
)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
	
	@Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

	protected Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}
    
    

}
