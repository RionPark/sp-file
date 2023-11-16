package com.sp.file.mapper;

import java.util.List;

import com.sp.file.vo.RoleInfoVO;

public interface RoleInfoMapper {
	List<RoleInfoVO> selectRoleInfosByLiNum(int liNum);
}
