DROP TABLE IF EXISTS cars_2_contractors;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS contractors;

CREATE TABLE cars (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    brand VARCHAR(128) NOT NULL, --CarBrand enum (Audi, BMW, Ford)
    model VARCHAR(128) NULL,
    productionYear INTEGER NOT NULL,
    color VARCHAR(64) NULL, --CarColor enum (Yellow, Black, Green)
    vin VARCHAR(64) NULL,
    createdAt DATETIME NOT NULL,
    updatedAt DATETIME NOT NULL
);

CREATE TABLE contractors (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    firstName VARCHAR(128) NOT NULL,
    lastName VARCHAR(128) NOT NULL,
    email VARCHAR(128) NULL,
    phone VARCHAR(64) NULL,
    type VARCHAR(64),
    createdAt DATETIME NOT NULL,
    updatedAt DATETIME NOT NULL
);

CREATE TABLE cars_2_contractors (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    car_id INTEGER NOT NULL,
    contractor_id INTEGER NOT NULL,
    createdAt DATETIME NOT NULL,
    updatedAt DATETIME NOT NULL,
    FOREIGN KEY (car_id) REFERENCES cars (id),
    FOREIGN KEY (contractor_id) REFERENCES contractors (id)
);

INSERT INTO cars (brand, model, productionYear, color, vin, createdAt, updatedAt)
VALUES
   ('Audi', 'A4', 2012, 'Yellow', 'ABC123456789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   ('BMW', 'X5', 2021, 'Black', 'XYZ987654321', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   ('BMW', 'X3', 2009, 'Yellow', 'XYZABCDEF123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   ('Ford', 'Mustang', 2023, 'Green', 'DEF456123789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); -- not assigned to any customer

INSERT INTO contractors (firstName, lastName, email, phone, type, createdAt, updatedAt)
VALUES
   ('John', 'Doe', 'john.doe@example.com', '555-1234', 'Mechanic', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   ('Jane', 'Smith', 'jane.smith@example.com', '555-5678', 'Painter', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   ('Bob', 'Johnson', 'bob.johnson@example.com', '555-9012', 'Owner', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   ('Dane', 'Joe', 'dane@joe.com', '1234-6783', 'Owner', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO cars_2_contractors (car_id, contractor_id, createdAt, updatedAt)
VALUES
   (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Car 1 is associated with Contractor 1
   (2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Car 2 is associated with Contractor 2
   (3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Car 3 is associated with Contractor 3
   (1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); -- Car 1 is associated with Contractor 3 (and Car 1 as well)
