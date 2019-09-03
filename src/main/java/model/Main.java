package model;

import model.session.Session;

import java.util.Scanner;

public class Main {

    private static SessionProvider sessionProvider = new SessionProvider();

    public static void main(String[] args) {
        var userId = 1984;
        var userIntent = "NEW USER";

        Session session = sessionProvider.getSessionById(userId);
        session.acceptUserIntent(userIntent);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter intent: ");
            String input = scanner.nextLine();

            if (input.equals("q")) {
                System.out.println("Exit!");
                scanner.close();
                return;
            }

            session.acceptUserIntent(input);

            var eventText = session.getEventText();
            var buttonsText = session.getButtonsText();

            StringBuilder buttons = new StringBuilder("Buttons:");
            buttonsText.forEach(b -> buttons.append(" ").append(b));

            System.out.println(eventText);
            System.out.println(buttons.toString());
        }
    }
}
