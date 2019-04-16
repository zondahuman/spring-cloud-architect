package org.spring.cloud.architect.service;

import org.spring.cloud.architect.model.ArchitectureVo;

/**
 * Created by lee on 2019/1/30.
 */

public interface InformationService {

    void find(String param) ;

    void consumeTbox(ArchitectureVo architectureVo);



}
