package model;

import model.session.Session;

import java.util.Scanner;

public class Main {

    private static void startGameLoop(Session session, Scanner scanner) {
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.print("> ~input: ");
            session.acceptUserIntent(scanner.nextLine());
            printSessionToConsole(session);
        }
    }

    private static void printSessionToConsole(Session session) {
        System.out.println("> ~output: ");
        System.out.println(session.getEventText());

        var buttons = new StringBuilder("Buttons:\n");
        session.getButtonsText().forEach(buttonText -> buttons.append(buttonText).append(" | "));
        System.out.println(buttons.toString());
    }

    public static void main(String[] args) {
        var userId = "12349f91f91";

        var forUser = Repository.getUser(userId);
        var session = forUser.haveActiveGame() ? Repository.getSession(forUser) : Repository.newSession(forUser);
        printSessionToConsole(session);
        startGameLoop(session, new Scanner(System.in));
    }
}
