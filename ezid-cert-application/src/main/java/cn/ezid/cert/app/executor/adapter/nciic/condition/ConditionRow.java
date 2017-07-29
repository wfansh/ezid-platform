package cn.ezid.cert.app.executor.adapter.nciic.condition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ROW", propOrder = { "ywlx", "fsd", "gmsfhm", "xm" })
@XmlAccessorType(XmlAccessType.FIELD)
public class ConditionRow {

	@XmlAttribute(name = "FSD")
	private String fsd;

	@XmlAttribute(name = "YWLX")
	private String ywlx;

	@XmlElement(name = "GMSFHM")
	private String gmsfhm;

	@XmlElement(name = "XM")
	private String xm;

	public ConditionRow() {
		super();
	}

	public ConditionRow(String fsd, String ywlx, String gmsfhm, String xm) {
		super();
		this.fsd = fsd;
		this.ywlx = ywlx;
		this.gmsfhm = gmsfhm;
		this.xm = xm;
	}

	public ConditionRow(String gmsfhm, String xm) {
		super();
		this.gmsfhm = gmsfhm;
		this.xm = xm;
	}

	public String getFsd() {
		return fsd;
	}

	public void setFsd(String fsd) {
		this.fsd = fsd;
	}

	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getGmsfhm() {
		return gmsfhm;
	}

	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
}
