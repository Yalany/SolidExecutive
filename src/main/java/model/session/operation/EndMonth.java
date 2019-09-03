package model.session.operation;

import model.session.SessionOperator;
import model.session.Operation;

public class EndMonth implements Operation {

    @Override
    public void activate(SessionOperator operator) {
        operator.endMonth();
    }
}
