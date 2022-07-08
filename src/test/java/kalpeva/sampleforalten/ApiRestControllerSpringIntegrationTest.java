package kalpeva.sampleforalten;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import kalpeva.sampleforalten.model.Price;
import kalpeva.sampleforalten.repository.PriceRepository;
import kalpeva.sampleforalten.rest.PriceRequest;

@AutoConfigureMockMvc
@SpringBootTest
public class ApiRestControllerSpringIntegrationTest {
	 
    private static final String URI = "/api/prices";
    
	@Value("${server.port}")
    private int port;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private PriceRepository priceRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @ParameterizedTest
    @CsvSource({
    "2020-06-14-10.00.00,35455,1",
    "2020-06-14-16.00.00,35455,1",
    "2020-06-14-21.00.00,35455,1",
    "2020-06-15-10.00.00,35455,1",
    "2020-06-16-21.00.00,35455,1",
    })  
    void shouldReturnHttpCode200OnPost(String requestDate, Integer productId, Integer brandId) throws Exception {
    	Date date = Settings.SIMPLE_DATE_FORMAT.parse(requestDate);
    	List<Price> prices = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
    			productId,
    			brandId,
    			date,
    			date);
    	assertTrue(prices.size() >= 1);
    	
    	Price expectedPrice=prices.get(0);
    	assertNotNull(expectedPrice);
    	
    	PriceRequest request = new PriceRequest();
    	request.setProductId(expectedPrice.getProductId());
    	request.setBrandId(expectedPrice.getBrand().getId());
    	request.setDate(date);
    	
    	mockMvc.perform(
			MockMvcRequestBuilders.post(String.format("http://localhost:%s%s", port, URI))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(request)))
          	.andExpect(status().isOk())
          	.andExpect(jsonPath("$.productId", is(expectedPrice.getProductId())))
          	.andExpect(jsonPath("$.brand.id", is(expectedPrice.getBrand().getId())))
          	.andExpect(jsonPath("$.startDate", is(Settings.SIMPLE_DATE_FORMAT.format(expectedPrice.getStartDate()))))
          	.andExpect(jsonPath("$.endDate", is(Settings.SIMPLE_DATE_FORMAT.format(expectedPrice.getEndDate()))))
          	.andExpect(jsonPath("$.priceList", is(expectedPrice.getPriceList())))
          	.andExpect(jsonPath("$.currency", is(expectedPrice.getCurrency())))
          	.andReturn();
    }    
}