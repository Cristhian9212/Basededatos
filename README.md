
# User Management App

## Descripción

Esta es una aplicación de gestión de usuarios desarrollada en Kotlin utilizando Jetpack Compose y Room. Permite registrar, listar, editar y eliminar usuarios con campos de nombre, apellido y edad. La aplicación también maneja la persistencia de datos, lo que permite que los registros se mantengan incluso cuando la aplicación se cierra o se gira el dispositivo.

## Características

- **Registro de Usuarios:** Permite registrar nuevos usuarios con nombre, apellido y edad.
- **Listar Usuarios:** Muestra una lista de usuarios registrados en la base de datos.
- **Editar Usuarios:** Permite modificar la información de un usuario existente.
- **Eliminar Usuarios:** Posibilidad de eliminar un usuario de la lista.
- **Persistencia de Datos:** Utiliza Room para almacenar los datos en una base de datos local, lo que garantiza que la información se mantenga incluso al cerrar la aplicación o girar el dispositivo.

## Requisitos

- **Android Studio** (Koala o superior)
- **Kotlin** (1.5 o superior)
- **Jetpack Compose** (1.0.0 o superior)
- **Room** (2.4.0 o superior)

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/user-management-app.git
   ```

2. Abre el proyecto en Android Studio.

3. Sincroniza el proyecto con Gradle.

4. Ejecuta la aplicación en un dispositivo físico o emulador.

## Uso

1. **Registrar Usuario:** Completa los campos de nombre, apellido y edad y presiona el botón "Registrar".
2. **Listar Usuarios:** Presiona el botón "Listar" para mostrar todos los usuarios registrados.
3. **Editar Usuario:** Presiona el icono de lápiz junto a un usuario para cargar sus datos en los campos y editarlos.
4. **Eliminar Usuario:** Presiona el icono de papelera junto a un usuario para eliminarlo de la lista.

## Estructura del Proyecto

- **Screen:** Contiene las pantallas y la lógica de la interfaz de usuario.
- **Model:** Define los modelos de datos (por ejemplo, `User`).
- **Repository:** Maneja las operaciones de acceso a datos (por ejemplo, `UserRepository`).

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y comitea (`git commit -am 'Añadir nueva característica'`).
4. Envía tus cambios (`git push origin feature/nueva-caracteristica`).
5. Crea un nuevo Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Cristhian Camilo Fernandez Castro  
Email: tu_email@example.com
