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
            <%@ include file="decorators/menu_admin.jspf" %>
        </div>
        <div class="containe-child2-body">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Imagen</th>
                  <th scope="col">Nombre</th>
                  <th scope="col">Precio</th>
                  <th scope="col">Cantidad</th>
                  <th scope="col">Modificar</th>
                  <th scope="col">Borrar</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="producto" items="${ productos }">
                    <form method="post" action="${pageContext.request.contextPath}/ProductoUpdate">
                        <input name="nombreCliente" value="${nombreCliente}" type="hidden"/>
                        <c:if test="${producto.cantidad  > 0}">
                          <input name="idProducto" value="${producto.id}" type="hidden"/>
                          <tr>
                              <th scope="row">
                                <img class="productos" src="<%=request.getContextPath()%>/img/productos/sunu${producto.id}.jpg">                        
                              </th>
                              <td> <input class="form-control" size="20" name="nombre" value="${producto.nombre}"></td>
                              <td> <input class="form-control" size="20" name="precio" value="${producto.precio}"></td>
                              <td> <input class="form-control" size="20" name="cantidad" value="${producto.cantidad}"></td>
                              <td> <input class="btn btn-primary" name="send" value="Modificar" type="submit"> </td>
                              <td> <input class="btn btn-primary" name="send" value="Borrar"    type="submit"> </td>
                          </tr>
                        </c:if>
                    </form>
                </c:forEach> 
              </tbody>
            </table>
        </div>
        
        <div class="containe-child3-body">
            <form method="post" action="${pageContext.request.contextPath}/ProductoInsert">
                <table class="table">
                    <thead>
                        <tr>
                          <th scope="col">Nombre</th>
                          <th scope="col">Precio</th>
                          <th scope="col">Cantidad</th>
                          <th scope="col">Agregar</th>
                    </thead>
                    <tbody>
                        <input name="nombreCliente" value="${nombreCliente}" type="hidden"/>
                          <tr>
                              <td> <input class="form-control" size="20" name="nombre" ></td>
                              <td> <input class="form-control" size="20" name="precio" ></td>
                              <td> <input class="form-control" size="20" name="cantidad" ></td>
                              <td> <input class="btn btn-primary" value="Añadir" type="submit"> </td>
                          </tr>
                        <br>
                    </tbody>
                </table>
            </form>
        </div>              
    </body>
</html>