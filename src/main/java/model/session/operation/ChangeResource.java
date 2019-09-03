package model.session.operation;

import model.session.SessionOperator;
import model.session.Operation;

public class ChangeResource implements Operation {

    private String resourceName;
    private int value;

    public ChangeResource(String resourceName, int value) {
        this.resourceName = resourceName;
        this.value = value;
    }

    @Override
    public void activate(SessionOperator operator) {
        operator.changeResources(resourceName, value);
    }
}
