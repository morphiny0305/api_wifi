<%@ page import="dao.History_DAO" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.History_DTO" %>
<%@ page import="dao.Wifi_DAO" %>
<%@ page import="dto.Wifi_DTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

<html>
<head>
	<title>와이파이 정보 구하기</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<%@ include file="header.jsp"%>
<div>
	<%
		History_DAO service = new History_DAO();
		List<History_DTO> historyList = service.searchHistoryList();
		String strID = request.getParameter("id");
		if (strID != null) {
			service.deleteHistoryList(strID);
		}
	%>
	<table>
		<thead>
		<tr>
			<th>ID</th>
			<th>x좌표</th>
			<th>y좌표</th>
			<th>조회일자</th>
			<th>비고</th>
		</tr>
		</thead>
		<tbody>
		<% if (historyList.isEmpty()) {%>
		<tr>
			<td class="messages" colspan="5">위치 정보를 조회하신 이력이 없습니다.</td>
		</tr>
		<% } else { %>
		<% for (History_DTO historyDTO : historyList) { %>
		<tr>
			<td><%=historyDTO.getId()%></td>
			<td><%=historyDTO.getLat()%></td>
			<td><%=historyDTO.getLnt()%></td>
			<td><%=historyDTO.getSearchDttm()%></td>
			<td><button onclick="deleteHistory(<%=historyDTO.getId()%>)">삭제</button></td>
		</tr>
		<% }} %>
		</tbody>
	</table>
</div>
<script>
	function deleteHistory(ID) {
		if (confirm("데이터를 삭제하시겠습니까?")) {
			$.ajax({
				url: "http://localhost:8080/demo/history.jsp",
				data: {id : ID},
				success: function () {
					location.reload();
				},
				error: function (request, status, error) {
					alert("code: " + request.status + "\n"+ "message: " + request.responseText + "\n" + "error: " + error);
				}
			})
		}
	}
</script>
</body>
</html>