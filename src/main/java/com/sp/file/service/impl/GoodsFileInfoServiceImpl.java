package com.sp.file.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.file.mapper.GoodsFileInfoMapper;
import com.sp.file.service.GoodsFileInfoService;
import com.sp.file.type.Status;
import com.sp.file.vo.GoodsFileInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Primary
@Service
@RequiredArgsConstructor
@Slf4j
public class GoodsFileInfoServiceImpl implements GoodsFileInfoService {
	
	private final GoodsFileInfoMapper goodsFileMapper;
	
	@Value("${upload.file-path}")
	private String uploadFilePath; 
	
	
	@Override
	public int insertGoodsFileInfo(GoodsFileInfoVO goodsFile) {
		return goodsFileMapper.insertGoodsFileInfo(goodsFile);
	}

	@Override
	public List<GoodsFileInfoVO> selectGoodsFileInfos(int giNum) {
		return goodsFileMapper.selectGoodsFileInfos(giNum);
	}

	@Override
	public int insertGoodsFileInfos(int giNum, List<GoodsFileInfoVO> goodsFiles) {
		int result = 0;
		for(GoodsFileInfoVO goodsFile : goodsFiles) {
			MultipartFile file = goodsFile.getFile();
			String originName = file.getOriginalFilename(); // abcd.png
			String extName = originName.substring(originName.lastIndexOf(".")); //.png
			String fileName = UUID.randomUUID() + extName;
			goodsFile.setGfiOriginName(originName);
			goodsFile.setGfiPath("/file/" + fileName);
			try {
				file.transferTo(new File(uploadFilePath + fileName));
			} catch (IllegalStateException e) {
				log.error("file upload error=>{}", e);
			} catch (IOException e) {
				log.error("file upload error=>{}", e);
			}
			goodsFile.setGiNum(giNum);
			result += goodsFileMapper.insertGoodsFileInfo(goodsFile);
		}
		return result;
	}

	@Override
	public int updateGoodsFileInfos(int giNum, List<GoodsFileInfoVO> goodsFiles) {

		int result = 0;
		for(GoodsFileInfoVO goodsFile : goodsFiles) {
			if(goodsFile.getStatus() == Status.DELETE) {
				String fileName = goodsFile.getGfiPath();
				int idx = fileName.lastIndexOf("/")+1;
				fileName = fileName.substring(idx);
				File f = new File(uploadFilePath + fileName);
				if(f.exists()) {
					f.delete();
				}
				result += goodsFileMapper.deleteGoodsFileInfo(goodsFile.getGfiNum());
				continue;
			}
			MultipartFile file = goodsFile.getFile();
			if(file!=null) {
				String originName = file.getOriginalFilename(); // abcd.png
				String extName = originName.substring(originName.lastIndexOf(".")); //.png
				String fileName = UUID.randomUUID() + extName;
				goodsFile.setGfiOriginName(originName);
				goodsFile.setGfiPath("/file/" + fileName);
				try {
					file.transferTo(new File(uploadFilePath + fileName));
				} catch (IllegalStateException e) {
					log.error("file upload error=>{}", e);
				} catch (IOException e) {
					log.error("file upload error=>{}", e);
				}
			}
			goodsFile.setGiNum(giNum);

			if(goodsFile.getStatus() == Status.UPDATE) {
				result += goodsFileMapper.updateGoodsFileInfo(goodsFile);
			}else {
				result += goodsFileMapper.insertGoodsFileInfo(goodsFile);
			}
		}
		return result;
	}

}
