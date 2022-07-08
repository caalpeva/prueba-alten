package kalpeva.sampleforalten;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class Settings {
	
	public static final String DATE_PATTERN = "yyyy-MM-dd-HH.mm.ss";
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);	
	
	@Autowired
	public void configureJackson(ObjectMapper objectMapper) {
		objectMapper.setTimeZone(TimeZone.getDefault());
	}
}
