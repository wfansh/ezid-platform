package cn.ezid.cert.app.executor.adapter.nciic.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "INPUT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Input {

	@XmlElement(name = "gmsfhm")
	private String gmsfhm;

	@XmlElement(name = "xm")
	private String xm;

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
