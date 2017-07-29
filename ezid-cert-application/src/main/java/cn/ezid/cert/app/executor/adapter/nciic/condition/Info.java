package cn.ezid.cert.app.executor.adapter.nciic.condition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Fan
 * 
 */
@XmlType(name = "INFO", propOrder = { "sbm" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Info {

	@XmlElement(name = "SBM")
	private String sbm;

	public Info() {
		super();
	}

	public Info(String sbm) {
		super();
		this.sbm = sbm;
	}

	public String getSbm() {
		return sbm;
	}

	public void setSbm(String sbm) {
		this.sbm = sbm;
	}
}
