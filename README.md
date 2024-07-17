# Gestión de Usuarios con Compose for Desktop

## Descripción
Esta es una aplicación de escritorio desarrollada con Compose for Desktop que implementa un CRUD para la gestión de usuarios. La aplicación se conecta a una base de datos PostgreSQL para realizar operaciones de inserción, actualización, eliminación y consulta de datos de usuarios. La interfaz de usuario permite la entrada de datos de identificación, nombres, apellidos y correo electrónico, así como la búsqueda y visualización de usuarios existentes en la base de datos.

## Características
- **Agregar Usuario:** Inserta un nuevo usuario en la base de datos.
- **Modificar Usuario:** Actualiza la información de un usuario existente.
- **Eliminar Usuario:** Elimina un usuario de la base de datos.
- **Buscar Usuario:** Filtra y muestra usuarios basados en un criterio de búsqueda.
- **Restablecer Formulario:** Limpia los campos del formulario para ingresar un nuevo usuario.

## Requisitos
- JDK 11 o superior
- PostgreSQL

## Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/compose-desktop-user-management.git
   cd compose-desktop-user-management
  

2. Configura la base de datos PostgreSQL. Puedes usar el siguiente script para crear la base de datos y la tabla necesaria:

    [Script PostgreSQL](https://drive.google.com/file/d/1erqgtvrIYL1v1DMLezRGG4zbE0Esrb6b/view?usp=sharing)


4. Modifica el archivo `Database.kt` para que se ajuste a la configuración de tu base de datos PostgreSQL:
   ```kotlin
   // Database.kt
   val url = "jdbc:postgresql://localhost:5432/tu_base_de_datos"
   val user = "tu_usuario"
   val password = "tu_contraseña"
   ```

5. Ejecuta la aplicación:
   ```bash
   ./gradlew run
   ```

## Uso
- **Agregar Usuario:** Completa los campos de identificación, nombres, apellidos y correo, y haz clic en "Agregar".
- **Modificar Usuario:** Completa los campos de identificación, nombres, apellidos y correo con la información actualizada, y haz clic en "Modificar".
- **Eliminar Usuario:** Completa el campo de identificación y haz clic en "Eliminar".
- **Buscar Usuario:** Ingresa un nombre o parte del nombre en el campo de búsqueda y haz clic en "Buscar".

## Capturas de Pantalla
![image](https://github.com/user-attachments/assets/942efd40-ca07-4007-98de-123fbe169df7)


## Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue para discutir cualquier cambio que te gustaría realizar.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
