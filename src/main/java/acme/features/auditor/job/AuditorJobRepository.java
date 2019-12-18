
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j")
	Collection<Job> findAllJobs();

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findAuditorById(int id);

	@Query("select ar from AuditRecord ar where ar.job.id = ?1")
	AuditRecord findAuditRecordByJob(int jobId);

	//	@Query("select ar.job from AuditRecord ar where ar.auditor.id = ?1")
	//	Collection<Job> findJobsByAuditMine(int auditorId);

	@Query("select  j from Job j where exists(select ar from AuditRecord ar where ar.job.id = j.id and ar.auditor.id = ?1)")
	Collection<Job> findJobsByAuditMine(int auditorId);

	@Query("select  j from Job j where exists(select distinct ar from AuditRecord ar where ar.job.id = j.id and ar.auditor.id = ?1)")
	Collection<Job> findJobsByAuditNotMine(int auditorId);

	//	@Query("select distinct ar.job from AuditRecord ar where ar.auditor.id=?1")
	//	Collection<Job> findJobsByAuditNotMine(int auditorId);

}
