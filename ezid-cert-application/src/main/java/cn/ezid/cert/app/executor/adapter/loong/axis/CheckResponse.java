/**
 * CheckResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.ezid.cert.app.executor.adapter.loong.axis;

public class CheckResponse extends cn.ezid.cert.app.executor.adapter.loong.axis.ResponseBase implements
		java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3962894989004517501L;
	private cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierData identifier;

	public CheckResponse() {
	}

	public CheckResponse(cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode responseCode,
			cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierData identifier) {
		super(responseCode);
		this.identifier = identifier;
	}

	/**
	 * Gets the identifier value for this CheckResponse.
	 * 
	 * @return identifier
	 */
	public cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierData getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier value for this CheckResponse.
	 * 
	 * @param identifier
	 */
	public void setIdentifier(cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierData identifier) {
		this.identifier = identifier;
	}

	private java.lang.Object __equalsCalc = null;

	@SuppressWarnings("unused")
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof CheckResponse))
			return false;
		CheckResponse other = (CheckResponse) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = super.equals(obj)
				&& ((this.identifier == null && other.getIdentifier() == null) || (this.identifier != null && this.identifier
						.equals(other.getIdentifier())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = super.hashCode();
		if (getIdentifier() != null) {
			_hashCode += getIdentifier().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			CheckResponse.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.zonrong.org/", "CheckResponse"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identifier");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.zonrong.org/", "Identifier"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.zonrong.org/", "IdentifierData"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	@SuppressWarnings("rawtypes")
	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	@SuppressWarnings("rawtypes")
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
