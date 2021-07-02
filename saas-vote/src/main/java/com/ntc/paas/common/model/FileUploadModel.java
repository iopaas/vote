package com.ntc.paas.common.model;

import java.io.Serializable;

/**
 * 文件上传返回类
 * 
 * @author allen.yuan
 * @date 2021年6月25日 下午3:28:11
 */
public class FileUploadModel implements Serializable {

	private static final long serialVersionUID = -6243828533687372125L;

	private String filename = ""; // 文件名称
	private String filepath = ""; // 存储路径
	private String fileurl = ""; // 文件地址url

	public FileUploadModel() {

	}

	public FileUploadModel(String filename, String fileurl) {
		this.filename = filename;
		this.fileurl = fileurl;
	}

	public FileUploadModel(String filename, String filepath, String fileurl) {
		this.filename = filename;
		this.filepath = filepath;
		this.fileurl = fileurl;
	}

	public String getFilename() {
		return (filename == null) ? "" : filename.trim();
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return (filepath == null) ? "" : filepath.trim();
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFileurl() {
		return (fileurl == null) ? "" : fileurl.trim();
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

}
