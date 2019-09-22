package duke.util;

import duke.exceptions.DukeInvalidTimePeriodException;

import java.time.LocalDateTime;

public class TimePeriod {
    private LocalDateTime begin;
    private LocalDateTime end;

    public TimePeriod(LocalDateTime begin, LocalDateTime end) throws DukeInvalidTimePeriodException {
        if (end.isBefore(begin)) {
            throw new DukeInvalidTimePeriodException("End before begin!");
        }
        this.begin = begin;
        this.end = end;
    }

    public boolean isClashing(LocalDateTime localDateTime, boolean strictBegin, boolean strictEnd) {
        return localDateTime.isAfter(this.begin) && localDateTime.isBefore(this.end)
                || strictBegin && localDateTime.isEqual(this.begin)
                || strictEnd && localDateTime.isEqual(this.end);
    }

    public boolean isClashing(LocalDateTime localDateTime) {
        return this.isClashing(localDateTime, false, false);
    }

    public boolean isClashing(TimePeriod other) {
        return other.isClashing(this.begin) || other.isClashing(this.end);
    }

    public LocalDateTime getBegin() {
        return this.begin;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return this.end.isBefore(now) || this.end.isEqual(now);
    }
}