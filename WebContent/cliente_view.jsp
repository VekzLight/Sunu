l<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
    <meta   http-equiv="content-type"
            content="text/html";
            charset="windows-1252" />
          
    <meta   name="GENERATOR"
            content="SeaMonkey/2.40 [en] (Windows; 10; Intel(R) Core(TM) i7-4500U CPU @ 1.80GHz 2.40 GHz) [Composer]" />
    
    <meta   name="Author" 
            content="DNA System" />
    
    <title>Forma de Búsqueda de Cliente</title>
    
    <link   rel="stylesheet"
            href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <form   method="get"
            action="${ pageContext.request.contextPath }/cliente_view.jsp">
        
        <div class="step">Detalle del Cliente</div>
        
        <br>
        <br>
        
        <table border="0" width="100%">
            <tr class="form">
                <td align="center">
                    <div class="label">Nombre</div>
                </td>
                <td align="center">
                    <div class="label">Paterno</div>
                </td>
                <td align="center">
                    <div class="label">Materno</div>
                </td>
                <td align="center">
                    <div class="label">Edad</div>
                </td>
                <td align="center">
                    <div class="label">Sexo</div>
                </td>
            </tr>
            
            <c:forEach var="cliente" items="${ clientes }">
               <tr>
                    <td align="center">${ cliente.nombre }</td>
                    <td align="center">${ cliente.paterno }</td>
                    <td align="center">${ cliente.materno }</td>
                    <td align="center">${ cliente.edad }</td>
                    <td align="center">${ cliente.sexo }</td>
                    <td align="center">
               </tr>
            </c:forEach>
        </table>
        
        <br>
        
        <input type="button" value="  Regresar  "
            onclick="window.location='${ pageContext.request.contextPath }/cliente_search.jsp'">
    </form>
    <br>
</body>
</html>