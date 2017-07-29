package cn.ezid.cert.app.executor.adapter.nciic.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ITEM")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

	@XmlElement(name = "gmsfhm")
	private String gmsfhm;

	@XmlElement(name = "result_gmsfhm")
	private String resultGmsfhm;

	@XmlElement(name = "xm")
	private String xm;

	@XmlElement(name = "result_xm")
	private String resultXm;

	@XmlElement(name = "xp")
	private String xp;

	@XmlElement(name = "errormesage")
	private String errormesage;

	@XmlElement(name = "errormesagecol")
	private String errormesagecol;

	public String getGmsfhm() {
		return gmsfhm;
	}

	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}

	public String getResultGmsfhm() {
		return resultGmsfhm;
	}

	public void setResultGmsfhm(String resultGmsfhm) {
		this.resultGmsfhm = resultGmsfhm;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getResultXm() {
		return resultXm;
	}

	public void setResultXm(String resultXm) {
		this.resultXm = resultXm;
	}

	public String getXp() {
		return xp;
	}

	public void setXp(String xp) {
		this.xp = xp;
	}

	public String getErrormesage() {
		return errormesage;
	}

	public void setErrormesage(String errormesage) {
		this.errormesage = errormesage;
	}

	public String getErrormesagecol() {
		return errormesagecol;
	}

	public void setErrormesagecol(String errormesagecol) {
		this.errormesagecol = errormesagecol;
	}
}
