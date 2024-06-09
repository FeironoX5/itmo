-- Diagram: https://app.eraser.io/workspace/a1ddOea1jwJJCEwztSbC?elements=0cxbgUifAu9PAt3fmwSvMw

BEGIN;

-- AUTH
CREATE TABLE Users
(
    id             SERIAL PRIMARY KEY,
    username       VARCHAR(90) UNIQUE,
    hashedPassword VARCHAR(90),
    createdAt      TIMESTAMPTZ DEFAULT NOW(),
);

-- ROCKET
CREATE TABLE Rockets
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(90) UNIQUE,
    manufacturer  VARCHAR(90),
    originCountry VARCHAR(90),
    createdAt     TIMESTAMPTZ DEFAULT NOW(),
    FOREIGN KEY (createdBy) REFERENCES Users (id)
);

-- Assembly Components
CREATE TABLE NoseCones
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(90) UNIQUE,
    width     DOUBLE PRECISION,
    height    DOUBLE PRECISION,
    weight    DOUBLE PRECISION,
    material  VARCHAR(90),
    createdAt TIMESTAMPTZ DEFAULT NOW(),
    FOREIGN KEY (createdBy) REFERENCES Users (id),
    FOREIGN KEY (rocketId) REFERENCES Rockets (id)
);

CREATE TABLE Stages
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(90) UNIQUE,
    width     DOUBLE PRECISION,
    height    DOUBLE PRECISION,
    weight    DOUBLE PRECISION,
    material  VARCHAR(90),
    createdAt TIMESTAMPTZ DEFAULT NOW(),
    FOREIGN KEY (createdBy) REFERENCES Users (id),
    FOREIGN KEY (rocketId) REFERENCES Rockets (id)
);

-- Body Components
CREATE TABLE BodyComponents
(
    id SERIAL PRIMARY KEY
);

CREATE TABLE BodyTubes
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(90) UNIQUE,
    width         DOUBLE PRECISION,
    height        DOUBLE PRECISION,
    weight        DOUBLE PRECISION,
    material      VARCHAR(90),
    wallThickness DOUBLE PRECISION,
    rotation      DOUBLE PRECISION,
    createdAt     TIMESTAMPTZ DEFAULT NOW(),
    FOREIGN KEY (createdBy) REFERENCES Users (id),
    FOREIGN KEY (stageId) REFERENCES Stages (id)
);

CREATE TABLE Transitions
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(90) UNIQUE,
    width         DOUBLE PRECISION,
    height        DOUBLE PRECISION,
    weight        DOUBLE PRECISION,
    material      VARCHAR(90),
    innerDiameter DOUBLE PRECISION,
    shape         VARCHAR(90),
    createdAt     TIMESTAMPTZ DEFAULT NOW(),
    FOREIGN KEY (createdBy) REFERENCES Users (id),
    FOREIGN KEY (stageId) REFERENCES Stages (id)
);

-- Inner Components
CREATE TABLE InnerEngine
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(90) UNIQUE,
    width         DOUBLE PRECISION,
    height        DOUBLE PRECISION,
    weight        DOUBLE PRECISION,
    material      VARCHAR(90),
    wallThickness DOUBLE PRECISION,
    vectorX       DOUBLE PRECISION,
    vectorY       DOUBLE PRECISION,
    vectorZ       DOUBLE PRECISION,
    velocity      DOUBLE PRECISION,
    createdAt     TIMESTAMPTZ DEFAULT NOW(),
    FOREIGN KEY (createdBy) REFERENCES Users (id),
    FOREIGN KEY (bodyId) REFERENCES BodyComponents (id)
);

CREATE TABLE MassComponents
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(90) UNIQUE,
    width     DOUBLE PRECISION,
    height    DOUBLE PRECISION,
    weight    DOUBLE PRECISION,
    material  VARCHAR(90),
    createdAt TIMESTAMPTZ DEFAULT NOW(),
    FOREIGN KEY (createdBy) REFERENCES Users (id),
    FOREIGN KEY (bodyId) REFERENCES BodyComponents (id)
);

CREATE TABLE Parachutes
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(90) UNIQUE,
    width          DOUBLE PRECISION,
    height         DOUBLE PRECISION,
    weight         DOUBLE PRECISION,
    material       VARCHAR(90),
    weightCapacity DOUBLE PRECISION,
    createdAt      TIMESTAMPTZ DEFAULT NOW(),
    FOREIGN KEY (createdBy) REFERENCES Users (id),
    FOREIGN KEY (bodyId) REFERENCES BodyComponents (id)
);

END;