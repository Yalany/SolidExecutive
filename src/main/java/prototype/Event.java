package prototype;

@SuppressWarnings({"SameParameterValue", "unused"})
class Event {

    private Session session;

    private String eventText;
    private ResourcesTransaction initialTransaction;
    private Option[] options;

    class Option {

        private ResourcesTransaction transaction;
        private int eventToSchedule;
        private int scheduleOffset;
        private int eventToTrigger;
        private boolean doesEndsMonth;

        Option(ResourcesTransaction transaction,
               int eventToSchedule,
               int scheduleOffset,
               int eventToTrigger,
               boolean doesEndsMonth) {
            this.transaction = transaction;
            this.eventToSchedule = eventToSchedule;
            this.scheduleOffset = scheduleOffset;
            this.eventToTrigger = eventToTrigger;
            this.doesEndsMonth = doesEndsMonth;
        }

        void trigger() {
            if (transaction != null)
                session.changeResources(transaction);
            if (eventToSchedule > -1)
                session.scheduleEvent(eventToSchedule, scheduleOffset);
            if (eventToTrigger > -1)
                session.triggerEventInstantly(eventToTrigger);
            if (doesEndsMonth)
                session.triggerNextMonth();
        }
    }

    Event() {
        options = new Option[3];
    }

    Event setEventText(String eventText) {
        this.eventText = eventText;
        return this;
    }

    Event setInitialTransaction(ResourcesTransaction initialTransaction) {
        this.initialTransaction = initialTransaction;
        return this;
    }

    Event addOption(ResourcesTransaction transaction,
                    int eventToSchedule,
                    int scheduleOffset,
                    int eventToTrigger,
                    boolean doesEndsMonth) {
        var option = new Option(transaction,
                eventToSchedule,
                scheduleOffset,
                eventToTrigger,
                doesEndsMonth);
        int index = 0;
        while (options[index] != null) index++;
        options[index] = option;
        return this;
    }

    Event setSession(Session session) {
        this.session = session;
        return this;
    }

    void start() {
        if (initialTransaction != null)
            session.changeResources(initialTransaction);
    }

    String getEventText() {
        return eventText;
    }

    boolean acceptIntent(String intent) {
        if (intent == null)
            throw new IllegalArgumentException("can't accept: intent is null");

        if (intent.equals(Session.INTENT_OPTION_ONE) && options[0] != null) {
            options[0].trigger();
            return true;
        }
        if (intent.equals(Session.INTENT_OPTION_TWO) && options[1] != null) {
            options[1].trigger();
            return true;
        }
        if (intent.equals(Session.INTENT_OPTION_THREE) && options[2] != null) {
            options[2].trigger();
            return true;
        }
        return false;
    }
}
