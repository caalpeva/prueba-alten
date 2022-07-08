package kalpeva.sampleforalten.service.memory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kalpeva.sampleforalten.model.Price;
import kalpeva.sampleforalten.repository.PriceRepository;
import kalpeva.sampleforalten.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Override
	public List<Price> list() {
		return priceRepository.findAll();
	}
	
	@Override
	public Optional<Price> getPriceToApply(Integer productId, Integer brandId, Date date) {
		List<Price> prices = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
				productId, brandId, date, date);
		if (prices.size() > 0) {
			return Optional.of(prices.get(0));
		}
		
		return Optional.empty();
	}
}
