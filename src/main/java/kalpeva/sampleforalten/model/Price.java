package kalpeva.sampleforalten.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import kalpeva.sampleforalten.Settings;

@Entity
@Table(name="PRICES")
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRICE_LIST")
	private Integer priceList;
	
	@Column(name="START_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Settings.DATE_PATTERN)
	private Date startDate;
	
	@Column(name="END_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Settings.DATE_PATTERN)
	private Date endDate;
	
	@Column(name="PRODUCT_ID")
	private Integer productId;
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name="PRIORITY")
	@JsonIgnore
	private boolean priority = true;
	
	@Column(name="PRICE")
	private Double price;
	
	@Column(name="CURR")
	private String currency;
	
	@OneToOne
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;
	
	public Price() {
	}
	
	public Integer getPriceList() {
		return priceList;
	}   
	
	public void setPriceList(Integer priceList) {
		this.priceList = priceList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}	

	@Override
	public String toString() {
		return "Price [priceList=" + priceList + ", startDate=" + startDate + ", endDate=" + endDate + ", productId="
				+ productId + ", priority=" + priority + ", price=" + price + ", currency=" + currency + ", brand="
				+ brand + "]";
	}

}