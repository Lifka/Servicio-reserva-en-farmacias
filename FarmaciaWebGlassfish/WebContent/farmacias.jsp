<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.farmaciaWeb.servlets.ServletFarmacias" %>
<%@ page import="org.farmaciaWeb.modelo.Farmacia" %> 
<%@ page import="org.farmaciaWeb.modelo.Direccion" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/web.css" />
<title>Farmacia</title>

</head>
<body>
	<div class='nav' id="navegacion">
        <ul>
        	<li><a href="index.jsp">Home</a></li>
            <li><a href="productos.jsp">Productos</a></li>
            <li><a href="farmacias.jsp">Farmacias</a></li>
            <li><a href="administracion.jsp">Administración</a></li>
        </ul>
    </div>
    
    <div class='section section_farmacias'> 
    	<h1>Encuentra nuestras farmacias</h1>
    	<%
			ServletFarmacias sf = new ServletFarmacias();
    		sf.connectURL();
    		sf.createFarmacias();
    		List<Farmacia> farmacias = sf.getFarmacias();
    		
    		String html = "<div class='catalogo_farmacias'>";
			for (int i=0; i< farmacias.size(); i++){
				Farmacia f = farmacias.get(i);
				html += "<div class='farmacia'>";
					html += "<div class='info_farmacia'>";
						html += "<div class='cabecera_farmacia'>";
							html += "<div class='nombre_farmacia'>" + f.getNombre() + "</div>";
							html += "<div class='horario_farmacia'>";
								String hora_s = "";
								if (f.getHorario_abrir().getMinutes() < 10)
									hora_s = f.getHorario_abrir().getMinutes() + "0";
								else
									hora_s += f.getHorario_abrir().getMinutes();
								
								html += "<span class='horario_apertura'>" + f.getHorario_abrir().getHours() + ":" + hora_s + " - </span>";
								
								hora_s = "";
								if (f.getHorario_cerrar().getMinutes() < 10)
									hora_s = f.getHorario_cerrar().getMinutes() + "0";
								else
									hora_s += f.getHorario_cerrar().getMinutes();
									
								html += "<span class='horario_cierre'>" + f.getHorario_cerrar().getHours() +":" + hora_s +"</span>";
							html += "</div>";
							html += "<p>CIF: " + f.getCif() + "</p>";
						html += "</div>";
						Direccion d = f.getDireccion();
						html += "<div class='direccion_farmacia'>";
							html += "<span class='calle_farmacia'>C/:" + d.getCalle() + " nº " + d.getNumero() + d.getLetra() + "</span>";
							html += "<span class='ciudad'>" +d.getCiudad() + " - " + d.getCodigo_postal() + " ("+ d.getProvincia()+")</span>";
						html += "</div>";
					html += "</div>";
					html += "<div class='access_google_maps'>";
						html += "<a href='http://maps.google.es/?q="+f.getLatitud()+"%20"+f.getLongitud()+"'>Ver en Google Maps <span class='angle-right'>></span></a>";
					html += "</div>";
				html += "</div>";
			}
			html += "</div>";
			
			out.println(html);
    		
    	%>
    </div>
    
    <div class='footer'>
    <!-- Contacto -->
        <div class="footer-izquierdo">
            <div class="contacto">
                <p>Farmacia</p>
                <p>Imprenta, nº 2. 18010 Granada, Granada, España</p>
                <p>Teléfono: +34 958 215 273. ,	Fax: +34 958 225 765</p>
                <p>info@farmacia.com</p>
            </div>
        </div>
        
    <!-- Redes sociales -->
        <div class="footer-derecho">
            <div class="social">
                <div class="facebook">
                    <a href="#"></a>
                </div>
                <div class="twitter">
                    <a href="#"></a>
                </div>
                <div class="google-plus">
                    <a href="#"></a>
                </div>
            </div> 
        
    <!-- Copyright -->
            <div class="copyright">© Copyright 2016</div>
        </div>
    </div>
</body>
</html>