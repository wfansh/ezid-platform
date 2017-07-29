package cn.ezid.cert.app.executor.adapter.nciic.condition;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "info", "rows" })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ROWS")
public class Condition {

	@XmlElement(name = "INFO")
	private Info info;

	@XmlElements({ @XmlElement(name = "ROW", type = ConditionRow.class) })
	private List<ConditionRow> rows;

	public Condition() {
		super();
	}

	public Condition(Info info, List<ConditionRow> rows) {
		super();
		this.info = info;
		this.rows = rows;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<ConditionRow> getRows() {
		return rows;
	}

	public void setRows(List<ConditionRow> rows) {
		this.rows = rows;
	}
}
