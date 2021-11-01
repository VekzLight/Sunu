<!-- 
*************************************************
* DNA System                                    *
* Por imposible que parezca �Tiene Soluci�n!    *
*                                               *
* Jos� Enrique Garc�a Ram�rez        2163033941 *
* Tania Guadalupe Z�rate Ch�vez      2173075371 *
* Christopher Yael Meneses Mart�nez  2152001568 *
* Hurtado Avil�s Gabriel             2172000781 *
*                                               *
* Taller de desarrollo de aplicaci�nes web      *
* Hugo Pablo Leyva                              *
* 13/Agosto/2021                                *
*************************************************
-->

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
    
    <title>Sunu Home</title>
  </head>
<body>
    <div class="containe-parent-body2">
        <div class="containe-child1-body2">
            <%@ include file="decorators/menu_principal.jspf" %>
        </div>
        <div class="containe-child2-body2">
            <div class="containe-parent-form1">
                <div class="containe-child1-form1">
                    <input  class="btn btn-primary"
                            type="button"
                            value="Ingresar" 
                            onclick="window.location='login.jsp'" />
                </div>
                <div class="containe-child2-form1"> <p>�aun no tienes cuenta?  <a class="btn btn-link" href="cliente_register.jsp">Registrar</a></p>  </div>
               
            </div>
            <div class="parent">
                <div class="div1">
                    <h1>Explora el mundo que te rodea con Sunu Band</h1>
                    <br>
                    <h2>Y nuestros demas productos</h2>
                </div>
                <div class="div2">
                    <img class="don" src="<%=request.getContextPath()%>/img/don.png">
                </div>
                <div class="div3">
                    <iframe width="420" height="315" src="https://www.youtube.com/embed/1Y6DOF-QEfE"></iframe>
                </div>
                <div class="div4">
                    <div class="card" style="width: 18rem;">
                      <ul class="list-group list-group-flush">
                        <li class="list-group-item">Detecta obst�culos y reduce accidentes en la parte superior del cuerpo</li>
                        <li class="list-group-item">Seg�n los usuarios, les ayud� a evitar 9 de cada 10 accidentes</li>
                        <li class="list-group-item">Gana m�s confianza caminando en cualquier lugar</li>
                      </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>