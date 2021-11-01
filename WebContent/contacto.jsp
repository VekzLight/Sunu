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
    
    <title>Contacto</title>
  </head>
<body>
    <div class="containe-parent-body">
        <div class="containe-child1-body">
            <%@ include file="decorators/menu_principal.jspf" %>
        </div>
        <div class="containe-child2-body">
            <h1 align="center">Contacto</h1>
        </div>
        <div class="containe-child3-body" align="center">
            <div class="card" style="width: 50rem; rgba(0,0,0,0)" >
                <div class="mb-3">
                  <label for="email" class="form-label">Correo Electronico</label>
                  <input type="email" class="form-control" id="email" placeholder="name@example.com">
                </div>
                <div class="mb-3">
                  <label for="textEmail" class="form-label">Mensaje</label>
                  <textarea class="form-control" id="textEmail" rows="3"></textarea>
                </div>
                <button type="button" class="btn btn-secondary">Enviar</button>
            </div>
         </div>
    </div>
</body>
</html>