/**
 * IdentifierServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.ezid.cert.app.executor.adapter.loong.axis;

public interface IdentifierServiceSoap extends java.rmi.Remote {

	/**
	 * 单笔查询
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.CheckResponse singleCheck(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 取得缓存数据的记录条�?
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse queryCizitenDataCount(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryCizizenRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 取得缓存中的数据
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryCitizenResponse queryCizitenData(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryCizizenRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 是否查询过该用户的资�?
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.IsCitizenExistsResponseCode isCitizenExists(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 取得�?��的一次历史记�?
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse getCitizenDataAndWriteLog(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	public cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse getCitizenData(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 查询消费历史
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryResponse queryHistoryData(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 查询消费历史(记录条数)
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse queryHistoryDataCount(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 查询余额
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeResponse queryFeeData(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 用户登录
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.LoginResponse login(
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData request) throws java.rmi.RemoteException;

	/**
	 * 修改密码
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.LoginResposeCode modifyPassword(
			cn.ezid.cert.app.executor.adapter.loong.axis.ModifyPwdRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 查询账号
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountResponse queryAccount(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 创建�?��新的账号
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode createNewAccount(
			cn.ezid.cert.app.executor.adapter.loong.axis.AccountData data,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 修改账号资料
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode modifyAccount(
			cn.ezid.cert.app.executor.adapter.loong.axis.AccountData data,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException;

	/**
	 * 拨款
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode allocateFunds(java.lang.String userIdAllocatee,
			java.math.BigDecimal money, cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData)
			throws java.rmi.RemoteException;

	/**
	 * 版本�?
	 */
	public java.lang.String getVersion() throws java.rmi.RemoteException;

	/**
	 * 版本�?
	 */
	public void getVersion_1_9_8_20T() throws java.rmi.RemoteException;
}
