<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagos</title>
</head>
<body>
<form action="/pagos" method="post" >
 <table style="margin: 0 auto;">
  <tr>
    <th>Realizar pagos:</th>
  </tr>
  <tr>
    <td>
     <textarea name="pagos" cols="100" rows="30"></textarea><br>
    </td>
  </tr>
  <tr>
   <td>
    <input type="submit" value="enviar">
    ${mensaje}
   </td>
  </tr>
</table> 
</form>

<form action="/consulta" method="get" >
 <table style="margin: 0 auto;">
  <tr>
    <th>Consultar pagos:</th>
  </tr>
  <tr>
    <td>
    <input type="submit" value="consultar">
   </td>
  </tr>
  <tr>
   <td>
	 <textarea name="consultar" cols="100" rows="30">${respuesta}</textarea><br>
    </td>
  </tr>
</table> 
</form>
	
</body>
</html>