package isaapp.g3malt.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailDetails {

    public EmailDetails(String email, String msg, String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
