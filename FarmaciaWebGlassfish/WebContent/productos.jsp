<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.farmaciaWeb.servlets.ServletProducto" %>
<%@ page import="org.farmaciaWeb.modelo.Producto" %> 

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
    
    <div class='section section_productos'> 
    	<h1>Catálogo de productos</h1>
    	<%
    		ServletProducto sp = new ServletProducto();
    		sp.connectURL();
    		sp.createProductoObjects();
    		
    		String html = "<div class='catalogo_productos'>";
			for (int i=0; i< sp.getProductos().size(); i++){
				Producto p = sp.getProductos().get(i);
				html += "<div class='producto'>";
					html += "<div class='cabecera_producto'>";
						html += "<div class='id_producto'>" + p.getId() + "</div>";
						html += "<div class='nom_producto'>" + p.getNombre() + "</div>";
						html += "<div class='dep'>" + p.getDepartamento() + "</div>";
					html += "</div>";
					html += "<div class='descripcion'>" + p.getDescripcion() + "</div>";
					html += "<div class='precios'>";
						html += "<div class='precio'>Precio: " + p.getPrecio_sin_iva() + "€</div>";
						html += "<div class='iva'>IVA:" + new DecimalFormat("##").format((p.getIva()-1)*100) + "%</div>";
						html += "<div class='precio_con_iva'>Precio (IVA incluido):" + new DecimalFormat("##.##").format(p.getIva()*p.getPrecio_sin_iva()) + " €</div>";
					html += "</div>";
				html += "</div>";
			}
			html += "</div>"; // end div class='catalogo_productos'
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