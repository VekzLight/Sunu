<!-- 
*************************************************
* DNA System                                    *
* Por imposible que parezca ¡Tiene Solución!    *
*                                               *
* José Enrique García Ramírez        2163033941 *
* Tania Guadalupe Zárate Chávez      2173075371 *
* Christopher Yael Meneses Martínez  2152001568 *
* Hurtado Avilés Gabriel             2172000781 *
*                                               *
* Taller de desarrollo de aplicaciónes web      *
* Hugo Pablo Leyva                              *
* 13/Agosto/2021                                *
*************************************************
-->

<%@ page contentType="text/html; charset=UTF-8" %>
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
    
    <div class="containe-parent-body2">
        <div class="containe-child1-body">
            <%@ include file="decorators/menu_admin.jspf" %>
        </div>
        <div class="containe-child2-body" >
            <div style="margin: 20px;" align="right">
                <p>Ver Informacion de todos los usuarios</p>
                <input class="btn btn-primary" value="View" type="button" onclick="window.location='ClienteFormAllView'"> </td>
                <input class="btn btn-primary" value="HTML" type="button" onclick="window.location='ClienteFormAllHtml'"> </td>
                <input class="btn btn-primary" value="PDF"  type="button" onclick="window.location='ClienteFormAllPdf'"> </td>
                <input class="btn btn-primary" value="XLS"  type="button" onclick="window.location='ClienteFormAllXls'"> </td>
            </div>
            </div>
            <div class="containe-child3-body">
                <table class="table">
                <thead>
                    <tr>
                      <th scope="col">Nombre</th>
                      <th scope="col">Paterno</th>
                      <th scope="col">Materno</th>
                      <th scope="col">View</th>
                      <th scope="col">HTML</th>
                      <th scope="col">PDF</th>
                      <th scope="col">XLS</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cliente" items="${ clientes }">
                      <tr>
                          <td> <input class="form-control" size="20" name="nombre" value="${cliente.nombre}"></td>
                          <td> <input class="form-control" size="20" name="paterno" value="${cliente.paterno}"></td>
                          <td> <input class="form-control" size="20" name="materno" value="${cliente.materno}"></td>
                          <td> <input class="btn btn-primary" value="View" type="button" onclick="window.location='ClienteFormView?llave=${cliente.id}'"> </td>
                          <td> <input class="btn btn-primary" value="HTML" type="button" onclick="window.location='ClienteFormHtml?llave=${cliente.id}'"> </td>
                          <td> <input class="btn btn-primary" value="PDF"  type="button" onclick="window.location='ClienteFormPdf?llave=${cliente.id}'"> </td>
                          <td> <input class="btn btn-primary" value="XLS"  type="button" onclick="window.location='ClienteFormXls?llave=${cliente.id}'"> </td>
                      </tr>
                    </c:forEach> 
                </tbody>
            </table>
        </div>
    </div>              
</body>
</html>