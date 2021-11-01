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
            charset="windows-1252">
            
    <meta   name="GENERATOR" 
            content="SeaMonkey/2.40 [en] (Windows; 10; Intel(R) Core(TM) i7-4500U CPU @ 1.80GHz 2.40 GHz) [Composer]">
    
    <meta   name="Author"
            content="DNA System">
    
    <title>Catálogo de Productos</title>
  </head>
  <body>
    <div class="containe-parent-body">
        <div class="containe-child1-body">
            <%@ include file="decorators/menu_principal.jspf" %>
        </div>
        <div class="containe-child2-body">
            <h1 align="center">Explora el mundo que te rodea con Sunu Band</h1>
        </div>
        <div class="containe-child3-body">
            <div class="container-productos-cliente">
                <c:forEach var="producto" items="${ productos }"> 
                    <div class="container-productos">
                        <img class="productos" src="<%=request.getContextPath()%>/img/productos/sunu${producto.id}.jpg">
                        <p align="center">${producto.nombre}</p>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>              
 
    </body>
</html>