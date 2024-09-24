package com.practo;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import com.practo.daoimpl.PatientDaoImpl;
import com.practo.daoimpl.UserDaoImpl;
import com.practo.entity.Appointment;
import com.practo.entity.Patient;
import com.practo.entity.User;
import com.practo.service.AppointmentService;

public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			UserDaoImpl userDAO = new UserDaoImpl();
			PatientDaoImpl patientDAO = new PatientDaoImpl();
			AppointmentService appointmentService = new AppointmentService();

			// User Login
			System.out.print("Enter your username : ");
			String username = scanner.nextLine();
			System.out.print("Enter your password : ");
			String password = scanner.nextLine();

			// Authenticate user
			User loggedInUser = userDAO.login(username, password);
			if (loggedInUser == null) {
				System.out.println("Invalid username or password. Exiting...");
				return;
			}

			System.out.println("Login successful!");

			boolean continueApp = true;
			while (continueApp) {
				// Display options after login
				System.out.println("Select an option :");
				System.out.println("1. Create Patient");
				System.out.println("2. View Patient");
				System.out.println("3. Schedule Appointment");
				System.out.println("4. View Appointment");

				int choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline after integer input

				switch (choice) {
				case 1:
					// Case 1: Create a new Patient
					System.out.println("Enter first name : ");
					String firstName = scanner.nextLine();

					System.out.println("Enter last name : ");
					String lastName = scanner.nextLine();

					System.out.println("Enter date of birth (yyyy-mm-dd) : ");
					String dobInput = scanner.nextLine();
					Date dob = Date.valueOf(dobInput);

					System.out.println("Enter contact number : ");
					String contactNumber = scanner.nextLine();

					System.out.println("Enter address : ");
					String address = scanner.nextLine();

					// Create and save a new patient
					Patient newPatient = new Patient();
					newPatient.setFirstName(firstName);
					newPatient.setLastName(lastName);
					newPatient.setDateOfBirth(dob);
					newPatient.setContactNumber(contactNumber);
					newPatient.setAddress(address);

					patientDAO.createPatient(newPatient);
					System.out.println("Patient created successfully!");
					break;

				case 2:
					// Case 2: View a Patient
					System.out.println("Enter Patient ID : ");
					int patientId = scanner.nextInt(); // Get the patientId at runtime
					scanner.nextLine(); // Consume newline after integer input

					Patient patient = patientDAO.getPatientById(patientId);
					if (patient == null) {
						System.out.println("Patient not found.");
					} else {
						System.out.println("Patient Details : ");
						System.out.println("ID: " + patient.getPatientId());
						System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
						System.out.println("DOB: " + patient.getDateOfBirth());
						System.out.println("Contact Number: " + patient.getContactNumber());
						System.out.println("Address: " + patient.getAddress());
					}
					break;

				case 3:
					// Case 3: Schedule a new appointment
					System.out.println("Enter User ID : ");
					int userId = scanner.nextInt(); // Get the userId at runtime
					scanner.nextLine(); // Consume newline after integer input

					// Fetch user by ID
					User user = userDAO.getUserById(userId);
					if (user == null) {
						System.out.println("User not found. Cannot schedule an appointment.");
						return;
					}

					System.out.println("Enter Patient ID : ");
					int patientId1 = scanner.nextInt(); // Get the patientId at runtime
					scanner.nextLine(); // Consume newline after integer input

					// Fetch patient by ID
					Patient patientForAppointment = patientDAO.getPatientById(patientId1);
					if (patientForAppointment == null) {
						System.out.println("Patient not found. Cannot schedule an appointment.");
						return;
					}

					// Get appointment details from the user
					System.out.println("Enter appointment date (yyyy-mm-dd) : ");
					String appointmentDateInput = scanner.nextLine();
					Date appointmentDate = Date.valueOf(appointmentDateInput);

					System.out.println("Enter appointment time (HH:mm:ss) : ");
					String appointmentTimeInput = scanner.nextLine();
					Time appointmentTime = Time.valueOf(appointmentTimeInput);

					System.out.println("Enter test type : ");
					String testType = scanner.nextLine();

					// Create and schedule the appointment
					Appointment appointment = new Appointment();
					appointment.setPatient(patientForAppointment);
					appointment.setUser(user);
					appointment.setAppointmentDate(appointmentDate);
					appointment.setAppointmentTime(appointmentTime);
					appointment.setTestType(testType);

					appointmentService.scheduleAppointment(appointment);
					System.out.println("Appointment scheduled successfully!");
					break;

				case 4:
					// Case 4: View Appointments for a Patient
					System.out.println("Enter Patient ID : ");
					int patientIdForAppointment = scanner.nextInt();
					scanner.nextLine(); // Consume newline after integer input

					// Fetch appointments by Patient ID
					List<Appointment> appointments = appointmentService
							.getAppointmentsByPatientId(patientIdForAppointment);
					if (appointments.isEmpty()) {
						System.out.println("No appointments found for this patient.");
					} else {
						System.out.println("Appointments for Patient ID " + patientIdForAppointment + " : ");
						for (Appointment app : appointments) {
							System.out.println("Date: " + app.getAppointmentDate());
							System.out.println("Time: " + app.getAppointmentTime());
							System.out.println("Test Type: " + app.getTestType());

							// Check if the User object is null before accessing its ID
							if (app.getUser() != null) {
								System.out.println("Assigned User ID : " + app.getUser().getUserId());
							} else {
								System.out.println("Assigned User: No user assigned to this appointment.");
							}

							System.out.println("--------------------------");
						}
					}
					break;

				default:
					System.out.println("Invalid choice. Please select a valid option.");
				}

				// Ask the user if they want to continue or exit
				System.out.println("Do you want to continue? (Y/N) : ");
				String continueInput = scanner.nextLine();
				if (!continueInput.equalsIgnoreCase("Y")) {
					continueApp = false;
					System.out.println("Exiting application. Goodbye!");
				}
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}
}
