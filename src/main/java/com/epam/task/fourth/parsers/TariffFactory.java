package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.PensionerTariff;
import com.epam.task.fourth.entities.StudentTariff;
import com.epam.task.fourth.entities.Tariff;

public class TariffFactory {

    private final static String STUDENT_TARIFF = "student-tariff";
    private final static String PENSIONER_TARIFF = "pensioner-tariff";

    public static Tariff create(String type){
        switch (type){
            case STUDENT_TARIFF:
                return new StudentTariff();
            case PENSIONER_TARIFF:
                return new PensionerTariff();
            default:
                return new Tariff();
        }
    }
}
