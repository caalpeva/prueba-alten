package kalpeva.sampleforalten.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kalpeva.sampleforalten.model.Price;

public interface PriceRepository extends JpaRepository<Price, Integer> {
	
	List<Price> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(Integer productId, Integer brandId, Date startDate, Date endDate);	
}