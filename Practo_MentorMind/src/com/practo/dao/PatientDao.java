package com.practo.dao;

import java.sql.SQLException;

import com.practo.entity.Patient;

public interface PatientDao {

	public Patient getPatientById(int patientId) throws SQLException;

	public void createPatient(Patient patient) throws SQLException;

	public void updatePatient(Patient patient) throws SQLException;

}
