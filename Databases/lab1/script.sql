BEGIN;
DROP TABLE IF EXISTS MedicationHistory CASCADE;
DROP TABLE IF EXISTS TreatmentResults CASCADE;
DROP TABLE IF EXISTS Diagnoses CASCADE;
DROP TABLE IF EXISTS Medications CASCADE;
DROP TABLE IF EXISTS Diseases CASCADE;
DROP TABLE IF EXISTS Patients CASCADE;
DROP TABLE IF EXISTS Doctors CASCADE;
DROP TABLE IF EXISTS AssignedMedications CASCADE;

CREATE TABLE Patients (
    Id SERIAL PRIMARY KEY,
    Name TEXT NOT NULL,
    Surname TEXT NOT NULL,
    Gender VARCHAR(1) NOT NULL CHECK (Gender IN ('M', 'W')),
    BirthDate DATE NOT NULL,
    DeathDate DATE
    );
CREATE TABLE Doctors (
    Id SERIAL PRIMARY KEY,
    Name TEXT NOT NULL,
    Surname TEXT NOT NULL,
    Experience INTEGER NOT NULL,
    Qualification TEXT NOT NULL,
    EmploymentStartDate DATE NOT NULL,
    EmploymentEndDate DATE
    );
CREATE TABLE Diseases (
    Id SERIAL PRIMARY KEY,
    Name TEXT NOT NULL,
    Symptoms TEXT NOT NULL,
    Treatment TEXT NOT NULL,
    Prognosis TEXT NOT NULL
    );
CREATE TABLE Medications (
    Id SERIAL PRIMARY KEY,
    Name TEXT NOT NULL,
    Composition TEXT NOT NULL,
    Dosage TEXT NOT NULL,
    Indications TEXT NOT NULL,
    SideEffects TEXT,
    Instructions TEXT NOT NULL
    );
CREATE TABLE Diagnoses (
    Id SERIAL PRIMARY KEY,
    Time TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    Description TEXT,
    Symptoms TEXT NOT NULL,
    DoctorId INTEGER REFERENCES Doctors(Id),
    PatientId INTEGER REFERENCES Patients(Id),
    DiseaseId INTEGER REFERENCES Diseases(Id)
    );
CREATE TABLE MedicationHistory (
    Id SERIAL PRIMARY KEY,
    Time TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    DiagnosisId INTEGER REFERENCES Diagnoses(Id),
    MedicationId INTEGER REFERENCES Medications(Id)
    );
CREATE TABLE TreatmentResults (
    Id SERIAL PRIMARY KEY,
    Time TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PatientNoChanges TEXT,
    PatientWorsenings TEXT,
    PatientImprovements TEXT,
    DiagnosisId INTEGER REFERENCES Diagnoses(Id),
    DoctorId INTEGER REFERENCES Doctors(Id)
    );
CREATE TABLE AssignedMedications (
    DiagnosisId INTEGER REFERENCES Diagnoses(Id),
    MedicationId INTEGER REFERENCES Medications(Id),
    PRIMARY KEY(DiagnosisId, MedicationId)
    );

INSERT INTO Doctors (
        Name,
        Surname,
        Experience,
        Qualification,
        EmploymentStartDate
    ) VALUES (
        'Алёша',
        'Ливси',
        10,
        'Специалист по рому',
        '1999-04-15'
    );
INSERT INTO Patients (
        Name,
        Surname,
        Gender,
        BirthDate
    ) VALUES (
        'Андрей',
        'Смолов',
        'M',
        '2006-10-15'
    );
INSERT INTO Diseases (
        Name,
        Symptoms,
        Treatment,
        Prognosis
    ) VALUES (
        'Корь',
        'Горячая голова и жар',
        'Пить много воды',
        'Развивается в рак'
    );
INSERT INTO Diagnoses (
        Description,
        Symptoms,
        DoctorId,
        PatientId,
        DiseaseId
    ) VALUES (
        'Ему очень плохо',
        'У него жар',
        1,
        1,
        1
    );
INSERT INTO Medications (
        Name,
        Composition,
        Dosage ,
        Indications,
        SideEffects,
        Instructions
    ) VALUES (
        'Антибиотик АНТИРОМ',
        'Водка',
        '1 ложка',
        'Жар',
        'Трезвость',
        'Пить каждый день до конца жизни'
    );
INSERT INTO AssignedMedications (
        DiagnosisId,
        MedicationId
    ) VALUES (1, 1);
END;