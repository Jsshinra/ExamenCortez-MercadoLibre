ExamenCortez-MercadoLibre
Detector de Mutantes (Mutant Detector)
API REST desarrollada en Spring Boot que detecta secuencias de ADN mutante y mantiene estadísticas de las verificaciones en una base de datos H2 en memoria.

Endpoints
1. Detectar Mutante
POST /mutant

Envía una secuencia de ADN para su verificación.

Body:

JSON

{
  "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
Respuestas:

200 OK: Si el ADN pertenece a un mutante.

403 Forbidden: Si el ADN pertenece a un humano.

400 Bad Request: Si la entrada es inválida (matriz no cuadrada NxN o caracteres distintos a A, T, C, G).

2. Obtener Estadísticas
GET /stats

Devuelve las estadísticas de las verificaciones de ADN.

Respuesta:

JSON

{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}
Ejecutar Localmente
Prerrequisitos
Java 17+

Maven 3.9+

Ejecución directa con Maven
Bash

mvn spring-boot:run
O construir el JAR y ejecutar
Empaquetar la aplicación:

Bash

mvn clean package
Ejecutar el JAR:

Bash

java -jar target/mutants-0.0.1-SNAPSHOT.jar
Acceso a Base de Datos (H2)
Consola H2: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

User/Password: (Verificar en application.properties, usualmente sa / sin contraseña o password).

Tests y Cobertura de Código (Coverage)
Ejecutar la suite de pruebas y verificar cobertura:

Bash

mvn clean verify
Reporte JaCoCo: Se genera en target/site/jacoco/index.html.

Regla de Calidad: La compilación fallará (Build fails) si la cobertura de código es menor al 80%.

Docker
Construir y ejecutar con Docker
Construir la imagen:

Bash

docker build -t mutant-detector .
Correr el contenedor:

Bash

docker run --rm -p 8080:8080 mutant-detector
Despliegue en Render (Guía)
Opción A (Docker)
Conectar el repositorio en Render.

Seleccionar New Web Service.

Elegir entorno Docker.

El comando de inicio utilizará el Dockerfile incluido.

Asegurar que el puerto interno sea 8080.

Opción B (Java Nativo)
Elegir entorno Native Java.

Build Command:

Bash

mvn -DskipTests clean package
Start Command:

Bash

java -jar target/mutants-0.0.1-SNAPSHOT.jar
Runtime: Seleccionar Java 17.

Notas Técnicas y Algoritmo
Restricciones de Entrada: La matriz de ADN debe ser cuadrada (NxN) y solo debe contener las letras A, T, C, G.

Lógica de Detección:

Busca secuencias de 4 letras iguales de forma Horizontal, Vertical y en ambas Diagonales.

Optimización: El algoritmo implementa una "salida temprana" (early-exit); detiene la búsqueda tan pronto se encuentran más de 1 secuencia (es decir, 2 o más), marcando el ADN como mutante inmediatamente para ahorrar recursos.
