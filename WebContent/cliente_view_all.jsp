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
        <div class="step h1 text-center" style="padding: 20px 20px;">Detalle del Cliente</div>
        
       <c:forEach var="cliente" items="${ clientes }">
            <table class="table" style="padding: 10px;">
                <thead>
                    <tr>
                      <th scope="col">id</th>
                      <th scope="col">Nombre</th>
                      <th scope="col">Paterno</th>
                      <th scope="col">Materno</th>
                      <th scope="col">Edad</th>
                      <th scope="col">Sexo</th>
                    </tr>
                </thead>
                <tbody>
                  <tr>
                      <td> <input size="20" name="nombre" value="${cliente.id}"></td>
                      <td> <input size="20" name="nombre" value="${cliente.nombre}"></td>
                      <td> <input size="20" name="paterno" value="${cliente.paterno}"></td>
                      <td> <input size="20" name="materno" value="${cliente.materno}"></td>
                      <td> <input size="20" name="materno" value="${cliente.edad}"></td>
                      <td> <input size="20" name="materno" value="${cliente.sexo}"></td>
                  </tr>
                </tbody>
            </table>
            
            <table class="table">
                <thead>
                    <tr>
                      <th scope="col">id</th>
                      <th scope="col">Usuario</th>
                      <th scope="col">Email</th>
                    </tr>
                </thead>
                <tbody>
                  <tr>
                      <td> <input size="20" name="nombre" value="${cliente.usuario.id_usuario}"></td>
                      <td> <input size="20" name="nombre" value="${cliente.usuario.nombre_usuario}"></td>
                      <td> <input size="20" name="paterno" value="${cliente.usuario.email}"></td>
                  </tr>
                </tbody>
            </table>
            <br>
        </c:forEach>
        <input style="margin-left: 80%; margin-top: 50px;" class="btn btn-primary" type="button" value="  Regresar  " onclick="window.location='${ pageContext.request.contextPath }/ClienteUpdateForm'">
</body>
</html>