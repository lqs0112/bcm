package com.rtmap.traffic.bcm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rtmap.traffic.bcm.cache.CacheKeys;
import com.rtmap.traffic.bcm.cache.DictCache;
import com.rtmap.traffic.bcm.dao.IBuildingDao;
import com.rtmap.traffic.bcm.dao.ISysUserRolePrivDao;
import com.rtmap.traffic.bcm.dao.IVehicleDao;
import com.rtmap.traffic.bcm.domain.Building;
import com.rtmap.traffic.bcm.domain.RoleCdConstants;
import com.rtmap.traffic.bcm.domain.SysUser;
import com.rtmap.traffic.bcm.domain.Vehicle;
import com.rtmap.traffic.bcm.service.IDictService;

import lqs.frame.ui.model.ComboStrItem;
import lqs.frame.ui.model.DataSourceUtils;

@Service
public class DictServiceImpl implements IDictService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	IBuildingDao buildingDao;
	@Resource
	IVehicleDao vehicleDao;
	@Resource
	ISysUserRolePrivDao urpDao;

	@Override
	public List<ComboStrItem> getBuildingsWithAll() {
		List<ComboStrItem> source = DictCache.get(CacheKeys.BUILDING_WITH_ALL);

		if (source == null) {
			source = new ArrayList<>();

			DataSourceUtils.AddComboWithAll(source);
			source.addAll(getBuildings());
			DictCache.put(CacheKeys.BUILDING_WITH_ALL, source);
		}

		return source;
	}

	public List<ComboStrItem> getBuildings() {
		List<ComboStrItem> source = DictCache.get(CacheKeys.BUILDING);

		if (source == null) {
			source = new ArrayList<>();
			List<Building> list = buildingDao.selectAll();

			if (list == null || list.size() == 0) {
				logger.warn("建筑物参数表为空！");
			} else {
				for (Building building : list) {
					ComboStrItem item = new ComboStrItem();
					item.setText(building.getBuildingName());
					item.setValue(building.getBuildingNo());
					source.add(item);
				}
			}
		}

		DictCache.put(CacheKeys.BUILDING, source);

		return source;
	}

	@Override
	public List<ComboStrItem> getDriversWithAll() {
		List<ComboStrItem> source = DictCache.get(CacheKeys.DRIVER_WITH_ALL);

		if (source == null) {
			source = new ArrayList<>();
			
			DataSourceUtils.AddComboWithAll(source);
			source.addAll(getDrivers());
			DictCache.put(CacheKeys.DRIVER_WITH_ALL, source);
		}

		return source;
	}

	@Override
	public List<ComboStrItem> getDrivers() {
		List<ComboStrItem> source = DictCache.get(CacheKeys.DRIVER);

		if (source == null) {
			source = new ArrayList<>();
			List<SysUser> list = urpDao.selectSysUsersByRoleCd(RoleCdConstants.DRIVER);

			if (list == null || list.size() == 0) {
				logger.warn(String.format("【%s】角色的用户列表为空！", RoleCdConstants.DRIVER));
			} else {
				for (SysUser user : list) {
					ComboStrItem item = new ComboStrItem();
					item.setText(user.getUserCd());
					item.setValue(user.getUserCd());
					source.add(item);
				}
			}
		}

		DictCache.put(CacheKeys.DRIVER, source);

		return source;
	}

	@Override
	public List<ComboStrItem> getVehiclesWithAll() {
		List<ComboStrItem> source = DictCache.get(CacheKeys.VEHICLE_WITH_ALL);

		if (source == null) {
			source = new ArrayList<>();
			
			DataSourceUtils.AddComboWithAll(source);
			source.addAll(getVehicles());
			DictCache.put(CacheKeys.VEHICLE_WITH_ALL, source);
		}

		return source;
	}

	@Override
	public List<ComboStrItem> getVehicles() {
		List<ComboStrItem> source = DictCache.get(CacheKeys.VEHICLE);

		if (source == null) {
			source = new ArrayList<>();
			List<Vehicle> list = vehicleDao.selectAllEnabled();

			if (list == null || list.size() == 0) {
				logger.warn("建筑物参数表为空！");
			} else {
				for (Vehicle vehicle : list) {
					ComboStrItem item = new ComboStrItem();
					item.setText(vehicle.getVehicleName());
					item.setValue(vehicle.getVehicleNo());
					source.add(item);
				}
			}

			DictCache.put(CacheKeys.VEHICLE, source);
		}

		return source;
	}
}
