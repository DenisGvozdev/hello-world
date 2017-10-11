package ru.proghouse.cayenne.persistent;

import ru.proghouse.cayenne.persistent.auto._Artist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Artist extends _Artist {

    static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";

    /**
     * Устанавливает дату рождения используя строку формата yyyyMMdd.
     */
    public void setDateOfBirthString(String yearMonthDay)
    {
        if (yearMonthDay == null)
            setDateOfBirth(null);
        else
        {
            Date date;
            try
            {
                date = new SimpleDateFormat(DEFAULT_DATE_FORMAT)
                        .parse(yearMonthDay);
            }
            catch (ParseException e)
            {
                throw new IllegalArgumentException(
                        "Дата должна быть указана в формате '"
                                + DEFAULT_DATE_FORMAT + "': " + yearMonthDay);
            }
            setDateOfBirth(date);
        }
    }

}
