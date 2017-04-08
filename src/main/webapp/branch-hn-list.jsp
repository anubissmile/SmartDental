<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed">
	<thead>
		<tr>
			<th>สาขา</th>
			<th>เลขที่ hn</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="patBranchHnList" var="bhn">
		<tr>
			<td><s:property value="#bhn.branchId" /></td>
			<td><s:property value="#bhn.branchHn" /></td>
		</tr>
	</s:iterator>
	</tbody>
</table>