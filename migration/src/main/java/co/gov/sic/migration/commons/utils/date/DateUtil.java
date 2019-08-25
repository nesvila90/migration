package co.gov.sic.migration.commons.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String dateNowStringToString(Date fecha) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());
        return dateString;

    }

}
