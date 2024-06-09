CREATE OR REPLACE FUNCTION log_patient_death()
RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.death_date IS NOT NULL AND
       OLD.death_date IS NULL THEN
        INSERT INTO Treatment Results (
                PatientNoChanges, 
                PatientWorsenings,
                PatientImprovements,
                DiagnosisId,    
                DoctorId
            ) VALUES (
                NULL,
                'Press F as he died',
                NULL,
                NULL,
                NULL
            );
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER log_treatment_results_when_death
AFTER UPDATE OF death_date ON Patients
FOR EACH ROW
EXECUTE FUNCTION log_patient_death();
