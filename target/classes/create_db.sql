DROP TABLE cars_2_contractors;
DROP TABLE cars;
DROP TABLE contractors;

CREATE TABLE cars (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    brand VARCHAR(128) NOT NULL,
    model VARCHAR(128) NULL,
    productionYear INTEGER NOT NULL,
    color VARCHAR(64) NULL,
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
