package com.codeminders.labs.timeextractor.entities;

import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.temporal.entities.Temporal;

/* Received temporal expressions class */

public class TemporalExtraction implements Comparable<TemporalExtraction> {

    public TemporalExtraction() {
    }

    public TemporalExtraction(int fromPosition, int toPosition, Locale locale, double confidence, String classOfRuleType) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
        this.locale = locale;
        this.confidence = confidence;
        this.classOfRuleType = classOfRuleType;
    }

    public TemporalExtraction(RegexResult result) {
        Rule rule = result.getRule();
        fromPosition = result.getStart();
        classOfRuleType = result.getRuleName();
        toPosition = result.getEnd();
        locale = rule.getLocale();
        confidence = rule.getConfidence();
        temporal = rule.getTemporal(result.getText());
        temporalExpression = result.getText();
        this.rule = result.getRule();
        if (rule.getType() != null && getTemporal() != null && getTemporal().get(0) != null) {
            getTemporal().get(0).setType(rule.getType());
        }
    }

    private String temporalExpression;
    private int fromPosition;
    private int toPosition;
    private String classOfRuleType;
    private List<Temporal> temporal;
    private double confidence;
    private Locale locale;
    private Rule rule;

    public String getTemporalExpression() {
        return temporalExpression;
    }

    public void setTemporalExpression(String temporalExpression) {
        this.temporalExpression = temporalExpression;
    }

    public int getFromPosition() {
        return fromPosition;
    }

    public void setFromPosition(int fromPosition) {
        this.fromPosition = fromPosition;
    }

    public int getToPosition() {
        return toPosition;
    }

    public void setToPosition(int toPosition) {
        this.toPosition = toPosition;
    }

    public String getClassOfRuleType() {
        return classOfRuleType;
    }

    public void setClassOfRuleType(String classOfRuleType) {
        this.classOfRuleType = classOfRuleType;
    }

    public List<Temporal> getTemporal() {
        return temporal;
    }

    public void setTemporal(List<Temporal> temporal) {
        this.temporal = temporal;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return temporalExpression + ", " + classOfRuleType + ", " + temporal + ", " + fromPosition + ", " + toPosition;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public int compare(TemporalExtraction ext1, TemporalExtraction ext2) {
        int a = ext1.getFromPosition();
        int b = ext2.getFromPosition();
        int cmp = a > b ? +1 : a < b ? -1 : 0;
        if (cmp == 0) {
            return 1;
        }
        return cmp;
    }

    @Override
    public int compareTo(TemporalExtraction o) {
        return compare(this, o);
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

}
