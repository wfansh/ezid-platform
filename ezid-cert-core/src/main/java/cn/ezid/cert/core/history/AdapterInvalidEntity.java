package cn.ezid.cert.core.history;

import org.apache.ibatis.type.Alias;

import cn.ezid.cert.core.entity.BaseEntity;

@Alias("AdapterInvalidEntity")
public class AdapterInvalidEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5055754380655594253L;

	private long id;
	private String idcardNum;
	private String name;
	private String error;
	private String message;

	public AdapterInvalidEntity() {
	}

	public AdapterInvalidEntity(String idcardNum, String name, String error, String message) {
		super();
		this.idcardNum = idcardNum;
		this.name = name;
		this.error = error;
		this.message = message;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdcardNum() {
		return idcardNum;
	}

	public void setIdcardNum(String idcardNum) {
		this.idcardNum = idcardNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
