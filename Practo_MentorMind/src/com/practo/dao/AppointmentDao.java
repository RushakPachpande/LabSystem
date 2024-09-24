package com.practo.dao;

import java.sql.SQLException;

import com.practo.entity.Appointment;

public interface AppointmentDao {
	public void createAppointment(Appointment appointment) throws SQLException;

	public void updateAppointment(Appointment appointment) throws SQLException;

	public Appointment viewAppointment(int appointmentId) throws SQLException;
}
