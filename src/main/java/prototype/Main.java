package prototype;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Event event = new Event()
                .setEventText("Это тестовый эвент. Чтобы запустить тестовый эвент2 и завершить текущий месяц выбери option one.")
                .addOption(new ResourcesTransaction(5, -5, 100, -20),
                        2, 10, 2, false);
        Event event2 = new Event()
                .setEventText("Это тестовый эвент 2. Чтобы запустить этот же эвент, но завершить текущий месяц выбери option one.")
                .addOption(null,
                        -1, 0, 1, true)
                .setInitialTransaction(new ResourcesTransaction(5, -5, 100, -20));

        HashMap<Integer, Event> eventsMap = new HashMap<>();
        eventsMap.put(1, event);
        eventsMap.put(2, event2);
        EventProvider eventProvider = new EventProvider(eventsMap);
        Session session = new Session(eventProvider);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter intent: ");
            String input = scanner.nextLine();

            if (input.equals("q")) {
                System.out.println("Exit!");
                scanner.close();
                return;
            }

            System.out.println(session.talk(input));
        }
    }
}
