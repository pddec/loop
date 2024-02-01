package loop.update.trigger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.h2.api.Trigger;

public class UpdateTimestamp implements Trigger {

	public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
		
		final String sql = "UPDATE public.bill b SET update_ts = ? where b.id=?";
		
		final PreparedStatement state = conn.prepareStatement(sql);
		
		final Timestamp updateTs = Timestamp.valueOf(LocalDateTime.now());
		
		final Long id = (Long)oldRow[0];
		
		state.setTimestamp(1, updateTs);
		state.setLong(2,id);
		
		state.executeUpdate();
		
		state.close();
		conn.close();
	}

}
