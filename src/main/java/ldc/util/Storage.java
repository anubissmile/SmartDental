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
	 * STATUS
	 */
	private boolean isSuccess = false;
	private String msgStatus;
	
	/**
	 * CONSTRUCTOR
	 */
	public Storage(){
		super();
	}
	
	/**
	 * Set file element
	 * @author anubissmile
	 * @param File | file
	 * @param String | contentType
	 * @param String | fileName
	 * @return Storage
	 */
	public Storage file(File file, String contentType, String fileName) {
		if(file.exists()){
			if(file.isFile()){
				setMyFile(file);
				setMyFileContentType(contentType);
				setMyFileFileName(fileName);
				setSize(file.length());
				setFilePath(getMyFile().getAbsolutePath());
				type();
//				System.out.println(getSize() + " " + getMyFileContentType());
				setSuccess(true);
				setMsgStatus("Moving file success.");
			}else{
				setMsgStatus("Error! file was unnormal file.");
			}
		}else{
			setMsgStatus("Error! file doesn't exists.");
		}
		return this;
	}
	
	/**
	 * Copy file into destination path.
	 * @author anubissmile
	 * @param String | newPath
	 * @param String | fileName
	 * @return Storage
	 */
	public Storage storeAs(String newPath, String fileName){
		File destFile = new File(Servlet.realPath("/") + newPath, fileName + type());
		setDestAbsolutePath(destFile.getAbsolutePath());
		setFileName(fileName + type());
		setDestPath(newPath + fileName + type());
		
		try {
			FileUtils.copyFile(getMyFile(), destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Deleting file in server path.
	 * @author anubissmile
	 * @param String | path
	 * @return Storage
	 */
	public Storage delete(String path){
		if(path != null){
			File file = new File(Servlet.realPath("/") + path);
			if(file.exists()){
				if(file.delete()){
					setSuccess(true);
					setMsgStatus("Delete success");
				}else{
					setMsgStatus("Delete fail!");
				}
			}else{
				setSuccess(true);
				setMsgStatus("Delete success");
			}
		}
		return this;
	}
	
	/**
	 * Get extension type.
	 * @author anubissmile
	 * @return String | extension type of file such as (.pdf)
	 */
	public String type(){
		/*xlsx : application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
		xls : application/vnd.ms-excel
		ppt : application/vnd.ms-powerpoint
		pptx : application/vnd.openxmlformats-officedocument.presentationml.presentation
		doc : application/msword
		docx : application/vnd.openxmlformats-officedocument.wordprocessingml.document
		gif : image/gif
		jpg : image/jpeg
		png : image/png
		rar : application/octet-stream*/
		
		String type = getMyFileContentType(), str = null, group = null;
		
		/**
		 * DOCUMENT.
		 */
		if(type.equals("application/pdf")){
			str = ".pdf"; group = "document";
		}else if(type.equals("application/msword")){
			str = ".doc"; group = "document";
		}else if(type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
			str = ".docx"; group = "document";
		}else if(type.equals("application/mspowerpoint") || type.equals("application/powerpoint") || 
				type.equals("application/x-mspowerpoint") || type.equals("application/vnd.ms-powerpoint")){
			str = ".ppt"; group = "document";
		}else if(type.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")){
			str = ".pptx"; group = "document";
		}else if(type.equals("application/excel") || type.equals("application/vnd.ms-excel") ||
				type.equals("application/x-msexcel") || type.equals("application/x-excel")){
			str = ".xls"; group = "document";
		}else if(type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
			str = ".xlsx"; group = "document";
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
		}else if(type.equals("application/octet-stream")){
			str = ".rar"; group = "compress";
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

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	


}
