package com.projectt.projectts.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_Registrar")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Registrar extends DomainDTO {

//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name = "id")
//	private int id ;
	private String uuid = UUID.randomUUID().toString();

	@NotEmpty(message = "{FirstName.notNull}")
	@Column(name = "firstname", nullable = false)
	private String firstname;

	@NotEmpty(message = "{LastName.notNull}")
	@Column(name = "lastname", nullable = false)
	private String lastname;

	@NotEmpty(message = "{PhoneNumber.notNull}")
	@Column(name = "phonenumber", nullable = false)
	private String phonenumber;

	@NotEmpty(message = "{PhoneNumber.notNull}")
	@Column(name = "nid", nullable = false)
	private String nid;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotEmpty(message = "{Email.notNull}")
	@Column(name = "email", nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	private ECategory category;

	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	private Location location;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ECategory getCategory() {
		return category;
	}

	public void setCategory(ECategory category) {
		this.category = category;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
