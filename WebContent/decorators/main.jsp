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

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>

<html>
  <head>
    <title>
      <decorator:title default="T�tulo" />
    </title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/styleMain.css">
    
    <decorator:head />
  </head>

  <body>
    <div class="container-parent-main">
        <div class="container-chlid1-main"> <%@ include file="header.jspf" %>    </div>
        <div class="container-chlid2-main"> <decorator:body />                   </div>
        <div class="container-chlid3-main"> <%@include file="footer.jspf" %>     </div>
    </div>
  </body>
</html>