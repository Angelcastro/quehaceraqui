<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="es.angelcastro.quehaceraqui.clase.Rutas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            out.println("Aplicación ejecutada en la máquina: ");
            try {
                BufferedReader br = new BufferedReader(new FileReader("/etc/hostname"));
                out.println("<b>" + br.readLine() + "</b>");
            } catch(FileNotFoundException ex) {
                out.println("<b>Nombre de servidor no encontrado</b><br>");
                out.println("Sólo se detectarán los nombres de servidores Ubuntu");
            }
        %>
        <div id="cabe" style="alignment-adjust: central; background-color:  brown; border:solid #000 2px"><h1 style="color: white"align="center">Lista de Rutas.</h1></div>
        <br>
        <br>
        
        <table align="center" border="0" width="60%">
            <tr>
                <th style="background-color: #666666; color: white">Nombre</th>
                <th style="background-color: #666666; color: white">Alias</th>
                <th style="background-color: #666666; color: white">Dificultad</th>
                <th style="background-color: #666666; color: white">Localidad</th>
                <th style="background-color: #666666; color: white">Pais</th>
            </tr>
        <% 
            ArrayList<Rutas> rutasList = (ArrayList)request.getAttribute("rutasList"); 
            for(Rutas rutas: rutasList) {
                out.println("<tr>");
                out.println("<td align='center' style='background-color: #cccccc;'>"+rutas.getNombre()+"</td>");
                out.println("<td align='center' style='background-color: #cccccc;'>"+rutas.getAlias()+"</td>");
                out.println("<td align='center' style='background-color: #cccccc;'>"+rutas.getDificultad()+"</td>");
                out.println("<td align='center' style='background-color: #cccccc;'>"+rutas.getLocalidad()+"</td>");
                out.println("<td align='center' style='background-color: #cccccc;'>"+rutas.getPais()+"</td>");
                //Enlace para editar el registro
                String editLink = "Main?action=E&id="+rutas.getId();
                out.println("<td align='center' style='background-color: #cccccc;'><a href='"+editLink+"'><button>Editar</button></td>");
                //Enlace para eliminar el registro con confirmación por parte del usuario
                String deleteLink = "Main?action=D&id="+rutas.getId();
                String deleteConfirmText = "Confirme que desea eliminar La Ruta:\\n"+rutas.getNombre()+" "+rutas.getAlias();
                out.println("<td align='center' style='background-color: #cccccc;'><a href='"+deleteLink+"' onCLick='return confirm(\""+deleteConfirmText+"\")'><button>Suprimir</button></td>");
                
                out.println("</tr>");
            }
        %>
        </table>
        <br>
        <table align="center">
            <tr>
                <td>  
            
            <form  method="get" action="Main">
                <input type="hidden" name="action" value="I">
                <input type="submit" value="Nuevo Contacto">
            </form>
                </td>
                <td>
            <form method="get" action="Main" target="_blank">
                <input type="hidden" name="action" value="X">
                <input type="submit" value="Exportar XML">
            </form>
                </td>
        </tr>
        </table>
    </body>
</html>
