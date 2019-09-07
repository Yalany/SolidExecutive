package model;

import model.session.Commands;
import model.session.EventProvider;
import model.session.Session;
import model.session.eventprovider.GameEventProvider;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var userId = "12349f91f91";
        var userIntent = "NEW USER";

        EventProvider eventProvider = new GameEventProvider();
        Commands commands = new Commands();

        var session = Session.builder()
                .setUser(userId)
                .setEventDeck("Standard")
                .setGameResources("Normal")
                .setEventProvider(eventProvider)
                .setCommands(commands)
                .build();

        session.acceptUserIntent(userIntent);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter intent: ");
            var input = scanner.nextLine();

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
