/**
 * IdentifierServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.ezid.cert.app.executor.adapter.loong.axis;

public class IdentifierServiceLocator extends org.apache.axis.client.Service implements
		cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1122551905036977627L;

	public IdentifierServiceLocator() {
	}

	public IdentifierServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public IdentifierServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for IdentifierServiceSoap
	private java.lang.String IdentifierServiceSoap_address = "http://203.148.57.104:8899/identifierservice.asmx";

	public java.lang.String getIdentifierServiceSoapAddress() {
		return IdentifierServiceSoap_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String IdentifierServiceSoapWSDDServiceName = "IdentifierServiceSoap";

	public java.lang.String getIdentifierServiceSoapWSDDServiceName() {
		return IdentifierServiceSoapWSDDServiceName;
	}

	public void setIdentifierServiceSoapWSDDServiceName(java.lang.String name) {
		IdentifierServiceSoapWSDDServiceName = name;
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoap getIdentifierServiceSoap()
			throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(IdentifierServiceSoap_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getIdentifierServiceSoap(endpoint);
	}

	public cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoap getIdentifierServiceSoap(
			java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoapStub _stub = new cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoapStub(
					portAddress, this);
			_stub.setPortName(getIdentifierServiceSoapWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setIdentifierServiceSoapEndpointAddress(java.lang.String address) {
		IdentifierServiceSoap_address = address;
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	@SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoap.class
					.isAssignableFrom(serviceEndpointInterface)) {
				cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoapStub _stub = new cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoapStub(
						new java.net.URL(IdentifierServiceSoap_address), this);
				_stub.setPortName(getIdentifierServiceSoapWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  "
				+ (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	@SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("IdentifierServiceSoap".equals(inputPortName)) {
			return getIdentifierServiceSoap();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://www.zonrong.org/", "IdentifierService");
	}

	@SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://www.zonrong.org/", "IdentifierServiceSoap"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address)
			throws javax.xml.rpc.ServiceException {

		if ("IdentifierServiceSoap".equals(portName)) {
			setIdentifierServiceSoapEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address)
			throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
