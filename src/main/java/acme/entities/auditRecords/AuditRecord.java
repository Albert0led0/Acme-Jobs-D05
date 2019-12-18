
package acme.entities.auditRecords;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecord extends DomainEntity {

	private final static long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@NotBlank
	private String				body;

	@NotBlank
	@Pattern(regexp = "^(draft|published)$")
	private String				status;


	@Transient
	public boolean isPublic() {
		return this.status.equals("published");
	}


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Auditor	auditor;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job		job;

}
