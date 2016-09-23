package game.data;

import lombok.Value;

import java.time.YearMonth;

/**
 * Created by advena on 23.09.16.
 */
@Value
public class GameDataDto {

    private final YearMonth startDate;
    private final YearMonth endDate;

}
