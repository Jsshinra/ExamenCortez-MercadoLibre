# Detector de Mutantes (Desafio MercadoLibre)

API Spring Boot para detectar secuencias de ADN mutante y almacenar estadísticas en H2.

## Endpoints

- `POST /mutant` cuerpo de ejemplo:
  `{ "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }`
  - 200 OK si es mutante
  - 403 Forbidden si es humano
  - 400 Bad Request si el input no es NxN o trae caracteres distintos de `A,T,C,G`

- `GET /stats` respuesta:
  `{ "count_mutant_dna": 40, "count_human_dna": 100, "ratio": 0.4 }`
  - `ratio` = `count_mutant_dna / count_human_dna` (si no hay humanos devuelve el número de mutantes)

## Como ejecutar localmente

Prerrequisitos: Java 17+ y Gradle 8.x instalado.

```bash
./gradlew bootRun
```

O bien empaquetar y correr el JAR:

```bash
./gradlew clean bootJar
java -jar build/libs/mutants-0.0.1-SNAPSHOT.jar
```

Consola H2: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`).

## Pruebas y cobertura

```bash
./gradlew clean test jacocoTestReport jacocoTestCoverageVerification
```

Genera el reporte de JaCoCo en `build/reports/jacoco/html/index.html`. El build falla si la cobertura baja de 80%.

## Docker

Construir y ejecutar con Docker:

```bash
docker build -t mutant-detector .
docker run --rm -p 8080:8080 mutant-detector
```

## Despliegue en Render

Opcion A (Docker): conectar repo -> New Web Service -> Docker. El start usa el Dockerfile. Puerto 8080.

Opcion B (Java nativo):
- Build Command: `./gradlew -x test clean bootJar`
- Start Command: `java -jar build/libs/mutants-0.0.1-SNAPSHOT.jar`
- Runtime: Java 17

## Notas

- El ADN debe venir en matriz NxN y solo con letras `A,T,C,G`.
- La deteccion busca en horizontal, vertical y diagonales, y corta cuando encuentra 2 secuencias mutantes.
