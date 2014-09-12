package com.codeminders.labs.timeextractor.utils;

import java.util.Calendar;

import org.joda.time.LocalDate;

import com.codeminders.labs.timeextractor.constants.Holidays;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.constants.WeekOfMonth;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TimeDate;

public class TemporalParser {

    public Temporal getSeason(String season, int year) {

        if (season == null || season.isEmpty()) {
            return null;
        }

        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        if (season.equalsIgnoreCase("Summer")) {
            Date startDate = new Date(year, 6, 1);
            Date endDate = new Date(year, 8, 31);
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);

        }
        if (season.equalsIgnoreCase("Winter")) {
            Date startDate = new Date(year, 12, 1);
            // as end date will be in new year
            year = year + 1;
            boolean leap = isLeapYear(year);
            Date endDate = new Date(year, 2, 28);
            if (leap) {
                endDate = new Date(2, 29);
            }
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);
        }
        if (season.equalsIgnoreCase("Autumn") || season.equalsIgnoreCase("Fall")) {
            Date startDate = new Date(year, 9, 1);
            Date endDate = new Date(year, 11, 30);
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);
        }
        if (season.equalsIgnoreCase("Spring")) {
            Date startDate = new Date(year, 3, 1);
            Date endDate = new Date(year, 5, 31);
            start.setDate(startDate);
            end.setDate(endDate);
            return new Temporal(start, end);
        }
        return null;
    }

    public boolean isLeapYear(int year) {
        if (year == 0) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(cal.DAY_OF_YEAR) > 365;
    }

    public Temporal getHoliday(String holidayName) {

        if (holidayName == null || holidayName.isEmpty()) {
            return null;
        }
        Temporal temporal = null;
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        if (holidayName.equalsIgnoreCase(Holidays.NEW_YEAR)) {
            Date startDate = new Date(0, 1, 1);
            Date endDate = new Date(0, 1, 1);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }
        if (holidayName.equalsIgnoreCase(Holidays.HALLOWEEN)) {
            Date startDate = new Date(0, 10, 31);
            Date endDate = new Date(0, 10, 31);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
        }

        if (holidayName.equalsIgnoreCase(Holidays.ST_VALENTINE)) {
            Date startDate = new Date(0, 2, 14);
            Date endDate = new Date(0, 2, 14);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
        }

        if (holidayName.equalsIgnoreCase(Holidays.CHRISTMAS)) {
            Date startDate = new Date(0, 12, 25);
            Date endDate = new Date(0, 12, 25);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
        }

        if (holidayName.equalsIgnoreCase(Holidays.THANKSGIVING)) {
            Date startDate = new Date(0, 11, 0);
            startDate.setWeekOfMonth(WeekOfMonth.FOURTH);
            Date endDate = new Date(0, 11, 0);
            start.setDate(startDate);
            end.setDate(endDate);
            endDate.setWeekOfMonth(WeekOfMonth.FOURTH);
            temporal = new Temporal(start, end);
        }

        if (holidayName.equalsIgnoreCase(Holidays.INDEPENDENCE_DAY)) {
            Date startDate = new Date(0, 7, 4);
            Date endDate = new Date(0, 7, 4);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }
        if (temporal != null) {
            temporal.setType(Type.DATE);
        }
        return temporal;
    }

    public Temporal getRelativeTemporalObjectByProperty(String property, LocalDate localDate) {

        if (property.equalsIgnoreCase("today")) {
            TimeDate timeDate = getTimeDate(localDate);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);

        }
        if (property.equalsIgnoreCase("tomorrow")) {
            localDate = localDate.plusDays(1);
            TimeDate timeDate = getTimeDate(localDate);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        }

        if (property.equalsIgnoreCase("yesterday")) {
            localDate = localDate.minusDays(1);
            TimeDate timeDate = getTimeDate(localDate);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);

        }

        if (property.equalsIgnoreCase("the day before yesterday")) {
            localDate = localDate.minusDays(2);
            TimeDate timeDate = getTimeDate(localDate);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        }
        return null;
    }

    // Method returns TimeDate from localDate

    private TimeDate getTimeDate(LocalDate localDate) {
        TimeDate timeDate = new TimeDate();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthOfYear();
        int year = localDate.getYear();
        timeDate.setRelative(true);
        Date date = new Date(year, month, day);
        timeDate.setDate(date);
        return timeDate;

    }

}