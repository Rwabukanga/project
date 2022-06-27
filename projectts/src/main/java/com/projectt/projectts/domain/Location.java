package com.projectt.projectts.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LOCATIONS")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({ "level","parentId" })
public class Location extends DomainDTO {

	@NotEmpty(message = "{name.notNull}")
	@Column(name = "NAME")
	private String name;

	@NotEmpty(message = "{code.notNull}")
	@Column(name = "CODE")
	private String code;

	@ManyToOne
	@JoinColumn(name = "PARENT_ID", nullable = true)
	private Location parent;

	// @NotEmpty(message= "{level.notNull}")
	@Column(name = "level")
	private int level;

	@Transient
	private UUID parentIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Location getParent() {
		return parent;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public UUID getParentIds() {
		return parentIds;
	}

	public void setParentIds(UUID parentIds) {
		this.parentIds = parentIds;
	}
	
	

}
