package model.session.operation;

import model.session.SessionOperator;
import model.session.Operation;

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
