package ldc.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Class Storage is the class for manage your file uploading.
 * @author anubissmile
 */
public class Storage {
	
	/**
	 * PRIVATE FILE
	 */
	/**
	 * FILE CREDENTIALS
	 */
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	private Long size;
	private String filePath;
	private String extensionType;
	private String groupType;
	
	/**
	 * UPLOADED FILE CREDENTIALS
	 */
	private String destPath;
	private String fileName;
	private String destAbsolutePath;
	
	/**
	 * CONSTRUCTOR
	 */
	public Storage(){
		super();
	}
	
	public Storage file(File file, String contentType, String fileName) {
		setMyFile(file);
		setMyFileContentType(contentType);
		setMyFileFileName(fileName);
		setSize(file.length());
		setFilePath(getMyFile().getAbsolutePath());
		type();
//		System.out.println(getSize() + " " + getMyFileContentType());
		return this;
	}
	
	public boolean storeAs(String newPath, String fileName){
		
		
		File destFile = new File(newPath, fileName + type());
		setDestAbsolutePath(destFile.getAbsolutePath());
		setFileName(fileName + type());
		setDestPath(newPath);
		
		try {
			FileUtils.copyFile(getMyFile(), destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public String type(){
		String type = getMyFileContentType(), str = null, group = null;
		
		/**
		 * DOCUMENT.
		 */
		if(type.equals("application/pdf")){
			str = ".pdf"; group = "document";
		}else if(type.equals("application/msword")){
			str = ".doc"; group = "document";
		}else if(type.equals("application/mspowerpoint") || type.equals("application/powerpoint") || 
				type.equals("application/x-mspowerpoint") || type.equals("application/vnd.ms-powerpoint")){
			str = ".ppt"; group = "document";
		}else if(type.equals("application/excel") || type.equals("application/vnd.ms-excel") ||
				type.equals("application/x-msexcel") || type.equals("application/x-excel")){
			str = ".xls"; group = "document";
		}
		
		/**
		 * IMAGES.
		 */
		if(type.equals("image/jpeg") || type.equals("image/pjpeg")){
			str = ".jpg"; group = "image";
		}else if(type.equals("image/png")){
			str = ".png"; group = "image";
		}else if(type.equals("image/gif")){
			str = ".gif"; group = "image";
		}else if(type.equals("image/bmp") || type.equals("image/x-windows-bmp")){
			str = ".bmp"; group = "image";
		}
		
		/**
		 * COMPRESS FILES
		 */
		if(type.equals("application/zip") || type.equals("application/x-compressed") ||
				type.equals("application/x-zip-compressed") || type.equals("application/x-zip")){
			str = ".zip"; group = "compress";
		}
		
		/**
		 * TEXT PLAIN
		 */
		if(type.equals("text/plain")){
			str = ".txt"; group = "text";
		}
		
		setGroupType(group);
		setExtensionType(str);
		return getExtensionType();
	}
	
	/**
	 * GETTER & SETTER.
	 */
	public File getMyFile() {
		return myFile;
	}


	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}


	public String getMyFileContentType() {
		return myFileContentType;
	}


	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}


	public String getMyFileFileName() {
		return myFileFileName;
	}


	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}


	public String getDestPath() {
		return destPath;
	}


	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the destAbsolutePath
	 */
	public String getDestAbsolutePath() {
		return destAbsolutePath;
	}

	/**
	 * @param destAbsolutePath the destAbsolutePath to set
	 */
	public void setDestAbsolutePath(String destAbsolutePath) {
		this.destAbsolutePath = destAbsolutePath;
	}

	/**
	 * @return the extensionType
	 */
	public String getExtensionType() {
		return extensionType;
	}

	/**
	 * @param extensionType the extensionType to set
	 */
	public void setExtensionType(String extensionType) {
		this.extensionType = extensionType;
	}

	/**
	 * @return the groupType
	 */
	public String getGroupType() {
		return groupType;
	}

	/**
	 * @param groupType the groupType to set
	 */
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	


}
