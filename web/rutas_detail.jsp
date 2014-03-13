<%@page import="es.angelcastro.quehaceraqui.clase.Rutas"%>
<%@page import="es.angelcastro.quehaceraqui.Main"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //En request se reciben los datos enviados desde Main
    Rutas rutas  = (Rutas)request.getAttribute("rutas");    
    String action = request.getParameter("action");    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Datos del contacto</h1>
        <!-- Se añade enctype="multipart/form-data" para la subida de archivos -->
        <form method="post" action="Main" enctype="multipart/form-data">
        <!--<form method="post" action="Main">-->
            <input type="hidden" name="id" value="<%=rutas.getId()%>">
            Nombre: <input type="text" name="nombre" value="<%=rutas.getNombre()%>"><br>
            Alias: <input type="text" name="alias" value="<%=rutas.getAlias()%>"><br>
            Kilometros: <input type="text" name="kilometros" value="<%=rutas.getKilometros()%>"><br>
            Dificultad <input type="text" name="dificultad" value="<%=rutas.getDificultad()%>"><br>
            Latitud <input type="text" name="latitud" value="<%=rutas.getLatitud()%>"><br>
            Longitud: <input type="text" name="longitud" value="<%=rutas.getLongitud()%>"><br>
            Localidad: <input type="text" name="localidad" value="<%=rutas.getLocalidad()%>"><br>
            Provincia: <input type="text" name="provincia" value="<%=rutas.getProvincia()%>"><br>
            Pais: <input type="text" name="pais" value="<%=rutas.getPais()%>"><br>
            Comentario: <input type="text" name="comentario" value="<%=rutas.getComentario()%>"><br>
            <% 
                /*String strBirthDate = "";
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if(person.getBirthDate()!=null) {
                    strBirthDate = dateFormat.format(person.getBirthDate());
                }*/
                
            %>
            <%--Fecha nacimiento: <input type="text" name="birth_date" value="<%=strBirthDate%>"><br>
            Observaciones: <input type="text" name="comments" value="<%=person.getComments()%>"><br>--%>
            
            Foto:<br><img src='<%=Main.SAVE_DIR+"/"+rutas.getFotoruta()%>' width="128px">
            <input type="checkbox" name="deletePhoto">Borrar foto (tendrá efecto después de guardar)<br>
            <input type="file" name="fotoruta"><br><br>
            
            <%  //Botón guardar para editar o insertar
                if(action.equals(Main.ACTION_EDIT_REQUEST)) {
                    out.print("<input type='submit' value='Guardar'>");
                    out.print("<input type='hidden' name='action' value='"+Main.ACTION_EDIT_RESPONSE+"'>");
                } else if(action.equals(Main.ACTION_INSERT_REQUEST)) {
                    out.print("<input type='submit' value='Añadir'>");
                    out.print("<input type='hidden' name='action' value='"+Main.ACTION_INSERT_RESPONSE+"'>");
                }
            %>
        </form>
        
        <%-- Botón para Cancelar cambios.
            Para que se muestre de nuevo la lista no hay que indicar 
            ninguna acción y volver a cargar Main --%>
        <form method="post" action="Main">
            <input type="hidden" name="action" value="">
            <input type="submit" value="Cancelar">
        </form>
    </body>
</html>
