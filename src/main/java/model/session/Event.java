package model.session;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Event {

    private final String text;
    private final List<Button> buttons;

    private Event(final String text, final List<Button> buttons) {
        this.text = text;
        this.buttons = buttons;
    }

    String getText() {
        return text;
    }

    List<String> getButtonsText() {
        return buttons.stream()
                .map(Button::getName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    boolean canAcceptIntent(String intent) {
        return buttons.stream()
                .anyMatch(btn -> btn.containsIntent(intent));
    }

    void acceptIntent(String intent) {
        buttons.stream()
                .filter(btn -> btn.containsIntent(intent))
                .forEach(Button::activate);
    }

    // builder
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String text;
        private List<Button> buttons;

        private Builder() {
            buttons = new ArrayList<>();
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder addButton(Button button) {
            buttons.add(button);
            return this;
        }

        public Event build() {
            if (text == null)
                throw new IllegalStateException("text can't be null");
            if (buttons.isEmpty())
                throw new IllegalStateException("should have at least one button");
            return new Event(text, buttons);
        }
    }
}
