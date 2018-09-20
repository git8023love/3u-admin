package com._3u.dao;

import com._3u.mapper.TUseropinion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TUseropinionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUseropinion record);

    int insertSelective(TUseropinion record);

    TUseropinion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUseropinion record);

    int updateByPrimaryKey(TUseropinion record);

    List<TUseropinion> findAllOpinion();
}