# 🌲 Sistema de Gestión "Good Wood"

> Sistema integral de administración para empresas de carpintería y servicios | Java + POO

Aplicación de escritorio desarrollada en **Java** que permite gestionar el flujo completo de un negocio de servicios: desde el registro de usuarios y catálogos de clientes, hasta la creación de órdenes de trabajo y facturación.

Este proyecto forma parte de mi portafolio profesional y demuestra la implementación de una **arquitectura en capas** (Gestores/Modelos) sin depender de librerías externas.

## 🚀 Módulos del Sistema

### 1. 👥 Gestión de Usuarios
Control de acceso y roles dentro del sistema.
* **Roles soportados:** Administrador, Cliente y Carpintero.
* **Seguridad:** Validación de duplicados (Nickname/Correo) y control de dependencias antes de inactivar usuarios (ej. el sistema impide borrar un carpintero si tiene órdenes activas).

### 2. 🗂️ Catálogos Maestros
Administración centralizada de las entidades del negocio:
* **Clientes:** Base de datos de contacto y ubicación.
* **Servicios:** Catálogo de trabajos ofrecidos con requisitos específicos.
* **Proyectos:** Gestión de obras con asignación de materiales e identificación única.
* **Órdenes de Trabajo:** Asignación de servicios a carpinteros con estimación de tiempo.

### 3. 🧾 Facturación y Reportes
Sistema de cierre de ventas.
* **Generación de Facturas:** Vincula un Cliente con un Servicio prestado.
* **Control de Estado:** Capacidad de anular facturas y visualizar historial en tiempo real.

## 🛠️ Tecnologías y Conceptos Clave
* **Lenguaje:** Java (JDK 8+)
* **GUI:** Java Swing (`JOptionPane`) para una interfaz interactiva.
* **Persistencia:** Estructuras de datos dinámicas y manejo de arreglos de objetos.
* **Paradigma:** Programación Orientada a Objetos (Encapsulamiento, Polimorfismo y Abstracción).

## 📂 Estructura del Proyecto
El código está organizado en el paquete `projectoavance1` para mantener el orden:
* `Main.java`: Punto de entrada y menú principal.
* `GestorUsuarios.java` / `GestorCatalogos.java`: Lógica de negocio y validaciones.
* `Cliente.java`, `Servicio.java`, etc.: Modelos de datos (POJOs).

## 💻 Cómo ejecutar este proyecto

### Desde VS Code / IDEs
1. Abre la carpeta del proyecto.
2. Busca el archivo `Main.java` dentro del paquete `projectoavance1`.
3. Ejecuta el archivo (**Run Java**).

### Credenciales de Prueba (Opcional)
El sistema permite crear usuarios desde cero, pero puedes iniciar registrando un usuario "Administrador" para gestionar el resto.

---
Desarrollado por **Esteban Gamboa**.
[Ver mi perfil en GitHub](https://github.com/EstebanGamboaR)