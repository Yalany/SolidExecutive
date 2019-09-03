package model.session.operation;

import model.session.Operation;
import model.session.SessionOperator;

public class ExitGame implements Operation {

    @Override
    public void activate(SessionOperator operator) {
        operator.exitGame();
    }
}
