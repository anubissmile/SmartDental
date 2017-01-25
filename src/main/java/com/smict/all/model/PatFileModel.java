package com.smict.all.model;

import com.smict.person.model.BranchModel;

public class PatFileModel extends BranchModel {
	String patHn, fileId;

	public String getPatHn() {
		return patHn;
	}

	public void setPatHn(String patHn) {
		this.patHn = patHn;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
}
