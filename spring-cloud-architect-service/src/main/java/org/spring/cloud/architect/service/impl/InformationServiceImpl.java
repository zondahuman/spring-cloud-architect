package org.spring.cloud.architect.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.architect.common.util.JsonUtil;
import org.spring.cloud.architect.common.util.LoggerUtil;
import org.spring.cloud.architect.model.ArchitectureVo;
import org.spring.cloud.architect.service.InformationService;
import org.springframework.stereotype.Service;

/**
 * Created by lee on 2019/1/30.
 */
@Slf4j
@Service
public class InformationServiceImpl implements InformationService {
    protected final static Logger logger = LoggerFactory.getLogger(InformationServiceImpl.class);


    @Override
    public void find(String param) {
        LoggerUtil.vehicleInfo("param=" + param);
        logger.info("find--param" + param);
    }


    @Override
    public void consumeTbox(ArchitectureVo architectureVo) {
        logger.info("consumeTbox--architectureVo=" + JsonUtil.toJson(architectureVo));
    }


}
