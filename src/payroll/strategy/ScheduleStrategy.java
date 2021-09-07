package strategy;

import models.services.payment.PaymentSchedule;
import java.time.LocalDate;

public interface ScheduleStrategy {
    public int getMethodDiv();

    public boolean getDateInSchedule(PaymentSchedule employeeSchedule, int week, LocalDate current);
}
