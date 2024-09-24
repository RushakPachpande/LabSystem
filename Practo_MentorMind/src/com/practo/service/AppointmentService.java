package com.practo.service;

import java.sql.SQLException;
import java.util.List;

import com.practo.daoimpl.AppointmentDaoImpl;
import com.practo.entity.Appointment;

public class AppointmentService {
	private AppointmentDaoImpl appointmentDAO = new AppointmentDaoImpl();

	// Method to schedule an appointment
	public void scheduleAppointment(Appointment appointment) throws SQLException {
		appointmentDAO.createAppointment(appointment);
	}

	// Method to get appointments by patient ID
	public List<Appointment> getAppointmentsByPatientId(int patientIdForAppointment) throws SQLException {
		return appointmentDAO.viewAppointment(patientIdForAppointment); // Return the list of appointments
	}
}
