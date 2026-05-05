package Suizo5;

import javax.swing.*;

import java.io.*;
import java.util.*;

public class Students_FeedBack_System {

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Opening Student Feedback...", "Student Feedback System",JOptionPane.INFORMATION_MESSAGE);

		int totalFeedbacks = 0;
		int totalRatings = 0;
		int[] categoryCounts = new int[5];
		String[] labels = { "Very Poor", "Poor", "Average", "Good", "Excellent" };
		boolean exit = false;

		File file = new File("feedback.txt");

		try (PrintWriter writer = new PrintWriter(file)) {
			writer.println("--- Student Feedback Records ---\n");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error initializing file: " + e.getMessage());
			return;
		}

		while (!exit) {
			String menu = "Menu:\n[1] Add Feedback\n[2] View Student Feedback\n[3] Exit";
			String choice = JOptionPane.showInputDialog(null, menu, "Student Feedback System",JOptionPane.QUESTION_MESSAGE);

			if (choice == null) {
				exit = true;
				break;
			}

			if (!choice.isBlank()) {
				try {
					int option = Integer.parseInt(choice);
					switch (option) {
					case 1:
						String studentName = JOptionPane.showInputDialog(null, "Enter Student Name:", "Student Feedback System",JOptionPane.QUESTION_MESSAGE);
						String course = JOptionPane.showInputDialog(null, "Enter Course:", "Student Feedback System",JOptionPane.QUESTION_MESSAGE);
						String message = JOptionPane.showInputDialog(null, "Enter Feedback:", "Student Feedback System",JOptionPane.QUESTION_MESSAGE);

						int rating = 0;
						while (true) {
							try {
								String ratingStr = JOptionPane.showInputDialog(null, "Enter Rating (1-5):","Student Feedback System", JOptionPane.QUESTION_MESSAGE);
								rating = Integer.parseInt(ratingStr);
								if (rating < 1 || rating > 5) {
									JOptionPane.showMessageDialog(null, "Rating must be between 1-5.");
									continue;
								}
								break;
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Enter numbers only (1-5).");
							}
						}

						totalFeedbacks++;
						totalRatings += rating;
						categoryCounts[rating - 1]++;

						try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
							bw.write("Student Name: " + studentName + "\n");
							bw.write("Course: " + course + "\n");
							bw.write("Feedback: " + message + "\n");
							bw.write("Rating: " + rating + " (" + labels[rating - 1] + ")\n\n");
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
						}

						JOptionPane.showMessageDialog(null, "Feedback added successfully!", "Student Feedback System",JOptionPane.INFORMATION_MESSAGE);
						break;

					case 2:
					    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
					        String line;
					        String content = "";
					        while ((line = br.readLine()) != null) {
					            content += line + "\n";
					        }

					        double averageRating = totalFeedbacks > 0 ? (double) totalRatings / totalFeedbacks : 0.0;
					        content += "------------------------------------------------\n";
					        content += "Total Feedbacks: " + totalFeedbacks + "\n";
					        content += "Average Rating: " + String.format("%.1f", averageRating) + "\n\n";
					        content += "Rating Summary:\n";
					        content += "Excellent: " + categoryCounts[4] + "\n";
					        content += "Good: " + categoryCounts[3] + "\n";
					        content += "Average: " + categoryCounts[2] + "\n";
					        content += "Poor: " + categoryCounts[1] + "\n";
					        content += "Very Poor: " + categoryCounts[0] + "\n";
					        content += "------------------------------------------------";

					        JOptionPane.showMessageDialog(null, content, "Student Feedback Records",
					                JOptionPane.INFORMATION_MESSAGE);
					    } catch (Exception e) {
					        JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
					    }
					    break;
					case 3:
						exit = true;
						break;

					default:
						JOptionPane.showMessageDialog(null, "Invalid option. Enter 1, 2, or 3.",
								"Student Feedback System", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Enter numbers only (1, 2, or 3).", "Student Feedback System",JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "No input detected. Try again.", "Student Feedback System",JOptionPane.WARNING_MESSAGE);
			}
		}

		JOptionPane.showMessageDialog(null, "...Thank you for using the Student Feedback System...","Student Feedback System", JOptionPane.INFORMATION_MESSAGE);

	}

}
