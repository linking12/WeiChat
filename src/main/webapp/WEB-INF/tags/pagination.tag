<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page"
	required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer"
	required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	int current = page.getNumber() + 1;
	int totalPages = page.getTotalPages();
	int begin = Math.max(1, current - paginationSize / 2);
	int end = Math.min(begin + paginationSize - 1, totalPages);
	request.setAttribute("current", current);
	request.setAttribute("begin", begin);
	request.setAttribute("end", end);
%>
<script type="text/javascript">
	function go(url) {
		var formaction = document.forms[0].action;
		if (formaction.indexOf("?") == -1) {
			formaction = formaction + "?" + url;
		} else {
			if (formaction.indexOf("page") == -1) {
				formaction = formaction + "&" + url;
			} else {
				var actionindex = formaction.indexOf("page");
				formaction = formaction.substring(0, actionindex - 1) + "&"
						+ url;
			}

		}
		document.forms[0].action = formaction;
		document.forms[0].submit();
	}
</script>

<div class="pagination">

	<ul>
		<%
			if (page.hasPreviousPage()) {
		%>
		<li><a href="javascript:go('page=1')">&lt;&lt;</a></li>
		<li><a href="javascript:go('page=${current-1}')">&lt;</a></li>
		<%
			} else {
		%>
		<li class="disabled"><a href="#">&lt;&lt;</a></li>
		<li class="disabled"><a href="#">&lt;</a></li>
		<%
			}
		%>

		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
				<c:when test="${i == current}">
					<li class="active"><a href="javascript:go('page=${i}')">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:go('page=${i}')">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<%
			if (page.hasNextPage()) {
		%>
		<li><a href="javascript:go('page=${current+1}')">&gt;</a></li>
		<li><a href="javascript:go('page=${page.totalPages}')">&gt;&gt;</a></li>
		<%
			} else {
		%>
		<li class="disabled"><a href="#">&gt;</a></li>
		<li class="disabled"><a href="#">&gt;&gt;</a></li>
		<%
			}
		%>

	</ul>
</div>

