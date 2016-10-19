package com.youxuan.utils;

import java.util.ArrayList;
import java.util.List;


public class RecordBean {

	private String label;
	private String name;
	private String recordTypeValue;
	private String required;
	private String maxRecords;
	private String recordLen;
	private String note;
	private List<ItemBean> items;
	
	public RecordBean() {
		items = new ArrayList<ItemBean>();
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRecordTypeValue() {
		return recordTypeValue;
	}
	public void setRecordTypeValue(String recordTypeValue) {
		this.recordTypeValue = recordTypeValue;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getRecordLen() {
		return recordLen;
	}
	public void setRecordLen(String recordLen) {
		this.recordLen = recordLen;
	}
	public List<ItemBean> getItems() {
		return items;
	}
	public void setItems(List<ItemBean> items) {
		this.items = items;
	}
	public String getMaxRecords() {
		return maxRecords;
	}
	public void setMaxRecords(String maxRecords) {
		this.maxRecords = maxRecords;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
