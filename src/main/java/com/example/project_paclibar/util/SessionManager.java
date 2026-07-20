package com.example.project_paclibar.util;

import com.example.project_paclibar.model.User;
import java.io.*;

public class SessionManager {
    // Force the file to be created in the application's root directory
    private static final String SESSION_FILE = System.getProperty("user.dir") + File.separator + "session.dat";

    public static void saveSession(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SESSION_FILE))) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.err.println("Error saving session: " + e.getMessage());
        }
    }

    public static User getSession() {
        File file = new File(SESSION_FILE);

        System.out.println("Looking for session at: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("Session file not found.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SESSION_FILE))) {
            return (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading session: " + e.getMessage());
            return null;
        }
    }

    public static void deleteSession() {
        File file = new File(SESSION_FILE);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Session deleted successfully.");
            }
        }
    }
}