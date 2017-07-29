package cn.ezid.cert.app.executor.adapter.nciic.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RESPONSE")
public class ExceptionalResponse {

	@XmlAttribute(name = "errorcode")
	private String errorCode;

	@XmlAttribute(name = "code")
	private int code;

	@XmlAttribute(name = "countrows")
	private int countrows;

	@XmlElementWrapper(name = "ROWS")
	@XmlElements({ @XmlElement(name = "ROW", type = ResponseRow.class) })
	List<ResponseRow> rows;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCountrows() {
		return countrows;
	}

	public void setCountrows(int countrows) {
		this.countrows = countrows;
	}

	public List<ResponseRow> getRows() {
		return rows;
	}

	public void setRows(List<ResponseRow> rows) {
		this.rows = rows;
	}

}
