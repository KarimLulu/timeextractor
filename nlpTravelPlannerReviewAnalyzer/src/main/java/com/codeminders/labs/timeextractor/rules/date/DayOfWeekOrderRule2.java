package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.DayOfWeek;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.constants.WeekOfMonth;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class DayOfWeekOrderRule2 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String weekOfMonth;
    private String dayOfWeek;

    public DayOfWeekOrderRule2(String weekOfMonth, String dayOfWeek) {
        this.weekOfMonth = weekOfMonth;
        this.dayOfWeek = dayOfWeek;

    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        DayOfWeek dayOfWeek = null;
        WeekOfMonth weekOfMonth = null;
        dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(this.dayOfWeek);
        weekOfMonth = TemporalBasicCaseParser.getWeekOfMonth(this.weekOfMonth);

        Date date = new Date();
        date.setDayOfWeek(dayOfWeek);
        date.setWeekOfMonth(weekOfMonth);

        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);

        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }
}