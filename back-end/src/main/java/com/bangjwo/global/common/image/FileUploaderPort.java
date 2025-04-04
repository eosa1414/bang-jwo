package com.bangjwo.global.common.image;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploaderPort {
	/**
	 * 파일을 업로드하고, 해당 파일의 접근 가능한 URL을 반환합니다.
	 *
	 * @param file 업로드할 MultipartFile
	 * @param fileName 저장할 파일명
	 * @return 업로드된 파일의 접근 URL
	 */
	String upload(MultipartFile file, String directory, String fileName);
}
