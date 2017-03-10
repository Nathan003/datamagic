package com.dodoca.dataMagic.wxrrd.model;
// default package


/**
 * ShopRapidanalysisSummary entity. @author MyEclipse Persistence Tools
 */

public class ShopRapidanalysisSummary implements java.io.Serializable {

	// Fields

	private String id;
	private String week_sequence;
	private String firstday_inWeek;
	private String shop_id;
	private String key_no;
	private String key_name;
	private String key_status;
	private String key_value;
//	private String week_sequence;
//	private String firstday_inWeek;
//	private String shop_id;
//	private String key_no;
//	private String key_name;
//	private String key_status;
//	private String key_value;

	// Constructors

	/** default constructor */
	public ShopRapidanalysisSummary() {
	}

	/** full constructor */
	public ShopRapidanalysisSummary(String week_sequence, String firstday_inWeek,
			String shop_id, String key_no, String key_name, String key_status,
			String key_value) {
		this.week_sequence = week_sequence;
		this.firstday_inWeek = firstday_inWeek;
		this.shop_id = shop_id;
		this.key_no = key_no;
		this.key_name = key_name;
		this.key_status = key_status;
		this.key_value = key_value;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getweek_sequence() {
		return this.week_sequence;
	}

	public void setweek_sequence(String week_sequence) {
		this.week_sequence = week_sequence;
	}

	public String getfirstday_inWeek() {
		return this.firstday_inWeek;
	}

	public void setfirstday_inWeek(String firstday_inWeek) {
		this.firstday_inWeek = firstday_inWeek;
	}

	public String getshop_id() {
		return this.shop_id;
	}

	public void setshop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getkey_no() {
		return this.key_no;
	}

	public void setkey_no(String key_no) {
		this.key_no = key_no;
	}

	public String getkey_name() {
		return this.key_name;
	}

	public void setkey_name(String key_name) {
		this.key_name = key_name;
	}

	public String getkey_status() {
		return this.key_status;
	}

	public void setkey_status(String key_status) {
		this.key_status = key_status;
	}

	public String getkey_value() {
		return this.key_value;
	}

	public void setkey_value(String key_value) {
		this.key_value = key_value;
	}

}