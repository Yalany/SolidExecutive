package game.session.operation;

import game.session.SessionOperator;
import game.session.Operation;

public class InsertNextEvent implements Operation {

    private int eventId;

    public InsertNextEvent(int eventId) {
        this.eventId = eventId;
    }

    @Override
    public void activate(SessionOperator operator) {
        operator.insertNextEvent(eventId);
    }
}
