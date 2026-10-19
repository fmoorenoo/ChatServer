<div align="center">

# ChatServer

### Chat multicliente en tiempo real mediante sockets TCP y Java

**Java 21 · Arquitectura cliente-servidor · Programación concurrente**

![Java](https://img.shields.io/badge/Java-1A1B26?style=for-the-badge&logo=openjdk&logoColor=E46B79)
![TCP](https://img.shields.io/badge/TCP_Sockets-1A1B26?style=for-the-badge&logo=socketdotio&logoColor=E46B79)
![Threads](https://img.shields.io/badge/Multithreading-1A1B26?style=for-the-badge&logo=openjdk&logoColor=E46B79)
![NetBeans](https://img.shields.io/badge/Apache_NetBeans-1A1B26?style=for-the-badge&logo=apachenetbeanside&logoColor=E46B79)

[![Estado](https://img.shields.io/badge/ESTADO-Proyecto_de_práctica-712B39?style=flat-square)](#estado-del-proyecto)
[![Interfaz](https://img.shields.io/badge/INTERFAZ-Consola-22252C?style=flat-square)](#funcionamiento)
[![Código](https://img.shields.io/badge/REPOSITORIO-Código_fuente-22252C?style=flat-square)](https://github.com/fmoorenoo/ChatServer)

</div>

---

## Sobre el proyecto

**ChatServer** es una aplicación de mensajería de consola desarrollada en **Java** para practicar **comunicación mediante sockets TCP, arquitectura cliente-servidor y concurrencia**.

El repositorio contiene dos proyectos independientes: un **servidor** que escucha conexiones en el puerto `9876` y un **cliente** que se conecta a `localhost`, envía mensajes y recibe los publicados por otros participantes.

> Es un ejercicio de comunicación en red, no una plataforma de mensajería preparada para producción.

## Funcionalidades

- **Conexiones TCP:** servidor basado en `ServerSocket` y clientes mediante `Socket`.
- **Varios usuarios:** el servidor crea un hilo por conexión para gestionar clientes simultáneos.
- **Identificación básica:** al conectarse, cada usuario introduce un nombre visible en el chat.
- **Mensajes en tiempo real:** el servidor retransmite los textos a los demás clientes conectados.
- **Avisos de actividad:** notificaciones cuando un usuario entra o sale de la conversación.
- **Recepción concurrente:** cada cliente dispone de un hilo independiente para escuchar nuevos mensajes.
- **Consola:** lectura y escritura de mensajes sin interfaz gráfica ni dependencias externas.

Estas capacidades se han identificado mediante una revisión estática de los archivos Java. No se han ejecutado pruebas de comunicación durante esta revisión.

## Tecnologías

| Área | Tecnologías |
| --- | --- |
| **Lenguaje** | Java 21 (configuración de los proyectos) |
| **Red** | TCP · `ServerSocket` · `Socket` |
| **Concurrencia** | `Thread` · `Runnable` |
| **Colección compartida** | `CopyOnWriteArrayList` |
| **Entrada y salida** | `BufferedReader` · `PrintWriter` · `Scanner` |
| **Herramientas** | Apache NetBeans · Ant |
| **Interfaz** | Terminal / consola |

## Arquitectura

```text
ChatServer/
├── ChatServer/
│   ├── src/chatserver/
│   │   └── ChatServer.java       # Servidor y conexiones de usuarios
│   ├── nbproject/               # Configuración de NetBeans
│   └── build.xml                # Proyecto Ant
└── ChatClient/
    ├── src/chatclient/
    │   └── ChatClient.java       # Cliente TCP y recepción de mensajes
    ├── nbproject/               # Configuración de NetBeans
    └── build.xml                # Proyecto Ant
```

### Flujo de mensajes

```text
Cliente A ──TCP──┐
                 │
Cliente B ──TCP──┼──> Servidor :9876 ──> Broadcast al resto
                 │
Cliente C ──TCP──┘
```

El servidor mantiene una lista de clientes activos y utiliza un `ChatThread` por conexión. Cada mensaje recibido se reenvía a **todos los demás participantes**, excluyendo al emisor.

## Funcionamiento

1. Inicia el servidor y déjalo escuchando en el puerto `9876`.
2. Ejecuta una o varias instancias de cliente.
3. Introduce el nombre de usuario cuando el servidor lo solicite.
4. Escribe mensajes en la terminal del cliente; los demás clientes conectados los recibirán.
5. Al desconectarse un usuario, el servidor notifica su salida a los participantes restantes.

**Configuración predeterminada:** ambos programas usan `localhost:9876`. Para conectar dispositivos diferentes es necesario adaptar la dirección del servidor en `ChatClient.java` y comprobar la conectividad y el cortafuegos.

## Instalación y ejecución

**Requisito:** JDK 21, de acuerdo con `javac.source` y `javac.target` en los archivos de configuración.

### Opción 1 · Apache NetBeans

1. Clona el repositorio:

   ```bash
   git clone https://github.com/fmoorenoo/ChatServer.git
   cd ChatServer
   ```

2. Abre **ChatServer/** y **ChatClient/** como dos proyectos independientes en NetBeans.
3. Ejecuta primero el proyecto del servidor.
4. Inicia una o varias instancias del proyecto cliente.

### Opción 2 · Terminal

Desde la raíz del repositorio, compila el servidor y el cliente:

```bash
javac -d out/server ChatServer/src/chatserver/ChatServer.java
javac -d out/client ChatClient/src/chatclient/ChatClient.java
```

Inicia el servidor en una terminal:

```bash
java -cp out/server chatserver.ChatServer
```

En otra terminal, inicia un cliente:

```bash
java -cp out/client chatclient.ChatClient
```

Para simular varios usuarios, ejecuta el comando del cliente en terminales adicionales. Estas instrucciones derivan de la estructura del código; no se ha verificado una compilación real durante la auditoría.

## Estado del proyecto

**Proyecto de práctica de redes y programación concurrente.**

### Consideraciones técnicas

- **Sin cifrado:** la comunicación se realiza mediante TCP sin TLS; no conviene transmitir información sensible.
- **Identificación no verificada:** el servidor acepta el nombre introducido por el cliente, sin autenticación de usuarios.
- **Sin historial:** los mensajes no se almacenan en una base de datos.
- **Puerto fijo:** cliente y servidor utilizan el puerto `9876` definido directamente en el código.
- **Mantenimiento del repositorio:** existen archivos compilados en `build/` y configuraciones privadas de NetBeans bajo `nbproject/private/`; conviene excluirlos del control de versiones en una futura limpieza.

## Autor

**Fernando Montelongo Moreno** · Full Stack Developer · DAM + DAW

[![GitHub](https://img.shields.io/badge/GitHub-fmoorenoo-22252C?style=flat-square&logo=github)](https://github.com/fmoorenoo)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Perfil_profesional-712B39?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/fernando-montelongo/)

---

<div align="center">
  <sub>ChatServer · Comunicación TCP y concurrencia con Java</sub>
</div>
