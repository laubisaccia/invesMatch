# InvesMatch

## Introducción:
**InvesMatch** es una aplicación desarrollada en **Kotlin** que permite al usuario comparar dos inversiones y determinar cuál de ellas tiene un mejor rendimiento. 

La aplicación solicita al usuario los siguientes datos:
- Monto a invertir
- Cantidad de días de inversión
- TNA (Tasa Nominal Anual)
- Nombre de la entidad para ambas inversiones

Posteriormente, realiza el cálculo y devuelve el **ROI** (Retorno sobre la inversión) de cada una.  
Para su desarrollo, se utilizaron las siguientes tecnologías:
- **Kotlin**
- **Android SDK**
- **Git**

## Instalación:
El repositorio puede clonarse a través de **Android Studio**:

1. Abrir Android Studio
2. Ir a `New` => `Project from Version Control` => `Git`
3. Ingresar la URL del repositorio:


## Detalle de aplicación:

### Estructura de Carpetas Principales:
- `app/src/main/java/com/example/invesmatch`: contiene todas las **activities** (archivos `.kt`).
- `app/src/main/res/drawable`: contiene archivos `.xml` que definen formas, colores de algunos elementos y el logo.
- `app/src/main/res/layout`: contiene archivos `.xml` de las **activities**.
- `app/src/main/res/values`: contiene archivos `.xml` de recursos definidos (colores, estilos, temas, strings).

### Activities:
- **MainActivity**: Pantalla de solicitud de datos. Solicita al usuario su nombre, apellido y dirección de correo electrónico.
- **InvestorProfileActivity**: Pantalla en la que el usuario responde algunas preguntas para que la aplicación le asigne un perfil de inversor.
- **UserProfileActivity**: Pantalla que muestra el perfil del usuario, incluyendo su perfil de inversor.
- **InvestmentComparisonActivity (core)**: Pantalla de comparación de inversiones. El usuario ingresa datos de ambas inversiones.
- **ResultActivity**: Pantalla que muestra al usuario los resultados de la comparación de inversiones realizada.
- **TermsAndConditionsActivity**: Pantalla que muestra los términos y condiciones.
- **HistoryActivity**: Pantalla que muestra al usuario un historial de las últimas 5 comparaciones realizadas.

### Manejo de datos:
El guardado y lectura de datos se realiza a través de **SharedPreferences**, lo que permite almacenar y recuperar información persistente del usuario, como sus datos de perfil y comparaciones anteriores.
