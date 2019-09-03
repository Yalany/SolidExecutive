package prototype;

class PlayerResources {

    private static final int GOVERNMENT_RELATION_BASE = 75;     // %%
    private static final int CITIZENS_RELATION_BASE = 50;       // %%
    private static final int INFRASTRUCTURE_SCORE_BASE = 9001;  // value, in abstract score
    private static final int MONEY_BASE = 20000;                // value, in millions RUB

    private int governmentRelation;
    private int citizensRelation;
    private int infrastructureScore;
    private int money;

    PlayerResources() {
        this.governmentRelation = GOVERNMENT_RELATION_BASE;
        this.citizensRelation = CITIZENS_RELATION_BASE;
        this.infrastructureScore = INFRASTRUCTURE_SCORE_BASE;
        this.money = MONEY_BASE;
    }

    int getGovernmentRelation() {
        return governmentRelation;
    }

    int getCitizensRelation() {
        return citizensRelation;
    }

    int getInfrastructureScore() {
        return infrastructureScore;
    }

    int getMoney() {
        return money;
    }

    boolean acceptTransaction(ResourcesTransaction transaction) {
        if (!validateTransaction(transaction))
            return false;
        changeCitizensRelation(transaction);
        changeGovernmentRelation(transaction);
        changeInfrastructure(transaction);
        changeMoney(transaction);
        return true;
    }

    private boolean validateTransaction(ResourcesTransaction transaction) {
        return (isInfrastructureScoreEnough(transaction) && isMoneyEnough(transaction));
    }

    private boolean isInfrastructureScoreEnough(ResourcesTransaction transaction) {
        return infrastructureScore + transaction.getInfrastructureScoreDiff() >= 0;
    }

    private boolean isMoneyEnough(ResourcesTransaction transaction) {
        return money + transaction.getInfrastructureScoreDiff() >= 0;
    }

    private void changeGovernmentRelation(ResourcesTransaction transaction) {
        this.governmentRelation = Math.max(0, Math.min(100, transaction.getGovernmentRelationDiff() + this.governmentRelation));
    }

    private void changeCitizensRelation(ResourcesTransaction transaction) {
        this.citizensRelation = Math.max(0, Math.min(100, transaction.getCitizensRelationDiff() + this.citizensRelation));
    }

    private void changeInfrastructure(ResourcesTransaction transaction) {
        this.infrastructureScore += transaction.getInfrastructureScoreDiff();
        if (this.infrastructureScore < 0) this.infrastructureScore = 0;
    }

    private void changeMoney(ResourcesTransaction transaction) {
        this.money += transaction.getMoneyDiff();
        if (this.money < 0) this.money = 0;
    }
}
