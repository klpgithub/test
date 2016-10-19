package com.youxuan.utils;


public class ItemBean {

	private String label;
	private String name;
	private String note;
	private String start;
	private String len;
	private String dataType;
	private String itemType;
	private String occurrences;
	private String decimal;
	private String decimalChar;
	private String zeroFill;
	private ValueSetBean valueSet;
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
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLen() {
		return len;
	}
	public void setLen(String len) {
		this.len = len;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public ValueSetBean getValueSet() {
		return valueSet;
	}
	public void setValueSet(ValueSetBean valueSet) {
		this.valueSet = valueSet;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getOccurrences() {
		return occurrences;
	}
	public void setOccurrences(String occurrences) {
		this.occurrences = occurrences;
	}
	public String getDecimal() {
		return decimal;
	}
	public void setDecimal(String decimal) {
		this.decimal = decimal;
	}
	public String getDecimalChar() {
		return decimalChar;
	}
	public void setDecimalChar(String decimalChar) {
		this.decimalChar = decimalChar;
	}
	public String getZeroFill() {
		return zeroFill;
	}
	public void setZeroFill(String zeroFill) {
		this.zeroFill = zeroFill;
	}

}
