import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FromDate {
// Определение юбилея 10000 дней и временного интервала от введённой даты до текущего момента;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату:");
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();
        LocalDate firstDate = LocalDate.of(year, month, day);
        Instant firstMillis = firstDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        LocalDate nowDate = LocalDate.now();
        Instant nowMillis = Instant.now();
        LocalDate jubilee = firstDate.plusDays(10000);
        long differenceMillis = nowMillis.toEpochMilli() - firstMillis.toEpochMilli();
        int diffenenceMonths = differenceOfMonths(firstDate, nowDate);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy ");
        System.out.println();
        System.out.println("Юбилей 10000 дней - " + dtf.format(jubilee));
        System.out.println();
        System.out.println("С " + dtf.format(firstDate) + "года прошло:");
        printYears(diffenenceMonths / 12);
        printMonths(diffenenceMonths);
        printWeeks(differenceMillis / 604800000);
        printDays(differenceMillis / 86400000);
        printHours(differenceMillis / 3600000);
        printMinutes(differenceMillis / 60000);
        printSeconds(differenceMillis / 1000);
        printMillis(differenceMillis);
    }

    static int differenceOfMonths(LocalDate first, LocalDate now) {
        int result = 0;
        while (now.isAfter(first)) {
            first = first.plusMonths(1);
            result++;
        }
        return result;
    }

    static void printYears(int years) {
        String result = " лет";
        if (years % 100 < 5 || years % 100 > 20) {
            if (years % 10 == 1) {
                result = " год";
            } else if (years % 10 < 5 && years % 10 > 1) {
                result = " года";
            }
        }
        System.out.println(years + result);
    }

    static void printMonths(int months) {
        String result = " месяцев";
        if (months % 100 < 5  || months % 100 > 20) {
            if (months % 10 == 1) {
                result = " месяц";
            } else if (months % 10 < 5 && months % 10 > 1) {
                result = " месяца";
            }
        }
        System.out.println(months + result);
    }

    static void printWeeks(long weeks) {
        String result = " недель";
        if (weeks % 100 < 5  || weeks % 100 > 20) {
            if (weeks % 10 == 1) {
                result = " неделя";
            } else if (weeks % 10 < 5 && weeks % 10 > 1) {
                result = " недели";
            }
        }
        System.out.println(weeks + result);
    }

    static void printDays(long days) {
        String result = " дней";
        if (days % 100 < 5  || days % 100 > 20) {
            if (days % 10 == 1) {
                result = " день";
            } else if (days % 10 < 5 && days % 10 > 1) {
                result = " дня";
            }
        }
        System.out.println(days + result);
    }

    static void printHours(long hours) {
        String result = " часов";
        if (hours % 100 < 5  || hours % 100 > 20) {
            if (hours % 10 == 1) {
                result = " час";
            } else if (hours % 10 < 5 && hours % 10 > 1) {
                result = " часа";
            }
        }
        System.out.println(hours + result);
    }

    static void printMinutes(long minutes) {
        String result = " минут";
        if (minutes % 100 < 5  || minutes % 100 > 20) {
            if (minutes % 10 == 1) {
                result = " минута";
            } else if (minutes % 10 < 5 && minutes % 10 > 1) {
                result = " минуты";
            }
        }
        System.out.println(minutes + result);
    }

    static void printSeconds(long seconds) {
        String result = " секунд";
        if (seconds % 100 < 5  || seconds % 100 > 20) {
            if (seconds % 10 == 1) {
                result = " секунда";
            } else if (seconds % 10 < 5 && seconds % 10 > 1) {
                result = " секунды";
            }
        }
        System.out.println(seconds + result);
    }

    static void printMillis(long millis) {
        String result = " миллисекунд";
        if (millis % 100 < 5  || millis % 100 > 20) {
            if (millis % 10 == 1) {
                result = " миллисекунда";
            } else if (millis % 10 < 5 && millis % 10 > 1) {
                result = " миллисекунды";
            }
        }
        System.out.println(millis + result);
    }
}
