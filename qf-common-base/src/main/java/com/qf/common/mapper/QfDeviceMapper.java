package com.qf.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.common.dto.DeviceDto;
import com.qf.common.model.QfDevice;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * 设备表 Mapper 接口
 * </p>
 *
 * @author 千锋健哥
 */
public interface QfDeviceMapper extends BaseMapper<QfDevice> {

    /**
     * id
     * name
     * device_key
     * device_type_id
     * create_time
     * update_time
     * deleted
     * product_id
     * prarent_id
     * tenant_id
     * group_id
     * active
     * device_secret
     */
    @Select("SELECT d.*,p.name 'product_name',p.product_key 'product_key' ,type.name 'device_type_name',type.device_type 'device_type' from qf_device d inner join qf_product p inner join qf_device_type type on d.product_id = p.id and d.device_type_id = type.id where d.tenant_id =#{tenantId} and d.deleted=0")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "device_key", property = "deviceKey"),
            @Result(column = "device_type_id", property = "deviceTypeId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "deleted", property = "deleted"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "prarent_id", property = "parentId"),
            @Result(column = "tenant_id", property = "tenantId"),
            @Result(column = "group_id", property = "groupId"),
            @Result(column = "active", property = "active"),
            @Result(column = "device_secret", property = "deviceSecret"),
            @Result(column = "product_name", property = "productName"),
            @Result(column = "product_key", property = "productKey"),
            @Result(column = "device_type_name", property = "deviceTypeName"),
            @Result(column = "device_type", property = "deviceType")

    })
    public List<DeviceDto> listByTenantId(String tenantId);


    @Select("SELECT d.*,p.name 'product_name' ,p.product_key 'product_key',type.name 'device_type_name',type.device_type 'device_type' from qf_device d inner join qf_product p inner join qf_device_type type on d.product_id = p.id and d.device_type_id = type.id where d.product_id =#{productId} and d.deleted=0")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "device_key", property = "deviceKey"),
            @Result(column = "device_type_id", property = "deviceTypeId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "deleted", property = "deleted"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "prarent_id", property = "parentId"),
            @Result(column = "tenant_id", property = "tenantId"),
            @Result(column = "group_id", property = "groupId"),
            @Result(column = "active", property = "active"),
            @Result(column = "device_secret", property = "deviceSecret"),
            @Result(column = "product_name", property = "productName"),
            @Result(column = "product_key", property = "productKey"),
            @Result(column = "device_type_name", property = "deviceTypeName"),
            @Result(column = "device_type", property = "deviceType")

    })
    public List<DeviceDto> listByProductId(String productId);


    @Select("select d.id,d.name,d.device_key,d.device_type_id,d.create_time,d.update_time,d.deleted,d.product_id,d.parent_id,d.tenant_id,d.group_id,d.active,d.device_secret," +
            "p.id pid,p.name product_name,p.product_key,p.tenant_id prodcut_tenant_id,p.area_code,p.username,p.password,p.create_time product_create_time,p.update_time product_update_time,p.deleted product_deleted," +
            "a.name area_name,type.name 'device_name',type.device_type 'device_type' " +
            "from qf_device d inner join qf_product p  inner join qf_area a inner join qf_device_type type " +
            "on d.product_id = p.id and p.area_code=a.code and d.device_type_id = type.id "+
            "where d.id=#{deviceId} and d.deleted=0")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "device_key", property = "deviceKey"),
            @Result(column = "device_type_id", property = "deviceTypeId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "deleted", property = "deleted"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "prarent_id", property = "parentId"),
            @Result(column = "tenant_id", property = "tenantId"),
            @Result(column = "group_id", property = "groupId"),
            @Result(column = "active", property = "active"),
            @Result(column = "device_secret", property = "deviceSecret"),

            @Result(column = "pid", property = "productDto.id"),
            @Result(column = "product_name", property = "productDto.name"),
            @Result(column = "product_key", property = "productDto.productKey"),
            @Result(column = "prodcut_tenant_id", property = "productDto.tenantId"),
            @Result(column = "area_code", property = "productDto.areaCode"),
            @Result(column = "username", property = "productDto.username"),
            @Result(column = "password", property = "productDto.password"),
            @Result(column = "product_create_time", property = "productDto.createTime"),
            @Result(column = "product_update_time", property = "productDto.updateTime"),
            @Result(column = "product_deleted", property = "productDto.deleted"),
            @Result(column = "area_name", property = "productDto.areaName"),

            @Result(column = "product_name", property = "productName"),
            @Result(column = "device_type_name", property = "deviceTypeName"),
            @Result(column = "device_type", property = "deviceType")
    })
    public DeviceDto selectByDeviceId(String deviceId);

}
