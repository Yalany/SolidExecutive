package prototype;

public class Session {

    // commands formatted string data
    private final static String CURRENT_MONTH = "Вы управляете городом Эн уже %d месяцев. Желаете вернуться к делам?";
    private final static String RESOURCES_STATUS_TEXT = "Господин мэр, начальство одобряет вас на %d%%, а жители - на %d%%." +
            " Состояние городской инфраструктуры оценивается на %d очков, текущий бюджет - %d000000. Вернёмся к принятию решений?";
    private final static String EXIT_GAME = "Отдых — дело хорошее. Мы будем ждать Ваших будущих решений!";
    private final static String CATCH_ALL_FIRST = "Прошу прощения, но я не вполне понимаю, что Вы имеете в виду. Вас не затруднит повторить?";
    private final static String CATCH_ALL_SECOND = "Похоже, так мы ни к чему не придём. Быть может, лучше вернёмся к делам?";

    // list of all possible intents
    final static String INTENT_OPTION_ONE = "option one";
    final static String INTENT_OPTION_TWO = "option two";
    final static String INTENT_OPTION_THREE = "option three";
    private final static String INTENT_CATCH_ALL = "catch all";
    private final static String INTENT_REPEAT = "repeat";
    private final static String INTENT_MONTH = "month";
    private final static String INTENT_RESOURCES = "resources";
    private final static String INTENT_EXIT = "exit";

    private final EventProvider eventProvider;
    private final EventDeck eventDeck;
    private final PlayerState playerState;

    Session(EventProvider eventProvider) {
        this.eventProvider = eventProvider;
        this.eventDeck = new EventDeck();
        this.playerState = new PlayerState();
        this.playerState.setCurrentEvent(eventProvider.getEventByIdForSession(eventDeck.pop(), this));
    }

    String talk(String intent) {
        var currentSystemEvent = playerState.getSystemEvent();
        // if player currently in system event then he have only two options to use
        if (currentSystemEvent != null) {
            // catch all requires saved catch all event in order to operate
            if (!(currentSystemEvent.equals(INTENT_CATCH_ALL) && intent.equals(INTENT_CATCH_ALL)))
                playerState.flushSystemEvent();
            // "option one": return to gameplay
            if (intent.equals(INTENT_OPTION_ONE))
                return parseCommand(INTENT_REPEAT);
            // "option two": exit skill
            if (intent.equals(INTENT_OPTION_TWO))
                return parseCommand(INTENT_EXIT);
        }
        // TODO BUG: if event doesn't have certain options, parse intents for those options as catch all
        if (playerState.getCurrentEvent().acceptIntent(intent))
            return playerState.getCurrentEvent().getEventText();

        return parseCommand(intent);
    }

    private String parseCommand(String intent) {
        switch (intent) {
            case INTENT_MONTH:
                playerState.setSystemEvent(intent);
                return currentMonth();
            case INTENT_RESOURCES:
                playerState.setSystemEvent(intent);
                return resourcesStatus();
            case INTENT_REPEAT:
                return commandRepeat();
            case INTENT_EXIT:
                return exitGame();
            case INTENT_CATCH_ALL:
                return catchAll();
            default:
                throw new IllegalArgumentException("illegal intent");
        }
    }

    private String commandRepeat() {
        return playerState.getCurrentEvent().getEventText();
    }

    private String currentMonth() {
        return String.format(CURRENT_MONTH, playerState.getMonthsPassed());
    }

    private String resourcesStatus() {
        var resources = playerState.getPlayerResources();
        return String.format(RESOURCES_STATUS_TEXT,
                resources.getGovernmentRelation(),
                resources.getCitizensRelation(),
                resources.getInfrastructureScore(),
                resources.getMoney());
    }

    private String exitGame() {
        saveSession();
        // TODO ask session manager to terminate this session
        return EXIT_GAME;
    }

    private void saveSession() {
        // TODO save session using provided db interface
    }

    private String catchAll() {
        var systemEvent = playerState.getSystemEvent();
        if (systemEvent != null && systemEvent.equals(INTENT_CATCH_ALL))
            return CATCH_ALL_SECOND;
        playerState.setSystemEvent(INTENT_CATCH_ALL);
        return CATCH_ALL_FIRST;
    }

    void scheduleEvent(int eventId, int offset) {
        eventDeck.shuffleIn(eventId, offset);
    }

    void changeResources(ResourcesTransaction transaction) {
        if (transaction == null)
            return;
        if (!playerState.changeResources(transaction))
            throw new IllegalArgumentException("resource transaction can't be accepted");
    }

    void triggerEventInstantly(int eventId) {
        eventDeck.putOnTop(eventId);
        playerState.setCurrentEvent(eventProvider.getEventByIdForSession(eventDeck.pop(), this));
        playerState.getCurrentEvent().start();
    }

    void triggerNextMonth() {
        playerState.nextMonth();
        playerState.setCurrentEvent(eventProvider.getEventByIdForSession(eventDeck.pop(), this));
        playerState.getCurrentEvent().start();
    }
}
