/**
 * IdentifierServiceSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.ezid.cert.app.executor.adapter.loong.axis;

public class IdentifierServiceSoapStub extends org.apache.axis.client.Stub implements
		cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoap {
	@SuppressWarnings("rawtypes")
	private java.util.Vector cachedSerClasses = new java.util.Vector();
	@SuppressWarnings("rawtypes")
	private java.util.Vector cachedSerQNames = new java.util.Vector();
	@SuppressWarnings("rawtypes")
	private java.util.Vector cachedSerFactories = new java.util.Vector();
	@SuppressWarnings("rawtypes")
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc[] _operations;

	static {
		_operations = new org.apache.axis.description.OperationDesc[17];
		_initOperationDesc1();
		_initOperationDesc2();
	}

	private static void _initOperationDesc1() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("SingleCheck");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "CheckRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "CheckResponse"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.CheckResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "SingleCheckResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("QueryCizitenDataCount");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "QueryCizizenRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.QueryCizizenRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryRecordCountResponse"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryCizitenDataCountResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("QueryCizitenData");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "QueryCizizenRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.QueryCizizenRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryCitizenResponse"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.QueryCitizenResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryCizitenDataResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("IsCitizenExists");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "CheckRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "IsCitizenExistsResponseCode"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.IsCitizenExistsResponseCode.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "IsCitizenExistsResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetCitizenDataAndWriteLog");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "CheckRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "GetCitizenResponse"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "GetCitizenDataAndWriteLogResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[4] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetCitizenData");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "CheckRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "GetCitizenResponse"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "GetCitizenDataResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[5] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("QueryHistoryData");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "QueryHistoryRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryHistoryResponse"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryHistoryDataResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[6] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("QueryHistoryDataCount");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "QueryHistoryRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryRecordCountResponse"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryHistoryDataCountResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[7] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("QueryFeeData");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "QueryFeeRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryFeeResponse"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryFeeDataResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[8] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Login");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "LoginResponse"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.LoginResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "LoginResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[9] = oper;

	}

	private static void _initOperationDesc2() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ModifyPassword");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "ModifyPwdRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.ModifyPwdRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "LoginResposeCode"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.LoginResposeCode.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "ModifyPasswordResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[10] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("QueryAccount");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "QueryAccountRequest"),
				cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountRequest.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryAccountResponse"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryAccountResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[11] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("CreateNewAccount");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"data"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "AccountData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.AccountData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "ResponseCode"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "CreateNewAccountResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[12] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ModifyAccount");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"data"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "AccountData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.AccountData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "ResponseCode"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "ModifyAccountResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[13] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("AllocateFunds");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userIdAllocatee"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"money"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.zonrong.org/",
				"userData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.zonrong.org/", "LoginUserData"),
				cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.zonrong.org/", "ResponseCode"));
		oper.setReturnClass(cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "AllocateFundsResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[14] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetVersion");
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://www.zonrong.org/", "GetVersionResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[15] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetVersion_1_9_8_20t");
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[16] = oper;

	}

	public IdentifierServiceSoapStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public IdentifierServiceSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
			throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public IdentifierServiceSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		javax.xml.namespace.QName qName2;
		java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
		java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
		java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
		java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
		java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
		java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "AccountData");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.AccountData.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "ArrayOfAccountData");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.AccountData[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "AccountData");
		qName2 = new javax.xml.namespace.QName("http://www.zonrong.org/", "AccountData");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "ArrayOfHistoryData");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.HistoryData[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "HistoryData");
		qName2 = new javax.xml.namespace.QName("http://www.zonrong.org/", "HistoryData");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "ArrayOfIdentifierData");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierData[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "IdentifierData");
		qName2 = new javax.xml.namespace.QName("http://www.zonrong.org/", "IdentifierData");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "CheckRequest");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "CheckResponse");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.CheckResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "GetCitizenResponse");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "HistoryData");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.HistoryData.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "IdentifierData");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierData.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "IsCitizenExistsResponseCode");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.IsCitizenExistsResponseCode.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "LoginResponse");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.LoginResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "LoginResposeCode");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.LoginResposeCode.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "LoginUserData");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "ModifyPwdRequest");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.ModifyPwdRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "PagedRequest");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.PagedRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryAccountRequest");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryAccountResponse");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryCitizenResponse");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.QueryCitizenResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryCizizenRequest");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.QueryCizizenRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryFeeRequest");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryFeeResponse");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryHistoryRequest");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryHistoryResponse");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryRecordCountResponse");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "ResponseBase");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.ResponseBase.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://www.zonrong.org/", "ResponseCode");
		cachedSerQNames.add(qName);
		cls = cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

	}

	@SuppressWarnings("rawtypes")
	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet) {
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				_call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				java.lang.String key = (java.lang.String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized (this) {
				if (firstCall()) {
					// must set encoding style before registering serializers
					_call.setEncodingStyle(null);
					for (int i = 0; i < cachedSerFactories.size(); ++i) {
						java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
						javax.xml.namespace.QName qName = (javax.xml.namespace.QName) cachedSerQNames.get(i);
						java.lang.Object x = cachedSerFactories.get(i);
						if (x instanceof Class) {
							java.lang.Class sf = (java.lang.Class) cachedSerFactories.get(i);
							java.lang.Class df = (java.lang.Class) cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						} else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories
									.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories
									.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}
			return _call;
		} catch (java.lang.Throwable _t) {
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.CheckResponse singleCheck(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/SingleCheck");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "SingleCheck"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.CheckResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.CheckResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.CheckResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse queryCizitenDataCount(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryCizizenRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/QueryCizitenDataCount");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryCizitenDataCount"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryCitizenResponse queryCizitenData(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryCizizenRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/QueryCizitenData");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryCizitenData"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryCitizenResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryCitizenResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.QueryCitizenResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.IsCitizenExistsResponseCode isCitizenExists(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/IsCitizenExists");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "IsCitizenExists"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.IsCitizenExistsResponseCode) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.IsCitizenExistsResponseCode) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									cn.ezid.cert.app.executor.adapter.loong.axis.IsCitizenExistsResponseCode.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse getCitizenDataAndWriteLog(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/GetCitizenDataAndWriteLog");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "GetCitizenDataAndWriteLog"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse getCitizenData(
			cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/GetCitizenData");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "GetCitizenData"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.GetCitizenResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryResponse queryHistoryData(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/QueryHistoryData");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryHistoryData"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse queryHistoryDataCount(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryHistoryRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[7]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/QueryHistoryDataCount");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryHistoryDataCount"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.QueryRecordCountResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeResponse queryFeeData(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[8]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/QueryFeeData");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryFeeData"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.LoginResponse login(
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData request) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[9]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/Login");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "Login"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.LoginResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.LoginResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.LoginResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.LoginResposeCode modifyPassword(
			cn.ezid.cert.app.executor.adapter.loong.axis.ModifyPwdRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[10]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/ModifyPassword");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "ModifyPassword"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.LoginResposeCode) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.LoginResposeCode) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.LoginResposeCode.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountResponse queryAccount(
			cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountRequest request,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[11]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/QueryAccount");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "QueryAccount"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { request, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.QueryAccountResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode createNewAccount(
			cn.ezid.cert.app.executor.adapter.loong.axis.AccountData data,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[12]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/CreateNewAccount");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "CreateNewAccount"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { data, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode modifyAccount(
			cn.ezid.cert.app.executor.adapter.loong.axis.AccountData data,
			cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[13]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/ModifyAccount");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "ModifyAccount"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { data, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode allocateFunds(java.lang.String userIdAllocatee,
			java.math.BigDecimal money, cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData userData)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[14]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/AllocateFunds");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "AllocateFunds"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { userIdAllocatee, money, userData });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode) _resp;
				} catch (java.lang.Exception _exception) {
					return (cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode) org.apache.axis.utils.JavaUtils
							.convert(_resp, cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String getVersion() throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[15]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/GetVersion");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "GetVersion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void getVersion_1_9_8_20T() throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[16]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://www.zonrong.org/GetVersion_1_9_8_20t");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://www.zonrong.org/", "GetVersion_1_9_8_20t"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			}
			extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

}
