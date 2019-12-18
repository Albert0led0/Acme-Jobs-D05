
package acme.features.auditor.auditRecord;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditRecordListService implements AbstractListService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		Auditor res = this.repository.findAuditorById(request.getPrincipal().getActiveRoleId());

		return res != null;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");
		model.setAttribute("status", entity.getStatus());

	}

	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;

		Collection<AuditRecord> res;

		String jobId = request.getModel().getAttribute("id").toString();
		res = this.repository.findAuditByJobId(Integer.parseInt(jobId));

		return res.stream().filter(ar -> ar.getStatus().equals("published")).collect(Collectors.toList());
	}
}
