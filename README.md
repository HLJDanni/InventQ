<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>InventQ - Sistema de Gestión de Inventarios</title>
</head>
<body>
    <h1>InventQ - Sistema de Gestión de Inventarios</h1>

    <p><strong>InventQ</strong> es un sistema completo para la administración de inventarios de productos. Permite a los usuarios gestionar de manera eficiente el ciclo de vida de los productos, controlar las existencias, realizar actualizaciones y eliminar productos, así como generar informes detallados para facilitar la toma de decisiones.</p>

    <h2>Descripción del Proyecto</h2>
    <p>InventQ permite a las empresas llevar un registro detallado de sus inventarios y operaciones relacionadas, incluyendo productos, proveedores y movimientos de inventario. La aplicación está diseñada para ser escalable y eficiente, con un enfoque en la facilidad de uso y la integración de datos para análisis en tiempo real.</p>

    <h3>Características Principales</h3>
    <ul>
        <li><strong>Gestión de Inventarios:</strong> Agregar, actualizar y eliminar productos. Gestionar los niveles de existencias, proveedores y movimientos de inventario.</li>
        <li><strong>Informes Dinámicos:</strong> Generar reportes sobre ventas, movimientos de inventario y proyecciones de stock.</li>
        <li><strong>Base de Datos:</strong> Almacenamiento de los datos en bases de datos SQL (SQL Server o MySQL).</li>
        <li><strong>Integración con Power BI:</strong> Exportación de datos en formato Excel o CSV para análisis y visualización de datos a través de Power BI.</li>
    </ul>

    <h3>Tecnología y Herramientas Utilizadas</h3>
    <ul>
        <li><strong>Back-End (Java):</strong> Lógica de negocio y desarrollo de APIs REST para manejar las operaciones del sistema (crear, leer, actualizar, eliminar datos en la base de datos).</li>
        <li><strong>Base de Datos:</strong> SQL Server o MySQL para almacenar la información sobre productos, proveedores y movimientos de inventario.</li>
        <li><strong>Front-End:</strong> JavaFX para el diseño de la interfaz de usuario, con formularios y tablas interactivas.</li>
        <li><strong>Exportación de Datos:</strong> OpenCSV para generar archivos CSV que se pueden importar a Power BI.</li>
        <li><strong>Power BI:</strong> Para la creación de dashboards y análisis visual de los datos exportados.</li>
    </ul>

    <h2>Estructura del Proyecto</h2>

    <h3>1. Back-End (Java)</h3>
    <p>Desarrolla la lógica de negocio y las APIs REST para manejar las operaciones del sistema (crear, leer, actualizar, eliminar datos en la base de datos). El backend será responsable de interactuar con la base de datos para almacenar y recuperar los datos de productos, proveedores, ventas y movimientos de inventario.</p>

    <h3>2. Base de Datos (SQL Server/MySQL)</h3>
    <p>Crea las tablas necesarias para almacenar las entidades del sistema, como productos, clientes, ventas, proveedores, etc. La base de datos se conecta directamente con el back-end utilizando JDBC (Java Database Connectivity) para permitir el acceso a los datos de manera eficiente.</p>

    <h3>3. Front-End (JavaFX)</h3>
    <p>Diseña la interfaz de usuario para interactuar con el sistema. Utiliza JavaFX para crear formularios de entrada de datos, tablas interactivas para mostrar la información y botones para realizar las operaciones del sistema. Ejemplos de funcionalidades incluyen registrar ventas, visualizar inventarios y actualizar productos.</p>

    <h3>4. Integración con Power BI</h3>
    <p>Exporta los datos necesarios desde la aplicación en formato Excel o CSV que Power BI pueda importar fácilmente. Para la exportación de datos, se utiliza la biblioteca OpenCSV en el front-end de JavaFX. Alternativamente, si es posible, se puede configurar una API para integrar directamente los datos con Power BI, permitiendo la visualización dinámica de la información.</p>

    <h3>Ejemplo de Flujo para JavaFX y Power BI</h3>
    <ol>
        <li><strong>JavaFX para Captura y Visualización de Datos:</strong> La aplicación permite a los usuarios registrar y visualizar información a través de ventanas de entrada y tablas interactivas (por ejemplo, <code>TableView</code>).</li>
        <li><strong>Exportación de Datos:</strong> Desde la interfaz de JavaFX, se puede exportar la información a un archivo CSV mediante un botón de exportación. Este archivo puede ser luego importado a Power BI.</li>
        <li><strong>Conexión a Power BI:</strong> Los datos exportados en CSV pueden ser importados manualmente en Power BI, o en su defecto, se puede configurar una API para que Power BI extraiga los datos directamente.</li>
        <li><strong>Dashboard de Power BI:</strong> En Power BI, se pueden diseñar gráficos y tablas dinámicas para visualizar los datos de ventas, inventarios y desempeño de forma interactiva.</li>
    </ol>
</body>
</html>
