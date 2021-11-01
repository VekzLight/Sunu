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
        <title>Formulario de Registro</title>
    </head>

    <body>
        <div class="containe-parent-body2">
            <div class="containe-child1-body2">
                <%@ include file="decorators/menu_principal.jspf" %> 
            </div>
            <div class="containe-child2-body2" align="center">
                <div align="right" style="margin-top: 10px; margin-right: 10px;">
                    <p>¿Ya tienes una cuenta?</p>
                    <input  class="btn btn-primary"
                            
                            type="button"
                            value="Ingresar" 
                            onclick="window.location='login.jsp'" />
                </div>
                <form method="post" action="<%=request.getContextPath()%>/RegisterServlet" id="formularioInicio">
                    <div class="form-group" style="padding-top: 20px">
                      <label class="col-md-4 control-label">Nombre de Usuario</label>  
                      <div class="col-md-4 inputGroupContainer">
                      <div class="input-group">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                      <input  name="nombre_usuario" placeholder="Usuario del sistema" class="form-control"  type="text" required>
                        </div>
                      </div>
                    </div>
                    
                    <!-- Text input-->
                       <div class="form-group" style="padding-top: 20px">
                      <label class="col-md-4 control-label">E-Mail</label>  
                        <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                      <input name="email" placeholder="Direccion valida" class="form-control"  type="text" required>
                        </div>
                      </div>
                    </div>
                    
                    <!-- Text input-->
                    
                    <div class="form-group" style="padding-top: 20px">
                      <label class="col-md-4 control-label" >Password</label> 
                        <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                      <input name="password" placeholder="Password" class="form-control"  type="password" required>
                        </div>
                      </div>
                    </div>
                    
                    
                    <div class="form-group" style="padding-top: 20px">
                      <label class="col-md-4 control-label">Nombre</label>  
                      <div class="col-md-4 inputGroupContainer">
                      <div class="input-group">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                      <input  name="nombre_cliente" placeholder="Nombre o Nombres" class="form-control"  type="text" required>
                        </div>
                      </div>
                    </div>
                    
                    <!-- Text input-->
                    
                    <div class="form-group" style="padding-top: 20px">
                      <label class="col-md-4 control-label" >Apellido Paterno</label> 
                        <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                      <input name="apellido_paterno_cliente" placeholder="Apellido Paterno" class="form-control"  type="text" required>
                        </div>
                      </div>
                    </div>
                    
                    <!-- Text input-->
                    
                    <div class="form-group" style="padding-top: 20px">
                      <label class="col-md-4 control-label" >Apellido Materno</label> 
                        <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                      <input name="apellido_materno_cliente" placeholder="Apellido Materno" class="form-control"  type="text" required>
                        </div>
                      </div>
                    </div>
                      
                    
                    <!-- Text input-->
                    
                    <div class="form-group" style="padding-top: 20px">
                      <label class="col-md-4 control-label" >Edad</label> 
                        <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                      <input name="edad_cliente" placeholder="Edad en numeros" class="form-control"  type="text" required>
                        </div>
                      </div>
                    </div>
                    
                    <div class="form-group" style="padding-top: 20px"> 
                      <label class="col-md-4 control-label">Genero</label>
                        <div class="col-md-4 selectContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                        <select id="id_sexo" name="sexo_id_sexo" class="form-control selectpicker" required>
                          <option type="radio" value="">Selecciona tu Genero</option>
                          <option name="sexo_id_sexo_G" >Masculino</option>
                          <option name="sexo_id_sexo_M" >Femenino</option>
                        </select>
                      </div>
                    </div>
                    </div>
                    
                    <!-- Button -->
                    <div class="form-group" style="padding-top: 20px; padding-bottom: 50px;">
                      <label class="col-md-4 control-label"></label>
                      <div class="col-md-4"><br>
                        <button type="submit" onclick="revisarGenero()" class="btn btn-primary" > Registrar <span class="glyphicon glyphicon-send"></span> </button>
                      </div>
                    </div>
                </form>
                
            </div>
        </div>
        <script>
            //Funcion para revisar que genero eligio el usuario
            function revisarGenero() {
            var combo = document.getElementById("id_sexo");
            if(combo.selectedIndex <=0) {
              alert("Sin seleccionar genero");
            } else if(combo.selectedIndex == 2) {
              alert("Masculino");
            } else if(combo.selectedIndex == 1) {
                alert("Femenino");
            } 
            
            //Funcion para revisar que se llenen todos los campos
            var inputs = $("form#formularioInicio input, form#formularioInicio textarea");
            var validateInputs = function validateInputs(inputs) {
              var validForm = true;
              inputs.each(function(index) {
                var input = $(this);
                if (!input.val() || (input.type === "radio" && !input.is(':checked'))) {
                  $("#sexo_id_sexo").attr("disabled", "disabled");
                  validForm = false;
                }
              });
              return validForm;
            }
            
            //Funcion aux para revisar que se llenen todos los campos
            inputs.change(function() {
              if (validateInputs(inputs)) {
                $("#edad_cliente").removeAttr("disabled");
              }
            });
            
        </script>
        
    </body>
</html>