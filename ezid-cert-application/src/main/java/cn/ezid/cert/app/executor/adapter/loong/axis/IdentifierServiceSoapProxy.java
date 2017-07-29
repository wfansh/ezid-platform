package cn.ezid.cert.app.executor.adapter.loong.axis;

public class IdentifierServiceSoapProxy implements cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoap {
	private String _endpoint = null;
	private cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoap identifierServiceSoap = null;

	public IdentifierServiceSoapProxy() {
		_initIdentifierServiceSoapProxy();
	}

	public IdentifierServiceSoapProxy(String endpoint) {
		_endpoint = endpoint;
		_initIdentifierServiceSoapProxy();
	}

	private void _initIdentifierServiceSoapProxy() {
		try {
			identifierServiceSoap = (new cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceLocator())
					.getIdentifierServiceSoap();
			if (identifierServiceSoap != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) identifierServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address",
							_endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) identifierServiceSoap)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (identifierServiceSoap != null)
			((javax.xml.rpc.Stub) identifierServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address",
					_endpoint);

	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoap getIdentifierServiceSoap() {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap;
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.CheckResponse singleCheck(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.singleCheck(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse queryCizitenDataCount(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryCizizenRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.queryCizitenDataCount(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryCitizenResponse queryCizitenData(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryCizizenRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.queryCizitenData(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.IsCitizenExistsResponseCode isCitizenExists(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.isCitizenExists(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse getCitizenDataAndWriteLog(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.getCitizenDataAndWriteLog(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse getCitizenData(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.getCitizenData(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryResponse queryHistoryData(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.queryHistoryData(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse queryHistoryDataCount(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.queryHistoryDataCount(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeResponse queryFeeData(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.queryFeeData(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.LoginResponse login(
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData request) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.login(request);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.LoginResposeCode modifyPassword(
			cn.ezid.cert.app.executor.adapter.loong.axis.ModifyPwdRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.modifyPassword(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountResponse queryAccount(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.queryAccount(request, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode createNewAccount(
			cn.ezid.cert.app.executor.adapter.loong.axis.AccountData data,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.createNewAccount(data, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode modifyAccount(
			cn.ezid.cert.app.executor.adapter.loong.axis.AccountData data,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.modifyAccount(data, userData);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode allocateFunds(java.lang.String userIdAllocatee,
			java.math.BigDecimal money, cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData)
			throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.allocateFunds(userIdAllocatee, money, userData);
	}

	public java.lang.String getVersion() throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		return identifierServiceSoap.getVersion();
	}

	public void getVersion_1_9_8_20T() throws java.rmi.RemoteException {
		if (identifierServiceSoap == null)
			_initIdentifierServiceSoapProxy();
		identifierServiceSoap.getVersion_1_9_8_20T();
	}

}