<h1>InventQ - Sistema de Gestión de Inventarios</h1>
InventQ es un sistema completo para la administración de inventarios de productos. Permite a los usuarios gestionar de manera eficiente el ciclo de vida de los productos, controlar las existencias, realizar actualizaciones y eliminar productos, así como generar informes detallados para facilitar la toma de decisiones.

<h2>Descripción del Proyecto</h2>
InventQ permite a las empresas llevar un registro detallado de sus inventarios y operaciones relacionadas, incluyendo productos, proveedores y movimientos de inventario. La aplicación está diseñada para ser escalable y eficiente, con un enfoque en la facilidad de uso y la integración de datos para análisis en tiempo real.

<h2>Características Principales</h2>
Gestión de Inventarios: Agregar, actualizar y eliminar productos. Gestionar los niveles de existencias, proveedores y movimientos de inventario.

Informes Dinámicos: Generar reportes sobre ventas, movimientos de inventario y proyecciones de stock.

Base de Datos: Almacenamiento de los datos en bases de datos SQL (SQL Server o MySQL).

Integración con Power BI: Exportación de datos en formato Excel o CSV para análisis y visualización de datos a través de Power BI.

<h2>Tecnología y Herramientas Utilizadas</h2>
Back-End (Java): Lógica de negocio para manejar las operaciones del sistema (crear, leer, actualizar, eliminar datos en la base de datos).

Base de Datos: SQL Server o MySQL para almacenar la información sobre productos, proveedores y movimientos de inventario.

Front-End: JavaFX para el diseño de la interfaz de usuario, con formularios y tablas interactivas.

Exportación de Datos: Exportación de datos a través de POWER BI, en el apartado "SQL Server".

Power BI: Para la creación de dashboards y análisis visual de los datos exportados.



<h2>Estructura del Proyecto</h2>
1. Back-End (Java)
Desarrolla la lógica de negocio y las APIs REST para manejar las operaciones del sistema (crear, leer, actualizar, eliminar datos en la base de datos). El backend será responsable de interactuar con la base de datos para almacenar y recuperar los datos de productos, proveedores, ventas y movimientos de inventario.

2. Base de Datos (SQL Server/MySQL)
Crea las tablas necesarias para almacenar las entidades del sistema, como productos, clientes, ventas, proveedores, etc. La base de datos se conecta directamente con el back-end utilizando JDBC (Java Database Connectivity) para permitir el acceso a los datos de manera eficiente.

3. Front-End (JavaFX)
Diseña la interfaz de usuario para interactuar con el sistema. Utiliza JavaFX para crear formularios de entrada de datos, tablas interactivas para mostrar la información y botones para realizar las operaciones del sistema. Ejemplos de funcionalidades incluyen registrar ventas, visualizar inventarios y actualizar productos.

4. Integración con Power BI
Exporta los datos necesarios desde la aplicación en el área de "Exportar Datos desde SQL Server" para que Power BI pueda importar fácilmente. De esta forma proceder a realizar la limpieza, carga y transformación de datos, y así mismo la realización del Análisis y su respectivo dashboard.
