package cn.ezid.cert.app.executor.adapter.nciic.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ROWS")
public class Response {

	@XmlElements({ @XmlElement(name = "ROW", type = ResponseRow.class) })
	private List<ResponseRow> rows;

	public List<ResponseRow> getRows() {
		return rows;
	}

	public void setRows(List<ResponseRow> rows) {
		this.rows = rows;
	}

}
