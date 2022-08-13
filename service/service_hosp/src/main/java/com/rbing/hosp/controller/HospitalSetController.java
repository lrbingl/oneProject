package com.rbing.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rbing.common.result.Result;
import com.rbing.common.utils.MD5;
import com.rbing.hosp.service.HospitalSetService;
import com.rbing.yygh.model.hosp.HospitalSet;
import com.rbing.yygh.vo.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author rbing
 * @create 2022-08-13-15:50
 **/
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;


    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("/findAll")
    public Result findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }


    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospital(
            @ApiParam(name = "id", value = "医院设置id", required = true)
            @PathVariable Long id) {
        boolean b = hospitalSetService.removeById(id);
        return b ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "条件查询带分页查询医院设置")
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {

        //创建page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current, limit);

        QueryWrapper<HospitalSet> hospitalSetQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(hospitalSetQueryVo.getHosname())) {
            hospitalSetQueryWrapper.like("hosname", hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hospitalSetQueryVo.getHoscode())) {
            hospitalSetQueryWrapper.eq("hoscode", hospitalSetQueryVo.getHoscode());
        }
        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, hospitalSetQueryWrapper);
        return Result.ok(hospitalSetPage);

    }

    @ApiOperation(value = "添加医院设置")
    @PostMapping("/saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {

        hospitalSet.setStatus(1);
        //签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
        boolean save = hospitalSetService.save(hospitalSet);
        return save ? Result.ok() : Result.fail();
    }


    @ApiOperation(value = "根据id获取医院设置")
    @GetMapping("/getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id) {
        HospitalSet byId = hospitalSetService.getById(id);
        return Result.ok(byId);
    }

    @ApiOperation(value = "修改医院设置")
    @PutMapping("/updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {

        boolean save = hospitalSetService.updateById(hospitalSet);
        return save ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "批量逻辑删除医院设置")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean b = hospitalSetService.removeByIds(idList);
        return b ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "医院设置锁定和解锁")
    @PutMapping("/lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,@PathVariable Integer status) {

        HospitalSet byId = hospitalSetService.getById(id);
        byId.setStatus(status);
        boolean b = hospitalSetService.updateById(byId);
        return b ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "发送签名密钥")
    @PutMapping("/sendKey/{id}")
    public Result sendKey(@PathVariable Long id) {

        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信
        return Result.ok();
    }
}
