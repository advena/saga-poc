package user.aggregate.command;

import common.Command;
import lombok.Value;

/**
 * Created by advena on 25.09.16.
 */
@Value
public class AddPoints implements Command {
    private final int points;
}
