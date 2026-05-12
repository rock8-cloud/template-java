CREATE TABLE space_target (
    id                  BIGSERIAL PRIMARY KEY,
    name                VARCHAR(255)     NOT NULL,
    type                VARCHAR(100)     NOT NULL,
    distance_light_years DOUBLE PRECISION NOT NULL
);

INSERT INTO space_target (name, type, distance_light_years) VALUES
    ('Proxima Centauri',     'Červený trpaslík', 4.24),
    ('Andromedová galaxie',  'Spirální galaxie', 2537000),
    ('Mlhovina Orion',       'Emisní mlhovina',  1344);
