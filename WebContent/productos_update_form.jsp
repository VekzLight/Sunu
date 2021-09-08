<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
    
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
      <div class="step"> Catálogo de Productos </div>
      <div class="instructions"> Actualiza los Campos que se
        Requieran Modificar </div>
      <br>
      
      <sql:query var="resultados" dataSource="jdbc/TestDS"
        sql="SELECT * FROM producto;" />
      
      
      <c:forEach var="fila" items="${ resultados.rows }">
          <form method="post" action="${pageContext.request.contextPath}/ProductoUpdate">
            <table width="100%">
                <tr class="form">
                    <td align="center">
                      <div class="label"> Clave </div>
                    </td>
                    <td align="center">
                      <div class="label"> Nombre </div>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td align="center">${fila.id_producto}<input type="hidden" name="id" value="${fila.id_producto}"></td>
                    <td align="center">
                      <table border="0" cellpadding="0" cellspacing="0">
                          <tr>
                                <td>
                                    <input size="20" name="nombre" value="${fila.nombre_producto}">
                                </td>
                          </tr>
                      </table>
                    </td>
                    <td valign="bottom">
                        <input value="  Modificar  " type="submit">
                        <input value="  Borrar  " type="submit">
                    </td>
                </tr>
            </table>
          </form>
        </c:forEach>
      
        <table width="100%">
            <tr>
                <td align="center">
                  <input size="50">
                </td>
                <td align="center">
                  <table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>:</td>
                      <td>
                        <input size="20">
                      </td>
                    </tr>
                  </table>
                </td>
                <td valign="bottom">
                  <input value="  Agregar  " type="submit">
                </td>
            </tr>
        </table>
    
    <br>
    
    <input value="  Regresar  " onclick="window.location='${pageContext.request.contextPath}/cliente_update_form.jsp'" type="button">

    <br>
</body>
</html>