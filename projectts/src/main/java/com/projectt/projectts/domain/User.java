package ebaza.codejava.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(columnNames = { "USERNAME" }),
		@UniqueConstraint(columnNames = { "EMAIL" }) })
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class User extends DomainDTO {

	@NotEmpty(message = "{Username.notNull}")
	@Column(name = "username", nullable = false)
	private String username;

	@NotEmpty(message = "{Password.notNull}")
	@Column(name = "password", nullable = false)
	private String password;

	@NotEmpty(message = "{Email.notNull}")
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "ENABLED", nullable = false)
	private boolean enabled = Boolean.TRUE;

	@NotEmpty(message = "{name.notNull}")
	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "IMAGE_URL")
	private String imageUrl;

	@NotNull(message = "{provider.notNull}")
	@Column(name = "PROVIDER", nullable = false)
	@Enumerated(EnumType.STRING)
	private EProvider provider;

	@NotNull(message = "{category.notNull}")
	@Column(name = "CATEGORY", nullable = false)
	@Enumerated(EnumType.STRING)
	private ECategory category;

}
