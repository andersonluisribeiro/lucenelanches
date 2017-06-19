package br.com.entelgy.lucenelanches.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=75)
	private String name;
	
	@Column(length=100)
	private String address;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="request")
	private List<Snack> snacks = new ArrayList<Snack>();
	
	public Request() {
		super();
	}

	public Request(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	public Request(String name, String address, List<Snack> snacks) {
		super();
		this.name = name;
		this.address = address;
		this.snacks = snacks;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public List<Snack> getSnacks() {
		return snacks;
	}
	
	public Float getTotalPrice(){
		Float total = 0f;
		for( Snack snack : getSnacks() ){
			total += snack.getTotalPrice();
		}
		return total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
