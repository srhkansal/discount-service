package com.fedex.microservices.beans;

import javax.persistence.*;

@Entity
public class DiscountBean {
    @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

    @Column(name="start_date")
    private String start_date;

    @Column(name="end_date")
    private String end_date;

    @Column(name="display_name")
    private String display_name;

	@Column(name="description")
	private String description;

	@Column(name="type")
	private String type;

	@Column(name="amount")
	private double amount;

	public DiscountBean() {

	}

	public DiscountBean(Long id, String start_date, String end_date, String display_name, String description, String type, double amount) {
		this.id = id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.display_name = display_name;
		this.description = description;
		this.type = type;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "DiscountBean{" +
				"id=" + id +
				", start_date='" + start_date + '\'' +
				", end_date='" + end_date + '\'' +
				", display_name='" + display_name + '\'' +
				", description='" + description + '\'' +
				", type='" + type + '\'' +
				", amount=" + amount +
				'}';
	}
}
