package model.session;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Event {

    private int id;
    private String text;
    private List<Button> buttons;

    Event(int id, String text, List<Button> buttons) {
        this.id = id;
        this.text = text;
        this.buttons = buttons;
    }

    public int getId() {
        return id;
    }

    String getText() {
        return text;
    }

    List<String> getButtonsText() {
        return buttons.stream().map(Button::getName).collect(Collectors.toCollection(LinkedList::new));
    }

    boolean canAcceptIntent(String intent) {
        return buttons.stream().anyMatch(btn -> btn.contains(intent));
    }

    void acceptIntent(String intent, SessionOperator operator) {
        buttons.stream().filter(btn -> btn.contains(intent)).forEach(btn -> btn.activate(operator));
    }
}
