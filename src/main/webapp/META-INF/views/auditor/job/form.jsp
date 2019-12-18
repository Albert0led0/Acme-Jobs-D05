
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="auditor.job.form.label.referenceNumber" path="referenceNumber"/>
	<acme:form-textbox code="auditor.job.form.label.title" path="title"/>
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="auditor.job.form.label.salary" path="salary"/>
	<acme:form-textbox code="auditor.job.form.label.link" path="link"/>
	<acme:form-textarea code="auditor.job.form.label.description" path="description"/>
	
	<acme:form-return code="auditor.job.form.button.audit-record" action="/auditor/audit-record/list?id=${id}"/>
	<acme:form-return code="auditor.job.form.button.return"/>
</acme:form>