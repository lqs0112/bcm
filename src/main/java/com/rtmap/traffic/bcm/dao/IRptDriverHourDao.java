package com.rtmap.traffic.bcm.dao;

import com.rtmap.traffic.bcm.domain.RptDriverHour;

public interface IRptDriverHourDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RptDriverHour record);

    int insertSelective(RptDriverHour record);

    RptDriverHour selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptDriverHour record);

    int updateByPrimaryKey(RptDriverHour record);
}