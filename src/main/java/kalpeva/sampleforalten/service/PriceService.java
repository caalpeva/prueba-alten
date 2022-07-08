package kalpeva.sampleforalten.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import kalpeva.sampleforalten.model.Price;

public interface PriceService {
	List<Price> list();
	Optional<Price> getPriceToApply(Integer productId, Integer brandId, Date date);
}
