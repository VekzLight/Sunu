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
        <title>Registro Fallido</title>
    </head>

    <body>
        <div class="containe-parent-body">
            <div class="containe-child1-body">
                <%@ include file="decorators/menu_principal.jspf" %> 
            </div>
            <div class="containe-child2-body" align="center" style="padding-top: 20px;">
                <h1 align="center">Error al registrar el usuario</h1>
            </div>
            <div class="containe-child3-body" align="center">
                <button class="btn btn-primary" onclick="window.location='${pageContext.request.contextPath}/cliente_register.jsp'"> Registrar </button>
            </div>
        </div>
    </body>
</html>