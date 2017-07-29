package cn.ezid.cert.manual.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.ezid.cert.core.activiti.TaskConstants;
import cn.ezid.cert.core.activiti.model.HistoryTaskObject;
import cn.ezid.cert.core.activiti.model.TaskObject;
import cn.ezid.cert.core.activiti.model.VariableObject;
import cn.ezid.cert.core.util.EZUtils;
import cn.ezid.cert.core.util.ResourceEncoder;

public class TaskViewBean {
    private static final String THUMBNAIL_SUFFIX = "_s.jpg";
    private static final String FLV_VIDEO_SUFFIX = ".flv";

	private String processInstanceId;
	private String taskId;
	private String name;
	private String age;
	private String sex;
	private String idcardNum;
	private String passportNum;
	private String taskType;
	private String result;
	private String resultDesc;
	private String[] videoFilePaths;
	private String[] videoThumbFilePaths;
	private String[] photoFilePaths;
	private String[] photoThumbFilePaths;
	private String idcardPrintFilePath;
	private String idcardPrintThumbFilePath;
	private String passportPrintFilePath;
	private String passportPrintThumbFilePath;
	private String idcardPhotoFilePath;
	private String idcardPhotoThumbFilePath;
	
	private boolean processing;

	public TaskViewBean() {
		// TODO Auto-generated constructor stub
	}

	public TaskViewBean(TaskObject task) {
		this.setProcessInstanceId(task.getProcessInstanceId());
		this.setTaskId(task.getId());
		this.setVariables(task.getVariables());
		
		this.setProcessing(true);
	}

	public TaskViewBean(HistoryTaskObject task) {
		this.setProcessInstanceId(task.getProcessInstanceId());
		this.setTaskId(task.getId());
		this.setVariables(task.getVariables());
	}

	public TaskViewBean(TaskObject task, List<VariableObject> variables) {
		this.setProcessInstanceId(task.getProcessInstanceId());
		this.setTaskId(task.getId());
		this.setVariables(variables);
		
		this.setProcessing(true);
	}

	public void setVariables(List<VariableObject> variables) {
		for (VariableObject var : variables) {
			switch (var.getName()) {
			case TaskConstants.IN_VAR_PERSON_NAME: {
				this.setName(getStringFromVariables(var.getValue()));
				break;
			}
			case TaskConstants.IN_VAR_PERSON_IDCARD_NUM: {
				this.setIdcardNum(getStringFromVariables(var.getValue()));
				break;
			}
			case TaskConstants.IN_VAR_PERSON_PASSPORT_NUM: {
				this.setPassportNum(getStringFromVariables(var.getValue()));
				break;
			}
			case TaskConstants.IN_VAR_PERSON_GENDER: {
				this.setSex(getStringFromVariables(var.getValue()));
				break;
			}
			case TaskConstants.IN_VAR_PERSON_BIRTHDATE: {
				this.setAge(getAgeFromVariables(var.getValue()));
				break;
			}
			case TaskConstants.OUT_VAR_TASK_RESULT: {
				this.setResult(getStringFromVariables(var.getValue()));
				break;
			}
			case TaskConstants.OUT_VAR_TASK_RESULT_DESC: {
				this.setResultDesc(getStringFromVariables(var.getValue()));
				break;
			}
			case TaskConstants.COR_VAR_PERSON_IDCARD_PHOTO: {
				this.setIdcardPhotoFilePath(getStringFromVariables(var.getValue()));
				this.setIdcardPhotoThumbFilePath(getStringFromVariables(var.getValue()));
				break;
			}
			case TaskConstants.IN_VAR_PERSON_PHOTO: {
				this.setPhotoFilePaths(getStringFromVariables(var.getValue()).split(";"));
				this.setPhotoThumbFilePaths(getStringFromVariables(var.getValue()).split(";"));
				break;
			}
			case TaskConstants.IN_VAR_PERSON_VIDEO: {
				this.setVideoFilePaths(getStringFromVariables(var.getValue()).split(";"));
				this.setVideoThumbFilePaths(getStringFromVariables(var.getValue()).split(";"));
				break;
			}
			case TaskConstants.IN_VAR_PERSON_IDCARD_PRINT: {
				this.setIdcardPrintFilePath(getStringFromVariables(var.getValue()));
				this.setIdcardPrintThumbFilePath(getStringFromVariables(var.getValue()));
				break;
			}
			case TaskConstants.IN_VAR_PERSON_PASSPORT_PRINT: {
				this.setPassportPrintFilePath(getStringFromVariables(var.getValue()));
				this.setPassportPrintThumbFilePath(getStringFromVariables(var.getValue()));
				break;
			}
			}
		}
	}
	
	public void beforeRender() {		
		// 1. Person photo
		String[] originPhotoPaths = this.getPhotoFilePaths();
		if (originPhotoPaths.length > 0) {
			List<String> photoFilePaths = new ArrayList<>();
			List<String> photoFileThumbPaths = new ArrayList<>();
			for (String photoFilePath : originPhotoPaths) {
				photoFilePaths.add(ResourceEncoder.encode(photoFilePath));
				photoFileThumbPaths.add(ResourceEncoder.encode(photoFilePath + THUMBNAIL_SUFFIX));
			}

			this.setPhotoFilePaths(photoFilePaths.toArray(new String[] {}));
			this.setPhotoThumbFilePaths(photoFileThumbPaths.toArray(new String[] {}));
		}

		// 2. Video
		String[] originVideoPaths = this.getVideoFilePaths();
		if (originVideoPaths != null) {
        	List<String> videoFilePaths = new ArrayList<>();
        	List<String> videoFileThumbPaths = new ArrayList<>();
        	for (String videoFilePath : originVideoPaths) {
        		videoFilePaths.add(ResourceEncoder.encode(videoFilePath + FLV_VIDEO_SUFFIX));
        		videoFileThumbPaths.add(ResourceEncoder.encode(videoFilePath  + "_180" + FLV_VIDEO_SUFFIX));
        	}
        	
            this.setVideoFilePaths(videoFilePaths.toArray(new String[]{}));
            this.setVideoThumbFilePaths(videoFileThumbPaths.toArray(new String[]{}));
        }

		// 3. Idcard photo
		String originIdcardPhotoPath = this.getIdcardPhotoFilePath();
		if (EZUtils.isValidString(originIdcardPhotoPath)) {
			this.setIdcardPhotoFilePath(ResourceEncoder.encode(originIdcardPhotoPath));
			this.setIdcardPhotoThumbFilePath(ResourceEncoder.encode(originIdcardPhotoPath));
		}

		// 4. Idcard print
		String originIdcardPrintPath = this.getIdcardPrintFilePath();
		if (EZUtils.isValidString(originIdcardPrintPath)) {
			this.setIdcardPrintFilePath(ResourceEncoder.encode(originIdcardPrintPath));
			this.setIdcardPrintThumbFilePath(ResourceEncoder.encode(originIdcardPrintPath));
		}

		// 5. Passport print
		String originPassportPrintPath = this.getPassportPrintFilePath();
		if (EZUtils.isValidString(originPassportPrintPath)) {
			this.setPassportPrintFilePath(ResourceEncoder.encode(originPassportPrintPath));
			this.setPassportPrintThumbFilePath(ResourceEncoder.encode(originPassportPrintPath));
		}
	}
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		if ("0".equals(sex)) {
			return "女";
		}
		if ("1".equals(sex)) {
			return "男";
		}
		return "";
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcardNum() {
		return idcardNum;
	}

	public void setIdcardNum(String idcardNum) {
		this.idcardNum = idcardNum;
	}

	public String getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(String passportNum) {
		this.passportNum = passportNum;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String[] getVideoFilePaths() {
		return videoFilePaths;
	}

	public void setVideoFilePaths(String[] videoFilePaths) {
        if(videoFilePaths.length > 1 || !"".equals(videoFilePaths[0])) {
            this.videoFilePaths = videoFilePaths;
        }
	}

	public String[] getVideoThumbFilePaths() {
		return videoThumbFilePaths;
	}

	public void setVideoThumbFilePaths(String[] videoThumbFilePaths) {
		this.videoThumbFilePaths = videoThumbFilePaths;
	}

	public String[] getPhotoFilePaths() {
		return photoFilePaths;
	}

	public void setPhotoFilePaths(String[] photoFilePaths) {
		this.photoFilePaths = photoFilePaths;
	}

	public String[] getPhotoThumbFilePaths() {
		return photoThumbFilePaths;
	}

	public void setPhotoThumbFilePaths(String[] photoThumbFilePaths) {
		this.photoThumbFilePaths = photoThumbFilePaths;
	}

	public String getIdcardPrintFilePath() {
		return idcardPrintFilePath;
	}

	public void setIdcardPrintFilePath(String idcardPrintFilePath) {
		this.idcardPrintFilePath = idcardPrintFilePath;
	}

	public String getIdcardPrintThumbFilePath() {
		return idcardPrintThumbFilePath;
	}

	public void setIdcardPrintThumbFilePath(String idcardPrintThumbFilePath) {
		this.idcardPrintThumbFilePath = idcardPrintThumbFilePath;
	}

	public String getPassportPrintFilePath() {
		return passportPrintFilePath;
	}

	public void setPassportPrintFilePath(String passportPrintFilePath) {
		this.passportPrintFilePath = passportPrintFilePath;
	}

	public String getPassportPrintThumbFilePath() {
		return passportPrintThumbFilePath;
	}

	public void setPassportPrintThumbFilePath(String passportPrintThumbFilePath) {
		this.passportPrintThumbFilePath = passportPrintThumbFilePath;
	}

	public String getIdcardPhotoFilePath() {
		return idcardPhotoFilePath;
	}

	public void setIdcardPhotoFilePath(String idcardPhotoFilePath) {
		this.idcardPhotoFilePath = idcardPhotoFilePath;
	}

	public String getIdcardPhotoThumbFilePath() {
		return idcardPhotoThumbFilePath;
	}

	public void setIdcardPhotoThumbFilePath(String idcardPhotoThumbFilePath) {
		this.idcardPhotoThumbFilePath = idcardPhotoThumbFilePath;
	}

	
	public boolean isProcessing() {
		return processing;
	}

	public void setProcessing(boolean processing) {
		this.processing = processing;
	}

	private String getStringFromVariables(Object obj) {
		if (obj == null) {
			return "";
		} else if (obj.getClass().equals(Long.class)) {
			return obj.toString();
		} else if (obj.getClass().equals(Integer.class)) {
			return obj.toString();
		} else if (obj.getClass().equals(Boolean.class)) {
			return obj.toString();
		} else {
			return (String) obj;
		}
	}

	private String getAgeFromVariables(Object obj) {
		if (obj == null) {
			return null;
		}
		Long birthDate = (Long) obj;
		Date currentTime = new Date();
		long age = (currentTime.getTime() - birthDate.longValue()) / (1000 * 60 * 60 * 24) / 365 + 1;
		return String.valueOf(age);
	}

}
