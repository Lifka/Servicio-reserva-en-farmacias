<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.farmaciaWeb.servlets.ServletProducto" %>
<%@ page import="org.farmaciaWeb.servlets.ServletFarmacias" %>
<%@ page import="org.farmaciaWeb.modelo.Producto" %> 
<%@ page import="org.farmaciaWeb.modelo.Farmacia" %> 
<%@ page import="org.farmaciaWeb.modelo.Direccion" %>
<%@ page import="org.farmaciaWeb.modelo.Departamento" %>  

<!DOCTYPE html>
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
    
    <div class='section section_administracion'>
    	<div class='section_administracion_productos'>
    		<h1>Administración de productos</h1>
    		<form id='form_nuevo_prod' method="get" action="ServletProducto">
    			<fieldset>
    				<legend>Crear nuevo producto</legend>
    			<input type='text' name='nom_prod' placeholder='nombre del producto' id='nom_prod' required>
    			<input type='text' name='desc_prod' placeholder='breve descripción' id='desc_prod' required>
    			<input type='text' name='precio_prod' placeholder='Precio (sin iva)' id='precio_prod' required>
    			<input type='text' name='iva_prod' placeholder='iva (%)' id='iva_prod' required>
    			<input type='hidden' name='accion_form' value='new' />
    			<select id='dep_producto' name='dep_prod'>
    				<%
    					for (int i=0;i<Departamento.values().length; i++){
    						out.println("<option value='"+Departamento.values()[i]+"'>"+Departamento.values()[i]+"</option>");
    					}
    				%>
    			</select>
    			<input type='submit' value='Crear producto' />
    			</fieldset>
    		</form>
    		<form id='form_remove_prod' action="ServletProducto" method="get"> 
    			<fieldset>
    				<legend>Eliminar producto por id</legend>
    				<input type='text' name='id_prod' placeholder='id del producto' id='id_prod' required/>
    				<input type='submit' value='Eliminar producto' />
    				<input type='hidden' name='accion_form' value='del' />
    			</fieldset>
    		</form>
    	</div> 
    	<div class='section_administracion_farmacias'>
    		<h1>Administración de farmacias</h1>
    		<form id='form_nueva_farm' method="get" action="ServletFarmacias">
    			<fieldset>
    				<legend>Crear nueva farmacia</legend>
    				<input type='text' name='cif_farm' placeholder='cif de la farmacia' id='cif_farm' required>
    				<input type='text' name='nom_farm' placeholder='nombre de la farmacia' id='nom_farm' required>
    				<input type="time" name='hora_apertura' placeholder='hora aprtura (hh:mm)' id='hora_apertura' required>
    				<input type="time" name='hora_cierre' placeholder='hora cierre (hh:mm)' id='hora_cierre' required>
    			</fieldset>
    			<p>Location Google Maps</p>
    			<fieldset>
	    			<input type="number" name='latitud' placeholder='latitud' id='latitud' step="any" required>
	    			<input type='number' name='longitud' placeholder='longitud' id='longitud' step="any" required>
    			</fieldset>
    			<p>Dirección</p>
    			<fieldset>
	    			<input type='text' name='calle_dir' placeholder='calle' id='calle_dir' required>
	    			<input type='number' name='num_dir' placeholder='número' id='num_dir' required>
	    			<input type='text' name='letra_dir' placeholder='letra' id='letra_dir' maxlength="1" >
	    			<input type='text' name='ciudad_dir' placeholder='ciudad' id='ciudad_dir' required>
	    			<input type='text' name='cod_dir' placeholder='cp' id='cod_dir' size="5">
	    			<input type='text' name='provincia_dir' placeholder='provincia' id='provincia_dir' required>
	    			<input type='text' name='pais_dir' placeholder='país' id='pais_dir' required>
	    			<input type='hidden' name='accion_form' value='new' />
	    			<input type='submit' value='Crear farmacia' />
    			</fieldset>
    		</form>
    		<form id='form_remove_farm' action="ServletFarmacias" method="get"> 
    			<fieldset>
    				<legend>Eliminar farmacia por cif</legend>
    				<input type='text' name='cif_farm' placeholder='cif de la farmacia' id='cif_farm' required/>
    				<input type='submit' value='Eliminar farmacia' />
    				<input type='hidden' name='accion_form' value='del' />
    			</fieldset>
    		</form>
    	</div>
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
    