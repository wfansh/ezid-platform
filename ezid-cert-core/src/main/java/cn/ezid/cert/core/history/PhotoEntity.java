package cn.ezid.cert.core.history;

import org.apache.ibatis.type.Alias;

import cn.ezid.cert.core.entity.BaseEntity;

@Alias("PhotoEntity")
public class PhotoEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2726610522922236737L;
	
	private long id;
	private String idcardNum;
	private String name;
	private String photoUri;
	private PhotoEntityType photoType;

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
	public String getPhotoUri() {
		return photoUri;
	}
	public void setPhotoUri(String photoUri) {
		this.photoUri = photoUri;
	}
	
	
	public PhotoEntityType getPhotoType() {
		return photoType;
	}
	public void setPhotoType(PhotoEntityType photoType) {
		this.photoType = photoType;
	}


	public enum PhotoEntityType {
		PHOTO_TYPE_IDCARD(0);
		private int value;

	    private PhotoEntityType(final int pValue) {
	        value = pValue;
	    }

		public int getValue() {
			return value;
		}
	}
}
