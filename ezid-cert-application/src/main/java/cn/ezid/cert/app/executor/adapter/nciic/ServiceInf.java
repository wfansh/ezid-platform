package cn.ezid.cert.app.executor.adapter.nciic;

/**
 * @author Fan
 *
 */
public interface ServiceInf {
	public String nciicCheck(String inLicense, String inConditions);
	public String nciicGetCondition(String inLicense);
}
