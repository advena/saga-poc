package user.points.addition;

import user.aggregate.UserAggregate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by advena on 25.09.16.
 */
public class EventsAssert implements user.aggregate.UserAggregate.Events {

    List<UserAggregate.PointsAdded> pointsAddedEvents = new ArrayList<>();

    @Override
    public void emit(UserAggregate.PointsAdded event) {
        pointsAddedEvents.add(event);
    }

    @Override
    public void emit(UserAggregate.PointsExtracted event) {

    }

    @Override
    public void emit(UserAggregate.GameAdded event) {

    }


    public int getPoints() {
        return pointsAddedEvents.get(pointsAddedEvents.size() - 1).getPoints();
    }
}
