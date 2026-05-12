# Getting Started

The application runs on port **8080** and requires a PostgreSQL database.

## Environment Variables

Database connection is configured via **one of two approaches**:

### Option A — Single connection URL (recommended for managed services)

| Variable | Required | Example |
|---|---|---|
| `DATABASE_URL` | yes | `postgres://user:pass@host:5432/dbname?sslmode=require` |

The URL must use the `postgres://` or `postgresql://` scheme. The `sslmode` query parameter is optional.

### Option B — Individual variables

| Variable | Required | Default | Description |
|---|---|---|---|
| `DB_HOST` | no | `localhost` | Database host |
| `DB_PORT` | no | `5432` | Database port |
| `DB_NAME` | no | `spacedb` | Database name |
| `DB_USER` | no | `postgres` | Database user |
| `DB_PASSWORD` | no | `postgres` | Database password |
| `DB_SSL_MODE` | no | _(none)_ | SSL mode, e.g. `require` or `disable` |

`DATABASE_URL` takes precedence. If it is set, the individual `DB_*` variables are ignored.

## Running with Docker Compose

```bash
docker compose up
```

The compose file starts the application together with a PostgreSQL instance and sets `DATABASE_URL` automatically. No additional configuration is needed for local development.

## Running Standalone

```bash
# build
mvn package -DskipTests

# run — Option A
DATABASE_URL=postgres://postgres:postgres@localhost:5432/spacedb java -jar target/*.jar

# run — Option B
DB_HOST=localhost DB_USER=postgres DB_PASSWORD=secret java -jar target/*.jar
```