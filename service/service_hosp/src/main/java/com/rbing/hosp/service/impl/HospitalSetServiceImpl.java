package com.rbing.hosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rbing.hosp.mapper.HospitalSetMapper;
import com.rbing.hosp.service.HospitalSetService;
import com.rbing.yygh.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

/**
 * @author rbing
 * @create 2022-08-13-15:46
 **/
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper,HospitalSet> implements HospitalSetService {
}
