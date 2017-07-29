package cn.ezid.cert.api.model.rest;

import java.util.List;

public class ProcessListObject {
	private List<ProcessObject> data;
	private int total;
	private int start;
	private String sort;
	private String order;
	private int size;

	public List<ProcessObject> getData() {
		return data;
	}

	public void setData(List<ProcessObject> data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
