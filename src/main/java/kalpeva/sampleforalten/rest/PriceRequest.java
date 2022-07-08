package kalpeva.sampleforalten.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import kalpeva.sampleforalten.Settings;

public class PriceRequest {

	private Integer productId;
	private Integer brandId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Settings.DATE_PATTERN)
	private Date date;
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getBrandId() {
		return brandId;
	}
	
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "PriceRequest [productId=" + productId + ", brandId=" + brandId + ", date=" + date + "]";
	}	
}
