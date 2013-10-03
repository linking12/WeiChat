/**
 * 
 */
package net.chat.service.impl;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @author Administrator
 *
 */
@Service
public class CopyFileInitService implements InitializingBean{
	@Value("${upload.file.path}")
	private String uploadpath;
	@Override
	public void afterPropertiesSet() throws Exception {
		
		FileUtils.copyDirectory(new File(uploadpath), new File(this.getClass().getResource("/").getFile() + "upload"));
		
	}
	
	
}
