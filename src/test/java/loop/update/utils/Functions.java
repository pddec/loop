package loop.update.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Functions {
	public static Timestamp now_plus(Long amount, String temporal) {
		try {
			final ChronoUnit timeUnit = ChronoUnit.valueOf(temporal.toUpperCase());
			final LocalDateTime plus = LocalDateTime.now()
					.plus(amount, timeUnit);
			return Timestamp.valueOf(plus);
		} catch(Exception e) {
			return Timestamp.valueOf(LocalDateTime.now());
		}
	}
}
