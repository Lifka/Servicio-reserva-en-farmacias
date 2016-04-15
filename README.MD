# INFO

* Diagramas: http://prado.ugr.es/moodle/mod/page/view.php?id=107506 	
* Entrega: http://prado.ugr.es/moodle/mod/assign/view.php?id=107603


# Tareas y reparto



## Diseño arquitectónico


### Capel said:

Documento arquitectonico -> diagramas de casos de uso, patrones, interacción entre componentes, tipos de interacción, diagrama d eclases,... dos, uno para cada parte. + información adicional para explicar por qué lo hemos pensado así


### CASOS DE USO

--> http://prado.ugr.es/moodle/pluginfile.php/308916/mod_page/content/17/caso-uso1.png

* Registrarse -> **JAVI** 	
* Iniciar sesión -> **JAVI** 	
* Cesta -> **JAVI** 	
* Realizar pagos -> **JAVI** 	
* MAS
	* resolver preguntas frecuentes de los clientes -> **JC** 	
	* ayuda e información sobre la protección legal de la aplicación -> **JC** 	
	* indicar la ubicación geográfica por parte del propio cliente -> **JC** 		
	* consultar su lista de peticiones actual -> **JC** 	
	* revisar sus contactos -> **JC** 	
* Comprar por Departamento [Entidad separada] -> **JC** 	
* Ver lista de productos que acumula en la cesta -> **JAVI** 	
* búsquedas y mostrar los resultados de una búsqueda -> **JAVI** 	

* Añadir producto: encargado añade nuevos productos a un departamento.
* Borrar producto: encargado elimina productos de un departamento.
* Añadir stock: encargado añade unidades de productos existentes a un departamento.
* Retirar stock: encargado retira el stock de un producto y lo deja como agotado.

### PATRONES

 * Observador -> Un cliente se suscribe a un producto agotado, este patrón le avisa cuando hay existencias.
 * Visitante -> Ofrece la posibilidad de distinguir entre distintas formas de buscar entre los productos que contiene el servidor. Permite recorrer una etsructura jerárquica sin modificar los produtos.
 * Factoría abstracta -> Para crear los productos en stock
 * Abstracción-caso -> Abstraer el departamento de los productos, evitar redundancia.
 * Fachada -> 

**Registrarse**: se trata de una funcionalidad compleja necesaria para acceder a la aplicación, como nuevo usuario, o volver. Incluirá informaciones sobre perfil del cliente, proyecto, peticiones actualizadas, preferencias. Tendremos en cuenta aquí la gestión necesaria para Crear-cuenta, sí como otra funcionalidad que tiene que ver con la administración de la misma: configuración de perfil, pedidos y preferencias.

**Iniciar sesión** debe ser un caso de uso independiente, que se encargue de la Identificación del usuario que vuelve a conectarse; también ha de ocuparse, eventualmente, de la Recuperación de contraseña y de actualizar la ubicación geográfica.

**Cesta** tiene que ver con la tarea requerida para realizar el Pago  de productos seleccionados, dando la oportunidad al usuario de arrepentirse de su selección, lo que se describe mediante el caso de uso: Borrar cesta. 

**Realizar pagos** describe cómo se realizará el pago según la modalidad seleccionada: TDC, transferencia, etc.

**MAS** describe la funcionalidad añadida al caso de uso inicial. Se concreta en casos de uso específicos: **resolver preguntas frecuentes de los clientes**, **ayuda e información sobre la protección legal de la aplicación**, así como para **indicar la ubicación geográfica por parte del propio cliente**, **consultar su lista de peticiones actual** y **revisar sus contactos**.

Dejamos el caso de uso **Comprar por Departamento** como una entidad separada, queriendo indicar que su comportamiento es ortogonal con  los demás casos de uso y, por tanto, el sistema ha de poder accederlo en cualquier momento y en cualquier contexto. Ha de incluir la posibilidad de mostrar los productos destacados (más vendidos) del día, así como mostrar el inventario de los diferentes departamentos de la farmacia (medicamentos, homeopatía, perfumería, etc.). Ha de incluir un caso de uso relacionado con **búsquedas y mostrar los resultados de una búsqueda**, así como el usuario ha de poder **ver la lista de productos que acumula en su cesta**.


# Cómo subir cosas


### Primera vez

git init 	
git pull https://github.com/Lifka/Servicio-reserva-en-farmacias.git 	
git add * 	
git commit -m "commit" 	
git remote add javiguapo https://github.com/Lifka/Servicio-reserva-en-farmacias.git 	
git push javiguapo master 


### Segunda vez	

git add * 	
git commit -m "commit" 	
git push javiguapo master 
