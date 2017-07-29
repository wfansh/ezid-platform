package cn.ezid.cert.app.executor.adapter.nciic.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ROW")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseRow {

	@XmlAttribute(name = "no")
	private int no;

	@XmlElement(name = "INPUT")
	private Input input;

	@XmlElementWrapper(name = "OUTPUT")
	@XmlElement(name = "ITEM")
	List<Item> items;

	@XmlElement(name = "ErrorCode")
	String errorCode;

	@XmlElement(name = "ErrorMsg")
	String errorMsg;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
