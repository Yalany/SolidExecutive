package model.session.operation;

import model.session.SessionOperator;
import model.session.Operation;

public class ScheduleEvent implements Operation {

    private int eventId;
    private int offset;

    public ScheduleEvent(int eventId, int offset) {
        this.eventId = eventId;
        this.offset = offset;
    }

    @Override
    public void activate(SessionOperator operator) {
        operator.scheduleEvent(eventId, offset);
    }
}
