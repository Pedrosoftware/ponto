package entity

import org.joda.time.Duration
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import org.joda.time.Period
import org.joda.time.format.DateTimeFormatterBuilder
import org.joda.time.format.PeriodFormatterBuilder

/**
 * Created by pedro on 08/08/17.
 */
class FormatadorDataHora {

    static String toTime(Duration duration){
        return new PeriodFormatterBuilder()
                .printZeroAlways()
                .minimumPrintedDigits(2)
                .appendHours()
                .appendSeparator(":")
                .appendMinutes()
                .appendSeparator(":")
                .toFormatter()
                .print(duration.toPeriod())
    }

    static String toDate(LocalDate ld){
        return   new DateTimeFormatterBuilder()
                .appendDayOfMonth(2)
                .appendLiteral('/')
                .appendMonthOfYear(2)
                .appendLiteral('/')
                .appendYear(4, 4)
                .toFormatter().print(ld)
    }

    static String toDateTime(LocalDate ld, LocalTime lt){
        String data = toDate(ld)
        String hora = toTime(new Period(lt.getHourOfDay(),
                lt.getMinuteOfHour(),
                lt.getSecondOfMinute(), 0).toStandardDuration())
        return "${data} ${hora}"

    }

}
