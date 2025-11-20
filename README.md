# Mutant Detector (MercadoLibre Challenge)

Spring Boot API that detects mutant DNA sequences and keeps stats in H2.

## Endpoints

- `POST /mutant` body:
  `{ "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }`
  - 200 OK if mutant
  - 403 Forbidden if human
  - 400 Bad Request on invalid input (non NxN or invalid chars)

- `GET /stats` response:
  `{ "count_mutant_dna": 40, "count_human_dna": 100, "ratio": 0.4 }`

## Run locally

Prereqs: Java 17+, Maven 3.9+

```bash
mvn spring-boot:run
```

Or build a jar and run:

```bash
mvn clean package
java -jar target/mutants-0.0.1-SNAPSHOT.jar
```

H2 console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`)

## Tests and Coverage

```bash
mvn clean verify
```

Generates JaCoCo report at `target/site/jacoco/index.html`. Build fails if coverage < 80%.

## Docker

Build and run with Docker:

```bash
docker build -t mutant-detector .
docker run --rm -p 8080:8080 mutant-detector
```

## Deploy on Render (guide)

Option A (Docker): Connect repo -> New Web Service -> Docker. Start command uses Dockerfile. Port 8080.

Option B (Native Java):
- Build Command: `mvn -DskipTests clean package`
- Start Command: `java -jar target/mutants-0.0.1-SNAPSHOT.jar`
- Runtime: Java 17

## Notes

- Input must be NxN, only letters `A,T,C,G`.
- Detection searches horizontal, vertical, and both diagonals; early-exits after 2 sequences.

"# ExamenCortez-MercadoLibre" 
