package com.javarush.task.pro.task16.task1612;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class DateTimeGenerator {

    private static final long MIN_DAY = LocalDate.of(1990, 1, 1).toEpochDay(); //минимальный день для генерации даты (кол-во дней от 1970)
    private static final long MAX_DAY = LocalDate.of(2020, 12, 31).toEpochDay(); //максимальный день для генерации даты (кол-во дней от 1970)

    private static long maxNano = 86399999999999L; //максимальное количество наносекунд для генерации времени

    public static LocalDate generateDate() {
        long randomDay = ThreadLocalRandom.current().nextLong(MIN_DAY, MAX_DAY); //генерирование дня в диапазоне мин-макс (кол-во дней от 1970)
        return LocalDate.ofEpochDay(randomDay); //получение объекта LocalDate на основании сгенерированного дня
    }

    public static LocalTime generateTime() {
        long randomTime = ThreadLocalRandom.current().nextLong(0, maxNano); //генерация числа наносекунд от 0 до макс
        return LocalTime.ofNanoOfDay(randomTime); //получение объекта LocalDate на основании сгенерированного числа наносекунд
    }

    public static List<LocalTime> generateTimeList() { //генерация листа с объктами LocalTime (от 1 до 5)
        List<LocalTime> timeList = new ArrayList<>();
        int size = ThreadLocalRandom.current().nextInt(1, 5);
        for (int i = 0; i < size; i++) {
            timeList.add(generateTime());
        }
        return timeList;
    }

    public static Map<LocalDate, List<LocalTime>> generateDateMap() { //создание мапы с объектами LocalDate в качестве ключа и уже сгененированными листами LocalTime в качестве значения
        Map<LocalDate, List<LocalTime>> dateMap = new HashMap<>();
        int size = ThreadLocalRandom.current().nextInt(3, 7);
        for (int i = 0; i < size; i++) {
            dateMap.put(generateDate(), generateTimeList());
        }
        return dateMap;
    }
}
