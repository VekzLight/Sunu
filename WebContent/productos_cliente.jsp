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
    
    <div class="containe-parent-body2">
        <div class="containe-child1-body2">
            <%@ include file="decorators/menu_cliente.jspf" %>
        </div>
        <div class="containe-child2-body2">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Imagen</th>
                  <th scope="col">Nombre</th>
                  <th scope="col">Precio</th>
                  <th scope="col">Comprar</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="producto" items="${ productos }">
                    <form method="post" action="${pageContext.request.contextPath}/ProductoCompra">
                        <input name="nombreCliente" value="${nombreCliente}" type="hidden"/>
                        <c:if test="${producto.cantidad  > 0}">
                          <input name="idProducto" value="${producto.id}" type="hidden"/>
                          <input name="cantidad" value="${producto.cantidad}" type="hidden"/>
                          <input name="precio" value="${producto.precio}" type="hidden"/>
                          <input name="nombre" value="${producto.nombre}" type="hidden"/>
                          <tr>
                              <th scope="row">
                                <img class="productos" src="<%=request.getContextPath()%>/img/productos/sunu${producto.id}.jpg">                        
                              </th>
                              <td> ${producto.nombre}</td>
                              <td> ${producto.precio}</td>
                              <td> <input class="btn btn-primary" value="Comprar" type="submit"> </td>
                          </tr>
                        </c:if>
                    </form>
                </c:forEach> 
              </tbody>
            </table>
        </div>
    </div>              
 
  </body>
</html>