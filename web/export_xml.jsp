<?xml version="1.0" encoding="UTF-8"?>
<%-- La línea anterior debe ir siempre la primera si se genera un XML --%>

<%@page import="java.util.ArrayList"%>
<%@page import="es.angelcastro.quehaceraqui.clase.Rutas"%>

<%-- Se informa que el contenido va a ser XML --%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>

<address_book>
<% 
    ArrayList<Rutas> rutasList = (ArrayList)request.getAttribute("rutasList"); 
    for(Rutas rutas: rutasList) {
        out.println("<rutas>");
        out.println("<id>"+rutas.getId()+"</id>");
        out.println("<nombre>"+rutas.getNombre()+"</nombre>");
        out.println("<alias>"+rutas.getAlias()+"</alias>");
        out.println("<kilometros>"+rutas.getKilometros()+"</kilometros>");
        out.println("<dificultad>"+rutas.getDificultad()+"</dificultad>");
        out.println("<latitud>"+rutas.getLatitud()+"</latitud>");
        out.println("<longitud>"+rutas.getLongitud()+"</longitud>");
        out.println("<localidad>"+rutas.getLocalidad()+"</localidad>");
        out.println("<provincia>"+rutas.getProvincia()+"</provincia>");
        out.println("<pais>"+rutas.getPais()+"</pais>");
        out.println("<comentario>"+rutas.getComentario() +"</comentario>");
        out.println("<fotoruta>"+rutas.getFotoruta()+"</fotoruta>");
        out.println("</rutas>");
    }
%>
</address_book>
