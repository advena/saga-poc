package user.aggregate;

import common.Event;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import user.aggregate.command.AddPoints;

/**
 * Created by advena on 23.09.16.
 */
@RequiredArgsConstructor
public class UserAggregate {

    private final long userId;
    private final Events events;
    private int saldo = 0;

    public void addPoints(AddPoints pointsToAdd) {
        boolean result = validate(pointsToAdd);
        if(result) {
            handle(pointsToAdd);
            events.emit(new PointsAdded(userId, saldo));
        }
    }

    private void handle(AddPoints pointsToAdd) {
        saldo += pointsToAdd.getPoints();
    }

    private boolean validate(AddPoints pointsToAdd) {
        return true;
    }

    public void addGame(long gameId) {

    }


    public interface Events {
        void emit(PointsAdded event);

        void emit(PointsExtracted event);

        void emit(GameAdded event);
    }

    @Value
    public static class PointsAdded implements Event {
        private final long userId;
        private final int points;
    }

    @Value
    public static class PointsExtracted implements Event{
        private final long userId;
        private final int points;
    }

    @Value
    public static class GameAdded implements Event{
        private final long gameId;
    }
}
