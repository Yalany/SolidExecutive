package model.session;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Event {

    private String text;
    private List<Button> buttons;

    Event(String text, List<Button> buttons) {
        this.text = text;
        this.buttons = buttons;
    }

    Event setText(String text) {
        this.text = text;
        return this;
    }

    Event addButton(Button button) {
        buttons.add(button);
        return this;
    }

    String getText() {
        return text;
    }

    List<String> getButtonsText() {
        return buttons.stream()
                .map(Button::getName)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    boolean canAcceptIntent(String intent) {
        return buttons.stream()
                .anyMatch(btn -> btn.contains(intent));
    }

    void acceptIntent(String intent, SessionOperator operator) {
        buttons.stream()
                .filter(btn -> btn.contains(intent))
                .forEach(btn -> btn.activate(operator));
    }
}
