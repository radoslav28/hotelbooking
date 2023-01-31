package hotelbooking.config.jwt;

import hotelbooking.repositories.repositories.UserRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;

@Component
public class UserTokenIdManager {
    @Autowired
    private UserRepository userRepository;

    public int getIdFromToken(String token) throws org.apache.tomcat.util.json.ParseException {
        String bodyEncoded = token.split("\\.")[1];
        String payloadAsString = new String(Base64.getUrlDecoder().decode(bodyEncoded));

        Map<String, Object> payload = null;
        try {
            payload = new JSONParser(payloadAsString).parseObject();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String id = (String) payload.get("id");

        return Integer.parseInt(id);

    }

    public String getRoleFromToken(String token) throws org.apache.tomcat.util.json.ParseException {
        String bodyEncoded = token.split("\\.")[1];
        String payloadAsString = new String(Base64.getUrlDecoder().decode(bodyEncoded));

        Map<String, Object> payload = null;
        try {
            payload = new JSONParser(payloadAsString).parseObject();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String role = (String) payload.get("role");

        return role;

    }
}
