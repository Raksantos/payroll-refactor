package models.services.payment;

import strategy.ScheduleStrategy;

import java.io.Serializable;
import java.time.DayOfWeek;

public class PaymentSchedule implements Serializable{

    private Integer monthDay;

    private DayOfWeek weekDay;

    private String schedule;

    private ScheduleStrategy strategy;

    public PaymentSchedule(){

    }

    public PaymentSchedule(Integer monthDay, DayOfWeek weekDay, String schedule, ScheduleStrategy strategy) {
        this.monthDay = monthDay;
        this.weekDay = weekDay;
        this.schedule = schedule;
        this.strategy = strategy;
    }

    public Integer getMonthDay() {
        return monthDay;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public ScheduleStrategy getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return " Payment Schedule {" +
                "Day of the month: " + getMonthDay() +
                ", week day: " + getWeekDay() +
                ", kind of schedule: '" + getSchedule() + '\'' +
                '}';
    }
}