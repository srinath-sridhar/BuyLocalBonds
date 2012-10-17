<%@ include file="_header.jsp" %>
Logging out...

<script>

$.post("UserAuthentication", { logout : 1 }, function() {
	
	window.location.href = "index.jsp";

});

</script>

<%@ include file="_footer.jsp" %>