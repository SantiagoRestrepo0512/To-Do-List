# To-Do-List Application

Esta aplicación permite gestionar una lista de tareas donde los usuarios pueden realizar funciones básicas como: Agregar, consultar, filtrar por ID, eliminar tareas y actualizar las mismas ya sea cambiando su estado o su prioridad.

# Tabla de Contenido



- 📝 **[Requisitos](#requisitos)**
- 🔧 **[Tecnologías Implementadas](#tecnologías-implementadas)**
- ⚙️ **[Instalación](#instalación)** 
- 🗄️ **[Base de Datos](#base-de-datos)** 
- 🧩 **[Funcionalidades](#funcionalidades)**
- 🚀 **[Uso](#uso)**
- 📞 **[Contacto](#contacto)**

##  Requisitos
- Java 17 o superior.
- Maven.
- Git.
- IDE (Eclipese de preferencia).
- Postman (Opcional para prueba de API).

## Tecnologías Implementadas

- Java.
- Spring Boot.
- Mockito.
- Junit5.
- H2 DataBase.
- Git.


## Instalación 

Si se desea instalar de manera rapida y sencilla este proyecto es recomendable seguir los siguientes pasos:

- Primero se inicia el IDE con el cual se desea trabajar (Eclipse en este caso).
- En la esquina superior izquierda selecciona la opción **File** > **Import** > **Git** > **Project From Git** > **Clonar URL** 
- Despues de esto, se suministra el siguente enlace en el campo de **URL** 
	https://github.com/SantiagoRestrepo0512/To-Do-List.git
- Una vez se autorellenen los demas campos, debe continuar y seleccionar el Branch **master**
- Selecciona el directorio local donde se desea tener el proyecto y finaliza la importación.


## Base De Datos

Para acceder a la Base de Datos desde el navegador, se puede hacer de la siguiente forma:

### **INICIAR EL PROGRAMA**
- Busca en el **Project Explorer** la ruta **src/main/java/com.nauth.todo**
- Allí dentro da click derecho al archivo **ToDoListApplication.java**
- Selecciona la opción **Run As** y luego la opción **Java Application**

### **Luego que se ejecute el programa**
- Por medio del navegador se ingresa a : http://localhost:8080/h2-console
- Luego ingresa las siguientes credenciales:
	**Driver Class**: org.h2.Driver
	**JDBC URL**: jdbc:h2:mem:testdb
	**User Name**: root
	**Password**: 123


Allí se podrá consultar de manera mas directa la tabla, los atributos y los datos que contiene la misma.


## Funcionalidades

Es importante entender como funciona esta aplicación:
**- Función CreateTask**: Esta función permite a grandes rasgos crear una tarea nueva.

Aquí se le debe suministrar valores en formato .JSON que contenga:
	
	- ID: Este valor es asignado automaticamente por el servicio y es mostrado en pantalla al momento de la creación de la tarea.
	- Descripcion: Esta es obligatoria, donde se describa detalladamente la tarea.
	- Completed: Se le asigna un valor True cuando la tarea sea completada y False cuando dicha tarea este pendiente.
	- Priority: Se le suministrar un valor entero entre 1 y 3 para determinar la prioridad de esta tarea:
		1- ALTA PRIORIDAD
		2- PRIORIDAD INTERMEDIA
		3- BAJA PRIORIDAD.

**- Función GetAllTasks**: Al realizar esta consulta el programa entregara todas las tareas almacenadas, filtradas por prioridad de ALTA  a BAJA  y en caso tal que tengan la misma prioridad procedera a organizarlas por ID.
	
**- Función GetTaskById**: Se le debe añadir un número de ID deseado y esta función buscará la tarea relacionada con este ID y la imprimira por pantalla con su respectivo ID, descripción, estado y prioridad.

**- Función UpdateTask**: Se le debe añadir un número de ID deseado y en el body colocar en formato .JSON el atributo que quieras cambiar y este sera reemplazado y mostrado por pantalla.
Puedes cambiar el completado entre True y False, la prioridad de 1 a 3 y la descripción facilmente. 

**- Función DeleteTask**: Se le debe añadir un número de ID deseado y al ejecutar la acción la tarea vinculada con este ID sera eliminada directamente de la base de Datos.

## Uso

Para probar las funcionalidades de la aplicación debemos hacer lo siguiente:

### **INICIAR EL PROGRAMA COMO SE HIZO ANTERIORMENTE**
- Busca en el **Project Explorer** la ruta **src/main/java/com.nauth.todo**
- Allí dentro da click derecho al archivo **ToDoListApplication.java**
- Selecciona la opción **Run As** y luego la opción **Java Application**

### **Configurar PostMan**
- Se debe Registrar o Acceder a la aplicación de Postman.
- Luego ingresar en **File**>**Importar** 
- Seleccionar el archivo ubicado en la carpeta **postman** del proyecto

Este archivo tiene un formato .JSON el cual te cargara las 5 funciones que tiene la aplicación.


### **Uso del CreateTask (Crear Nueva Tarea)**

Para crear una nueva tarea, se debe enviar una solicitud ** POST**  al siguiente endpoint: http://localhost:8080/api/tasks/create

Allí se debe suministrar un Body-raw en formato JSON tal que así:

```json 
{ 
"description": "Lavar platos", 
"completed": false
"false", "priority": 2 
}
```
Este creara la tarea y le asignara un ID por autoincremental, que en este caso por ser la primera tarea, recibira el ID : 1

Asi sucesivamente se podran crear infinidad de tareas.

### **Uso del GetAllTasks (Obtener Todas Las Tareas)**

Para obtener todas las tareas, se debe enviar una solicitud ** GET**  al siguiente endpoint: 
http://localhost:8080/api/tasks

Con tan solo este paso la aplicación imprimira por pantalla todas las tareas ordenadas por prioridad y por ID.

```json 
{

"id": 2,

"description": "Lavar Casa",

"completed": false,

"priority": 1

},

{

"id": 1,

"description": "Lavar platos",

"completed": true,

"priority": 2

}
```

### **Uso del GetTaskById (Obtener Tarea Por ID)**

Para obtener una tarea en especifico, se debe enviar una solicitud ** GET**  al siguiente endpoint: 
http://localhost:8080/api/tasks/get/ + El ID que desea filtrar

Por ejemplo para buscar la tarea que creamos recientemente quedaria así : http://localhost:8080/api/tasks/get/1

Solo con ese paso la aplicación mostrara por pantalla la tarea asociada con el ID suministrado (1 en este caso)

```json 
{ 
 
{

"id": 1,

"description": "Lavar platos",

"completed": true,

"priority": 2

}

}
```

### **Uso del UpdateTask (Actualizar Tarea)**

Para actualizar una tarea, se debe enviar una solicitud **PUT** al siguiente endpoint:
http://localhost:8080/api/tasks/update/ + El ID que desea actualizar

Allí se debe suministrar un Body-raw en formato JSON especificando el atributo que se desea modificar:

http://localhost:8080/api/tasks/update/1

```json 
{ 
 
"completed": true

}
```

En este momento el estado pasara a True = Completado e imprimira por pantalla la tarea actualizada
```json 
{ 

"id": 1,

"description": "Lavar platos",

"completed": true,

"priority": 2

}
```

### **Uso del DeleteTask (Eliminar Tarea)**

Para eliminar una tarea, se debe enviar una solicitud **DELETE** al siguiente endpoint:
http://localhost:8080/api/tasks/delete/ + El ID que desea actualizar

Al enviar la solicitud el programa eliminara directamente la tarea de la base de datos

Si la eliminación es realizada con exito, saldra un mensaje por pantalla tal que así:
http://localhost:8080/api/tasks/delete/1

```json 
{ 

Tarea #1 eliminada con exito

}
```


#### **Gracias por revisar este proyecto. Espero que esta aplicación de lista de tareas sea útil para tus necesidades.** 

## Contacto 
Si tienes alguna pregunta, sugerencia o necesitas asistencia adicional, no dudes en ponerte en contacto: 
- 📌 **Autor**: [Santiago Restrepo](https://github.com/SantiagoRestrepo0512) 
- 📧 **Correo Electrónico**: santiago.restrepoa0512@gmail.com 
- 🔗 **Repositorio**: [GitHub Repositorio](https://github.com/SantiagoRestrepo0512/To-Do-List)
