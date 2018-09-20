package com._3u.service.impl;

import com._3u.dao.TUseropinionMapper;
import com._3u.mapper.TUseropinion;
import com._3u.service.TUseropinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TUseropinionServiceImpl implements TUseropinionService {

    @Autowired
    TUseropinionMapper tUseropinionMapper;
    @Override
    public List<TUseropinion> findAllOpinion() {
        return tUseropinionMapper.findAllOpinion();
    }
}
