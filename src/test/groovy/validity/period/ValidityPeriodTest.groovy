package validity.period

import spock.lang.Specification

import java.time.YearMonth

/**
 * Created by advena on 25.09.16.
 */
class ValidityPeriodTest extends Specification {

    def "should return desired list of months for given start and end month"() {
        given:
        YearMonth startMonth = YearMonth.parse(start);
        YearMonth endMonth = YearMonth.parse(end);
        def validityPeriod = new ValidityPeriod(startMonth, endMonth)
        def expectedYearMonths = new ArrayList<>()
        expectedMonths.each { month ->
            expectedYearMonths.add(YearMonth.parse(month))
        }

        expect:
        validityPeriod.getAllMonths() == expectedYearMonths

        where:
        start     | end       | expectedMonths
        "2016-01" | "2016-01" | ["2016-01"]
        "2016-01" | "2016-05" | ["2016-01", "2016-02", "2016-03", "2016-04", "2016-05"]
        "2015-12" | "2016-02" | ["2015-12", "2016-01", "2016-02"]
        "2016-11" | "2017-01" | ["2016-11", "2016-12", "2017-01"]
        "2015-12" | "2017-01" | ["2015-12", "2016-01", "2016-02", "2016-03", "2016-04", "2016-05", "2016-06", "2016-07", "2016-08", "2016-09", "2016-10", "2016-11", "2016-12", "2017-01"]

    }
}
