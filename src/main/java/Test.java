import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalQuery;

/**
 * Created by shimizukazuki on 2015/09/28.
 */
public class Test {

	public static void main(String... args) throws ParseException {
		String v = "2015/09";
		String f = "yyyy/MM";
		System.out.println(new SimpleDateFormat(f).parse(v));
		System.out.println(YearMonth.parse(v, DateTimeFormatter.ofPattern(f)));

		System.out.println(LocalDate.from(YearMonth.parse(v, DateTimeFormatter.ofPattern(f)).atDay(1)));

	}
}
