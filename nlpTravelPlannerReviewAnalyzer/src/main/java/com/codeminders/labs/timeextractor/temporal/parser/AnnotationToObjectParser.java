package com.codeminders.labs.timeextractor.temporal.parser;

import java.util.List;

import com.codeminders.labs.timeextractor.rules.date.DayOfWeekOrderRule1;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekOrderRule2;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule1;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule2;
import com.codeminders.labs.timeextractor.rules.date.DayOrderAndMonthRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule2;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule3;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule4;
import com.codeminders.labs.timeextractor.rules.date.MonthAndYearRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthOfYear1;
import com.codeminders.labs.timeextractor.rules.date.YearRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class AnnotationToObjectParser {

    public List<Temporal> getTemporal(TemporalExtraction temporal) {
        List<Temporal> result = null;
        String extractedText = temporal.getTemporalExpression();
        switch (temporal.getClassOfRuleType()) {

        // 2009
        case ("YearRule"):
            YearRule rule = new YearRule(extractedText);
            result = rule.getTemporal();
            break;

        // October
        case ("MonthOfYear1"):
            MonthOfYear1 rule1 = new MonthOfYear1(extractedText);
            result = rule1.getTemporal();
            break;

        // October 2012
        case ("MonthAndYearRule1"):
            MonthAndYearRule1 rule2 = new MonthAndYearRule1(extractedText);
            result = rule2.getTemporal();
            break;

        // July 14
        case ("MonthAndDayRule1"):
            MonthAndDayRule1 rule3 = new MonthAndDayRule1(extractedText);
            result = rule3.getTemporal();
            break;

        // 14 July
        case ("MonthAndDayRule2"):
            MonthAndDayRule2 rule4 = new MonthAndDayRule2(extractedText);
            result = rule4.getTemporal();
            break;

        // Sunday
        case ("DayOfWeekRule1"):
            DayOfWeekRule1 rule5 = new DayOfWeekRule1(extractedText);
            result = rule5.getTemporal();
            break;

        // the 20th of january
        case ("MonthAndDayRule3"):
            MonthAndDayRule3 rule6 = new MonthAndDayRule3(extractedText);
            result = rule6.getTemporal();
            break;

        // Sunday 16
        case ("DayOfWeekRule2"):
            DayOfWeekRule2 rule7 = new DayOfWeekRule2(extractedText);
            result = rule7.getTemporal();
            break;

        // Jan. 20 2014
        case ("MonthAndDayRule4"):
            MonthAndDayRule4 rule8 = new MonthAndDayRule4(extractedText);
            result = rule8.getTemporal();
            break;

        // the first Tuesday (of the month)
        case ("DayOfWeekOrderRule1"):
            DayOfWeekOrderRule1 rule9 = new DayOfWeekOrderRule1(extractedText);
            result = rule9.getTemporal();
            break;

        // the 1st Tuesday (of the month)
        case ("DayOfWeekOrderRule2"):
            DayOfWeekOrderRule2 rule10 = new DayOfWeekOrderRule2(extractedText);
            result = rule10.getTemporal();
            break;

        // the first of December 2011
        case ("DayOrderAndMonthRule1"):
            DayOrderAndMonthRule1 rule11 = new DayOrderAndMonthRule1(extractedText);
            result = rule11.getTemporal();
            break;
        }

        return result;
    }

}
