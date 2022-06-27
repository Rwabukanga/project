package com.projectt.projectts.innerdomain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.projectt.projectts.domain.ECategory;
import com.projectt.projectts.domain.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class innerRegistrar {

	
	private String firstname;
	private String lastname;
	private String phonenumber;
	private String nid;
	@Enumerated(EnumType.STRING)
	private Gender gender;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private ECategory category;
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
	public ECategory getCategory() {
		return category;
	}
	public void setCategory(ECategory category) {
		this.category = category;
	}
    
    
    
}
