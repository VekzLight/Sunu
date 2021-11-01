<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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

<html>
  <head>
    <meta   http-equiv="content-type" 
            content="text/html" 
            charset="windows-1252" />

    <meta   name="GENERATOR" 
            content="SeaMonkey/2.40 [en] (Windows; 10; Intel(R) Core(TM) i7-4500U CPU @ 1.80GHz 2.40 GHz) [Composer]" />

    <meta   name="Author" 
            content="DNA System" />

    <title>Página de Login</title>
  </head>
  <body>
    <div class="containe-parent-body2">
        <div class="containe-child1-body2">
            <%@ include file="decorators/menu_principal.jspf" %> 
        </div>
        <div class="containe-child2-body2">
            <form name="form" method="post" action="<%=request.getContextPath()%>/LoginServlet">
                <div class="formRegistro" align="center">
                  <div class="text" style="padding-top: 20px;"> <h1>Forma de Login</h1></div>
                  <div class="text2"> <h3>Proporciona tu login y tu contraseña para tener acceso a la aplicación </h3></div>
                  <div class="usuariotext">Usuario:</div>
                  <div class="usuariotf">  <input class="form-control" name="nombre_usuario" size="10" />  </div>
                  <div class="passtxt"> Contraseña: </div>
                  <div class="passtf"> <input  class="form-control" name="password" size="10" type="password"> </div>
                  <div class="alerttxt">
                    <span style="color:red"> 
                        <%= (request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage") %>
                    </span>
                  </div>
                  <div class="passbtn"> <input class="btn btn-primary" type="submit" value=" Ingresar "/> </div>
                  <div class="registrobtn">
                    <p> ¿No tiene cuenta? </p>
                    <input  class="btn btn-secondary" type="button"
                                value=" Registro " 
                                onclick="window.location='cliente_register.jsp'" />
                  </div>
                  <div class="limpiar"> <input class="btn btn-info" type="reset" value=" Limpiar "/> </div>
                </div>
        
            </form>
            
        </div>
    </div>
  </body>
</html>