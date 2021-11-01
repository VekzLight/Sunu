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
-->-->

<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
    <head>
        <title>Formulario de Registro</title>
    </head>

    <body>
        <div class="containe-parent-body">
            <div class="containe-child1-body">
                <%@ include file="decorators/menu_cliente.jspf" %> 
            </div>
            <div class="containe-child2-body" align="center">
                <form method="post" action="<%=request.getContextPath()%>/ClienteUpdatePwd" id="formularioInicio">
                    <div class="form-group" style="padding-top: 20px">
                      <label class="col-md-4 control-label" >Password</label> 
                        <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                      <input name="password" placeholder="Password" class="form-control" type="password" required>
                        </div>
                      </div>
                    </div>
                    <!-- Button -->
                    <div class="form-group" style="padding-top: 20px">
                      <label class="col-md-4 control-label"></label>
                      <div class="col-md-4"><br>
                        <button type="submit" o class="btn btn-warning" > Cambiar Contraseña <span class="glyphicon glyphicon-send"></span> </button>
                      </div>
                    </div>
                </form>
                
            </div>
            <div class="containe-child3-body"></div>
        </div>
        
        <script>
        //Funcion para revisar que genero eligio el usuario 
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