package prototype;

class ResourcesTransaction {
    private final int governmentRelationDiff;
    private final int citizensRelationDiff;
    private final int infrastructureScoreDiff;
    private final int moneyDiff;

    ResourcesTransaction(int governmentRelationDiff,
                                int citizensRelationDiff,
                                int infrastructureScoreDiff,
                                int moneyDiff) {
        this.governmentRelationDiff = governmentRelationDiff;
        this.citizensRelationDiff = citizensRelationDiff;
        this.infrastructureScoreDiff = infrastructureScoreDiff;
        this.moneyDiff = moneyDiff;
    }

    int getGovernmentRelationDiff() {
        return governmentRelationDiff;
    }

    int getCitizensRelationDiff() {
        return citizensRelationDiff;
    }

    int getInfrastructureScoreDiff() {
        return infrastructureScoreDiff;
    }

    int getMoneyDiff() {
        return moneyDiff;
    }
}
