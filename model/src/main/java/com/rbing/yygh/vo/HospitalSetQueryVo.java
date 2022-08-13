package com.rbing.yygh.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rbing
 * @create 2022-08-13-17:57
 **/
@Data
public class HospitalSetQueryVo {
    @ApiModelProperty(value = "医院名称")
    private String hosname;
    @ApiModelProperty(value = "医院编号")
    private String hoscode;
}
