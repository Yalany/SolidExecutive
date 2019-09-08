package model;

import model.session.Session;
import model.session.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var userId = "12349f91f91";

        User user = Repository.getUser(userId);

        Session session;

        if (user.haveActiveGame())
            session = Repository.getSession(userId);
        else
            session = Session.builder()
                    .setEventProvider(
                            user.isSubscribed() ? Repository.getEventProvider("gamePlus")
                                    : Repository.getEventProvider("game"))
                    .setCommands(Repository.getCommands("menu"))
                    .setCommands(Repository.getCommands("game"))
                    .setEventDeck(Repository.getEventDeck("tutorial"))
                    .setEventDeck(Repository.getEventDeck("standard"))
                    .setGameResources(Repository.getGameResources("tutorial"))
                    .setGameResources(Repository.getGameResources("standard"))
                    .build();

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

            StringBuilder buttons = new StringBuilder("Buttons:\n");
            buttonsText.forEach(b -> buttons.append(b).append(" | "));

            System.out.println(eventText);
            System.out.println(buttons.toString());
        }
    }
}
