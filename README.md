# Agua Purificada Celica

Aplicación móvil Android para **Agua Purificada Celica**, orientada a facilitar la solicitud de productos, la gestión de pedidos y el acceso a información y servicios de la empresa en Celica y Macará.

La aplicación integra autenticación de usuarios, persistencia de datos con Firebase, consulta de configuración remota, ubicación mediante Google Maps, enlaces de contacto, información institucional y experiencias de realidad aumentada mediante efectos externos.

## Funcionalidades principales

- Pantalla inicial con animación de carga y aceptación de la política de privacidad.
- Menú principal con acceso a:
  - Solicitud de botellones y pedidos.
  - Información de la empresa mediante **Conócenos**.
  - Información de contacto mediante **Contáctanos**.
  - Ubicaciones y puntos de interés mediante **Encuéntranos**.
  - Experiencias de realidad aumentada.
- Selección de ciudad para pedidos:
  - Celica.
  - Macará.
- Consulta de disponibilidad del servicio de entrega desde Firebase Realtime Database.
- Registro e inicio de sesión con Firebase Authentication.
- Almacenamiento de datos en Firebase Realtime Database y Firebase Storage.
- Uso de Google Maps y servicios de ubicación.
- Solicitud de permisos de cámara para las funciones relacionadas con realidad aumentada.
- Animaciones Lottie para la pantalla de inicio y animaciones XML para la interfaz.
- Política de privacidad incluida dentro de la aplicación.

## Stack tecnológico

- **Lenguaje:** Java.
- **Plataforma:** Android.
- **Build system:** Gradle.
- **Compile SDK:** Android API 34.
- **Minimum SDK:** Android API 24.
- **Target SDK:** Android API 34.
- **Application ID:** `com.xavker.aguapurificadacelica`.
- **Versión actual:** `1.0.1` (`versionCode 6`).
- **Interfaz:** XML, AndroidX, Material Components y ConstraintLayout.

### Dependencias relevantes

- AndroidX AppCompat, Core, CoordinatorLayout y DrawerLayout.
- Material Components.
- Firebase Authentication.
- Firebase Realtime Database.
- Firebase Storage.
- Firebase Cloud Messaging.
- Google Play Services Maps.
- Google Play Services Location.
- Retrofit con convertidores Gson y Scalars.
- Glide y Picasso para carga de imágenes.
- Lottie para animaciones.
- CircleImageView y ShapeOfView para componentes visuales.

## Estructura del proyecto

```text
AguaPurificadaCelica/
├── app/
│   ├── build.gradle                 # Configuración del módulo Android
│   ├── google-services.json         # Configuración de Firebase para Android
│   ├── proguard-rules.pro           # Reglas de ofuscación y reducción
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml  # Actividades, permisos y Google Maps
│       │   ├── assets/              # Animaciones Lottie JSON
│       │   ├── java/com/example/aguapurificadacelica/
│       │   │   └── activities/
│       │   │       ├── Home/        # Splash, pantalla principal y pedidos
│       │   │       ├── Conocenos/   # Información institucional y adaptadores
│       │   │       ├── Contactanos/ # Contacto con la empresa
│       │   │       ├── Encuentranos/ # Ubicaciones y mapas
│       │   │       ├── RealidadVirtual/ # Acceso a efectos de realidad aumentada
│       │   │       ├── client/      # Registro de clientes
│       │   │       ├── models/      # Modelos de datos
│       │   │       ├── providers/   # Configuración y proveedores Firebase
│       │   │       └── includes/    # Componentes auxiliares
│       │   └── res/
│       │       ├── anim/            # Animaciones de transición
│       │       ├── drawable*/       # Imágenes, iconos y recursos gráficos
│       │       ├── layout/          # Interfaces XML de las actividades
│       │       ├── menu/            # Menús de la aplicación
│       │       ├── mipmap*/          # Iconos de aplicación por densidad
│       │       ├── raw/             # Recursos sin procesar
│       │       ├── values/          # Colores, textos, estilos y temas
│       │       └── xml/             # Backup y extracción de datos
│       └── test/                    # Base para pruebas locales
├── build.gradle                     # Configuración Gradle raíz
├── gradle.properties                # Opciones globales de Gradle y AndroidX
├── gradlew                          # Gradle Wrapper para Linux/macOS
├── gradlew.bat                      # Gradle Wrapper para Windows
├── settings.gradle                  # Repositorios y módulo incluido
└── politicadeprivacidad             # Archivo auxiliar de política de privacidad
```

## Flujo principal de la aplicación

1. `Splash` muestra la política de privacidad la primera vez que se inicia la aplicación y guarda la decisión mediante `SharedPreferences`.
2. Después de la pantalla de carga, se abre `Home`.
3. `Home` consulta la configuración `delivery` en Firebase mediante `ConfigProvaider`.
4. Según la disponibilidad del servicio, el usuario puede acceder al flujo de pedidos o a la sección de realidad aumentada.
5. Los botones del menú abren las actividades `Conocenos`, `Contactanos`, `Encuentranos` y `Realidad`.
6. Para los pedidos, se selecciona la ciudad y se abre `DialogodePedido` con los datos del cliente y del pedido.

## Requisitos

- Android Studio compatible con Android Gradle Plugin 8.0.2.
- JDK compatible con la versión de Gradle y Android Studio instalada.
- Android SDK Platform 34.
- Android SDK Build-Tools configurado.
- Un dispositivo o emulador con Android 7.0 (API 24) o superior.
- Proyecto de Firebase configurado para la aplicación.
- Clave válida de Google Maps.
- Acceso a Internet para Firebase, mapas, autenticación y servicios externos.

## Configuración de Firebase y Google Maps

1. Crea o selecciona un proyecto en Firebase.
2. Registra la aplicación Android con el identificador:

   ```text
   com.xavker.aguapurificadacelica
   ```

3. Descarga `google-services.json` y colócalo dentro de `app/`.
4. Habilita los servicios que utiliza la aplicación:
   - Firebase Authentication.
   - Firebase Realtime Database.
   - Firebase Storage.
   - Firebase Cloud Messaging, si se requieren notificaciones.
5. Configura una clave de Google Maps y verifica el recurso `google_maps_key` en `app/src/main/res/values/google_maps_api.xml`.
6. Configura en Realtime Database los valores requeridos por la aplicación, incluido `delivery`, usado para determinar si el servicio de entrega está activo.

> **Seguridad:** `google-services.json` y las claves de mapas/Firebase deben revisarse antes de publicar el repositorio. No expongas credenciales privadas, reglas inseguras de Firebase ni claves sin restricciones.

## Compilar y ejecutar

### Desde Android Studio

1. Clona el repositorio.
2. Abre la carpeta raíz `AguaPurificadaCelica` en Android Studio.
3. Espera a que Gradle sincronice el proyecto.
4. Configura `google-services.json` y Google Maps.
5. Conecta un dispositivo Android o inicia un emulador.
6. Ejecuta la configuración `app`.

### Desde la terminal

Linux/macOS:

```bash
./gradlew assembleDebug
./gradlew installDebug
```

Windows:

```bat
gradlew.bat assembleDebug
gradlew.bat installDebug
```

El APK de depuración se genera normalmente en:

```text
app/build/outputs/apk/debug/app-debug.apk
```

Para limpiar los artefactos de compilación:

```bash
./gradlew clean
```

## Pruebas

El proyecto incluye la configuración base para pruebas locales y pruebas instrumentadas con JUnit, AndroidX Test y Espresso.

```bash
./gradlew test
./gradlew connectedAndroidTest
```

`connectedAndroidTest` requiere un dispositivo o emulador conectado y autorizado mediante ADB.

## Permisos utilizados

El manifiesto declara permisos y características para:

- Cámara, usada por las funcionalidades relacionadas con realidad aumentada.
- Ubicación aproximada y precisa, usada por las funciones de mapas y localización.
- Identificador publicitario de Google Play Services.

La aplicación solicita el permiso de cámara durante el flujo inicial o desde `Home` cuando corresponde.

## Notas de mantenimiento

- La consulta de `delivery` en `Home.deliveryOn()` es asíncrona; cualquier decisión de navegación debería ejecutarse después de recibir el resultado de Firebase.
- Conviene revisar la configuración de versiones Gradle: el archivo raíz conserva una dependencia histórica de `com.android.tools.build:gradle:3.5.2`, mientras que el bloque de plugins declara Android Gradle Plugin `8.0.2`.
- Antes de una compilación de producción, verifica que todas las dependencias antiguas sean compatibles con Android API 34.
- Revisa los textos temporales o de ejemplo en los recursos, como `Hello Wolrd`, y corrige errores ortográficos antes de publicar.
- Verifica que el objeto de carga usado por `LoginActivity` esté inicializado antes de llamar a `mDialog.show()`.
- Usa reglas de seguridad de Firebase apropiadas para proteger usuarios, pedidos, almacenamiento y configuraciones.

## Política de privacidad

La aplicación muestra una política de privacidad durante el primer inicio y también contiene recursos asociados en `app/src/main/res/layout/activity_politicadedatos.xml` y `app/src/main/res/values/strings.xml`.

Antes de distribuir la aplicación, revisa que la política describa de forma exacta el uso de autenticación, ubicación, cámara, pedidos, Firebase, almacenamiento y servicios externos.

## Autor y contacto

- **Proyecto:** Agua Purificada Celica
- **Desarrollador indicado en la aplicación:** Xavker
- **Correo de contacto incluido en la política:** `aguacelica@gmail.com`

## Licencia

No se encontró un archivo de licencia en la raíz del repositorio. Define una licencia antes de distribuir o reutilizar el código públicamente.
