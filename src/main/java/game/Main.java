package game;

import game.session.Session;

import java.util.Scanner;

public class Main {

    private static void startGameLoop(Session session, Scanner scanner) {
        //noinspection InfiniteLoopStatement
        while (true) {
            printSessionToConsole(session);
            System.out.print("> ~input: ");
            session.acceptUserIntent(scanner.nextLine());
        }
    }

    private static void printSessionToConsole(Session session) {
        System.out.println("> ~output: ");
        System.out.println(session.getEventText());

        var buttons = new StringBuilder("Buttons:\n");
        session.getButtonsText().forEach(buttonText -> buttons.append(buttonText).append(" | "));
        System.out.println(buttons);
    }

    public static void main(String[] args) {
        var user = new User("12349f91f91");
        var session = user.haveActiveGame() ? Repository.getSession(user) : Repository.newSession(user);
        startGameLoop(session, new Scanner(System.in));
    }
}
