package cn.ezid.cert.core.activiti;

public class TaskConstants {
	
	public static final String GROUP_PHOTO_PREPROCESS = "group_photo_preprocess";
	public static final String GROUP_PHOTO_ADAPTER = "group_photo_adapter";
	public static final String GROUP_MACHINE_CERTIFICATION = "group_machine_certification";
	public static final String GROUP_MANUAL_REVIEW = "group_manual_review";
	public static final String GROUP_MANUAL_CERTIFICATION = "group_manual_certification";
	public static final String GROUP_ADMIN= "admin";


	public static final String IN_VAR_PERSON_IDCARD_NUM = "in_personIdcardNum";
	public static final String IN_VAR_PERSON_PASSPORT_NUM = "in_personPassportNum";
	public static final String IN_VAR_PERSON_NAME = "in_personName";
	public static final String IN_VAR_PERSON_PHOTO = "in_personPhoto";
	public static final String IN_VAR_PERSON_PHOTO_SOURCE = "in_personPhotoSource";
	public static final String IN_VAR_PERSON_VIDEO = "in_personVideo";
	public static final String IN_VAR_PERSON_IDCARD_PRINT = "in_personIdcardPrint";
	public static final String IN_VAR_PERSON_PASSPORT_PRINT = "in_personPassportPrint";
	public static final String IN_VAR_PERSON_GENDER = "in_personGender";
	public static final String IN_VAR_PERSON_BIRTHDATE = "in_personBirthdate";
	public static final String IN_VAR_DEPARTMENT = "in_department";
	public static final String IN_VAR_ADAPTER_PHOTO_IGNORED = "in_adapterPhotoIgnored";
	public static final String IN_VAR_MACHINE_CERTIFICATION_THRESHOLD = "in_machineCertificationThreshold";
	public static final String IN_VAR_CERT_CALLBACK = "in_certCallback";
	public static final String IN_VAR_BUSINESS_TYPE = "in_businessType";
	public static final String IN_VAR_DEBUG_MODE = "in_debugMode";
	public static final String IN_VAR_EXTRA = "in_extra";
	public static final String COR_VAR_PERSON_IDCARD_PHOTO = "cor_personIdcardPhoto";
	public static final String OUT_VAR_TASK_RESULT = "out_taskResult";
	public static final String OUT_VAR_TASK_RESULT_CERTAINTY = "out_taskResultCertainty";
	public static final String OUT_VAR_TASK_RESULT_DESC = "out_taskResultDesc";
	public static final String OUT_VAR_TASK_JOURNAL = "out_taskJournal";
	public static final String OUT_VAR_MACHINE_CERTIFICATION_RESULT = "out_machineCertificationResult";

	public static final String TASK_DEFINITION_KEY_PHOTO_PREPROCESS = "photo_preprocess";
	public static final String TASK_DEFINITION_KEY_PHOTO_ADAPTER = "get_idcard_photo";
	public static final String TASK_DEFINITION_KEY_IDCARD_PHOTO_IDENTIFICATION = "idcard_photo_identification";
	public static final String TASK_DEFINITION_KEY_MACHINE_CERTIFICATION = "machine_certification";
	public static final String TASK_DEFINITION_KEY_MANUAL_REVIEW = "manual_review";
	public static final String TASK_DEFINITION_KEY_MANUAL_CERTIFICATION = "manual_certification";
	public static final String TASK_DEFINITION_KEY_MANUAL_LIKE = "manual_%";
	public static final String TASK_NAME_MANUAL_LIKE = "照片人工%";
}
