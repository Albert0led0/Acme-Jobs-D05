
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findAuditorById(int id);

	@Query("select ar from AuditRecord ar where ar.job.id=?1")
	Collection<AuditRecord> findAuditByJobId(int jobId);

	@Query("select ar from AuditRecord ar where ar.id = ?1")
	AuditRecord findOneAuditRecordById(int id);

}
