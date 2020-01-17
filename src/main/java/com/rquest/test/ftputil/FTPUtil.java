package com.rquest.test.ftputil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * FTP服务工具类
 * 
 * @author： admin @time：
 */
@Service
public class FTPUtil {

	/** 日志对象 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(FTPUtil.class);

	public static final String SPLITASLASH = "/";
	/** FTP地址 **/
//	@Value("ftp.server.address")
	private String FTP_ADDRESS = ResourceBundle.getBundle("application").getString("ftp.server.address");

	/** FTP端口 **/
//	@Value("ftp.server.port")
	private String FTP_PORT = ResourceBundle.getBundle("application").getString("ftp.server.port");

	/** FTP用户名 **/
//	@Value("ftp.server.login.name")
	private String FTP_USERNAME = ResourceBundle.getBundle("application").getString("ftp.server.login.name");

	/** FTP密码 **/
//	@Value("ftp.server.login.password")
	private String FTP_PASSWORD = ResourceBundle.getBundle("application").getString("ftp.server.login.password");

	/** FTP基础目录 **/
	private String BASE_PATH = "/";

	/** 本地字符编码 **/
	private static String localCharset = "GBK";

	/** FTP协议里面，规定文件名编码为iso-8859-1 **/
	private static String serverCharset = "ISO-8859-1";

	/** UTF-8字符编码 **/
	private static final String CHARSET_UTF8 = "UTF-8";
	
	/** OPTS UTF8字符串常量 **/
	private static final String OPTS_UTF8 = "OPTS UTF8";

	/** 设置缓冲区大小4M **/
	private static final int BUFFER_SIZE = 1024 * 1024 * 4;

	/** FTPClient对象 **/
	private FTPClient ftpClient = null;

	/****/
	private boolean isPassive = false;
	/**
	 * 本地文件上传到FTP服务器
	 * 
	 * @param ftpPath
	 *            FTP服务器文件相对路径，例如：test/123
	 * @param savePath
	 *            本地文件路径，例如：D:/test/123/test.txt
	 * @param fileName
	 *            上传到FTP服务的文件名，例如：666.txt
	 * @return boolean 成功返回true，否则返回false
	 */
	public boolean uploadLocalFile(String ftpPath, String savePath, String fileName) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		boolean flag = false;
		if (ftpClient != null) {
			File file = new File(savePath);
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				ftpClient.setBufferSize(BUFFER_SIZE);
				// 设置编码：开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
				if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
					localCharset = CHARSET_UTF8;
				}
				ftpClient.setControlEncoding(localCharset);
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 目录不存在，则递归创建

				if (!ftpClient.changeWorkingDirectory(path)) {
					this.createDirectorys(path);
				}
				setPassiveValue();
				// 上传文件
				flag = ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset), fis);
			} catch (Exception e) {
				LOGGER.error("本地文件上传FTP失败", e);
			} finally {
				IOUtils.closeQuietly(fis);
				closeConnect();
			}
		}
		return flag;
	}

	/**
	 * 远程文件上传到FTP服务器
	 * 
	 * @param ftpPath
	 *            FTP服务器文件相对路径，例如：test/123
	 * @param remotePath
	 *            远程文件路径，例如：http://www.baidu.com/xxx/xxx.jpg
	 * @param fileName
	 *            上传到FTP服务的文件名，例如：test.jpg
	 * @return boolean 成功返回true，否则返回false
	 */
	public boolean uploadRemoteFile(String ftpPath, String remotePath, String fileName) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		boolean flag = false;
		if (ftpClient != null) {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			try {
				// 远程获取文件输入流
				HttpGet httpget = new HttpGet(remotePath);
				response = httpClient.execute(httpget);
				HttpEntity entity = response.getEntity();
				InputStream input = entity.getContent();
				ftpClient.setBufferSize(BUFFER_SIZE);
				// 设置编码：开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
				if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
					localCharset = CHARSET_UTF8;
				}
				ftpClient.setControlEncoding(localCharset);
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 目录不存在，则递归创建
				if (!ftpClient.changeWorkingDirectory(path)) {
					this.createDirectorys(path);
				}
				setPassiveValue();
				// 上传文件
				flag = ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset), input);
			} catch (Exception e) {
				LOGGER.error("远程文件上传FTP失败", e);
			} finally {
				closeConnect();
				try {
					httpClient.close();
				} catch (IOException e) {
					LOGGER.error("关闭流失败", e);
				}
				if (response != null) {
					try {
						response.close();
					} catch (IOException e) {
						LOGGER.error("关闭流失败", e);
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 下载指定文件到本地
	 * 
	 * @param ftpPath
	 *            FTP服务器文件相对路径，例如：test/123
	 * @param fileName
	 *            要下载的文件名，例如：test.txt
	 * @param savePath
	 *            保存文件到本地的路径，例如：D:/test
	 * @return 成功返回true，否则返回false
	 */
	public boolean downloadFile(String ftpPath, String fileName, String savePath) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		boolean flag = false;
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return flag;
				}
				//设置主动或者被动模式，默认主动模式
				setPassiveValue();
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return flag;
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					if (ftpName.equals(fileName)) {
						File file = new File(savePath + '/' + ftpName);
						try (OutputStream os = new FileOutputStream(file)) {
							flag = ftpClient.retrieveFile(ff, os);
						} catch (Exception e) {
							LOGGER.error(e.getMessage(), e);
						}
						break;
					}
				}
			} catch (IOException e) {
				LOGGER.error("下载文件失败", e);
			} finally {
				closeConnect();
			}
		}
		return flag;
	}
	/**
	 * 设置被动模式，默认为主动模式
	 */
	private void setPassiveValue() {
		if (isPassive) {
			ftpClient.enterLocalPassiveMode(); // 设置被动模式，开通一个端口来传输数据
		}
	}

	/**
	 * 下载该目录下所有文件到本地
	 * 
	 * @param ftpPath
	 *            FTP服务器上的相对路径，例如：test/123
	 * @param savePath
	 *            保存文件到本地的路径，例如：D:/test
	 * @return 成功返回true，否则返回false
	 */
	public boolean downloadFiles(String ftpPath, String savePath) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		boolean flag = false;
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return flag;
				}
				setPassiveValue();
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return flag;
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					File file = new File(savePath + '/' + ftpName);
					try (OutputStream os = new FileOutputStream(file)) {
						ftpClient.retrieveFile(ff, os);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
				flag = true;
			} catch (IOException e) {
				LOGGER.error("下载文件失败", e);
			} finally {
				closeConnect();
			}
		}
		return flag;
	}
	/**
	 * 开头不需要加/
	 * @param ftpPath
	 * @return
	 */
	public boolean changeDirectory(String ftpPath) {
		String path = changeEncoding(BASE_PATH + ftpPath);
		// 判断是否存在该目录
		try {
			if (!ftpClient.changeWorkingDirectory(path)) {
				LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 获取该目录下所有文件,以字节数组返回
	 * 
	 * @param ftpPath
	 *            FTP服务器上文件所在相对路径，例如：test/123
	 * @return Map<String, Object> 其中key为文件名，value为字节数组对象
	 */
	public Map<String, byte[]> getFileBytes(String ftpPath) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		Map<String, byte[]> map = new HashMap<>();
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return map;
				}
				setPassiveValue();
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return map;
				}
				for (String ff : fs) {
					try (InputStream is = ftpClient.retrieveFileStream(ff)) {
						String ftpName = new String(ff.getBytes(serverCharset), localCharset);
						ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[BUFFER_SIZE];
						int readLength = 0;
						while ((readLength = is.read(buffer, 0, BUFFER_SIZE)) > 0) {
							byteStream.write(buffer, 0, readLength);
						}
						map.put(ftpName, byteStream.toByteArray());
						ftpClient.completePendingCommand(); // 处理多个文件
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
			} catch (IOException e) {
				LOGGER.error("获取文件失败", e);
			} finally {
				closeConnect();
			}
		}
		return map;
	}

	/**
	 * 根据名称获取文件，以字节数组返回
	 * 
	 * @param ftpPath
	 *            FTP服务器文件相对路径，例如：test/123
	 * @param fileName
	 *            文件名，例如：test.xls
	 * @return byte[] 字节数组对象
	 */
	public byte[] getFileBytesByName(String ftpPath, String fileName) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return byteStream.toByteArray();
				}
				setPassiveValue();
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return byteStream.toByteArray();
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					if (ftpName.equals(fileName)) {
						try (InputStream is = ftpClient.retrieveFileStream(ff);) {
							byte[] buffer = new byte[BUFFER_SIZE];
							int len = -1;
							while ((len = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
								byteStream.write(buffer, 0, len);
							}
						} catch (Exception e) {
							LOGGER.error(e.getMessage(), e);
						}
						break;
					}
				}
			} catch (IOException e) {
				LOGGER.error("获取文件失败", e);
			} finally {
				closeConnect();
			}
		}
		return byteStream.toByteArray();
	}

	/**
	 * 获取该目录下所有文件,以输入流返回
	 * 
	 * @param ftpPath
	 *            FTP服务器上文件相对路径，例如：test/123
	 * @return Map<String, InputStream> 其中key为文件名，value为输入流对象
	 */
	public Map<String, InputStream> getFileInputStream(String ftpPath) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		Map<String, InputStream> map = new HashMap<>();
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return map;
				}
				setPassiveValue();
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return map;
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					InputStream is = ftpClient.retrieveFileStream(ff);
					map.put(ftpName, is);
					ftpClient.completePendingCommand(); // 处理多个文件
				}
			} catch (IOException e) {
				LOGGER.error("获取文件失败", e);
			} finally {
				closeConnect();
			}
		}
		return map;
	}

	/**
	 * 根据名称获取文件，以输入流返回
	 * 
	 * @param ftpPath
	 *            FTP服务器上文件相对路径，例如：test/123
	 * @param fileName
	 *            文件名，例如：test.txt
	 * @return InputStream 输入流对象
	 */
	public InputStream getInputStreamByName(String ftpPath, String fileName) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		InputStream input = null;
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return input;
				}
				setPassiveValue();
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return input;
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					if (ftpName.equals(fileName)) {
						input = ftpClient.retrieveFileStream(ff);
						break;
					}
				}
			} catch (IOException e) {
				LOGGER.error("获取文件失败", e);
			} finally {
				closeConnect();
			}
		}
		return input;
	}

	/**
	 * 删除指定文件
	 * 
	 * @param filePath
	 *            文件相对路径，例如：test/123/test.txt
	 * @return 成功返回true，否则返回false
	 */
	public boolean deleteFile(String filePath) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		boolean flag = false;
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + filePath);
				flag = ftpClient.deleteFile(path);
			} catch (IOException e) {
				LOGGER.error("删除文件失败", e);
			} finally {
				closeConnect();
			}
		}
		return flag;
	}

	/**
	 * 删除目录下所有文件
	 * 
	 * @param dirPath
	 *            文件相对路径，例如：test/123
	 * @return 成功返回true，否则返回false
	 */
	public boolean deleteFiles(String dirPath) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		boolean flag = false;
		if (ftpClient != null) {
			try {
				setPassiveValue();
				String path = changeEncoding(BASE_PATH + dirPath);
				String[] fs = ftpClient.listNames(path);
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + dirPath + "该目录下没有文件");
					return flag;
				}
				for (String ftpFile : fs) {
					ftpClient.deleteFile(ftpFile);
				}
				flag = true;
			} catch (IOException e) {
				LOGGER.error("删除文件失败", e);
			} finally {
				closeConnect();
			}
		}
		return flag;
	}

	/**
	 * 连接FTP服务器
	 * 
	 * @param address
	 *            地址，如：127.0.0.1
	 * @param port
	 *            端口，如：21
	 * @param username
	 *            用户名，如：root
	 * @param password
	 *            密码，如：root
	 */
	private void login(String address, String port, String username, String password) {
		if (ftpClient == null) {
			ftpClient = new FTPClient();
		}
		if (ftpClient.isConnected()) {
			LOGGER.error("FTP服务器已经连接");
			return;
		}
		try {
			ftpClient.connect(address, Integer.parseInt(port));
			ftpClient.login(username, password);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				closeConnect();
				LOGGER.error("FTP服务器连接失败");
			}
		} catch (Exception e) {
			LOGGER.error("FTP登录失败", e);
		}
	}

	/**
	 * 关闭FTP连接
	 * 
	 */
	public void closeConnect() {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				LOGGER.error("关闭FTP连接失败", e);
			}
		}
	}

	/**
	 * FTP服务器路径编码转换
	 * 
	 * @param ftpPath
	 *            FTP服务器路径
	 * @return String
	 */
	private String changeEncoding(String ftpPath) {
		String directory = null;
		try {
			if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
				localCharset = CHARSET_UTF8;
			}
			directory = new String(ftpPath.getBytes(localCharset), serverCharset);
		} catch (Exception e) {
			LOGGER.error("路径编码转换失败", e);
		}
		return directory;
	}

	/**
	 *      * 在服务器上递归创建目录      *      * @param dirPath 上传目录路径      * @return     
	 */
	private void createDirectorys(String dirPath) {
		try {
			new File(dirPath).mkdirs();
			LOGGER.info("create dir success!" + dirPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String transferEncode(String source,String code) {
		String results = null;
		try {
			results = new String(source.getBytes(),code);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	public String getLocalCharset() {
		return localCharset;
	}

	public void setLocalCharset(String localCharset) {
		FTPUtil.localCharset = localCharset;
	}

	public String getServerCharset() {
		return serverCharset;
	}

	public void setServerCharset(String serverCharset) {
		FTPUtil.serverCharset = serverCharset;
	}

	public boolean isPassive() {
		return isPassive;
	}

	public void setPassive(boolean isPassive) {
		this.isPassive = isPassive;
	}

	public String getFtpAddress() {
		return FTP_ADDRESS;
	}

	public String getFtpPort() {
		return FTP_PORT;
	}

	public String getFtpUsername() {
		return FTP_USERNAME;
	}

	public String getFtpPassword() {
		return FTP_PASSWORD;
	}

	public String getBasePath() {
		return BASE_PATH;
	}

	public FTPClient getFtpClient() {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		return ftpClient;
	}

}