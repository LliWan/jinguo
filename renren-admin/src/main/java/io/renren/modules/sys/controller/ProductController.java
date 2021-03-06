package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.renren.modules.sys.entity.ProductEntity;
import io.renren.modules.sys.service.ProductService;
import io.renren.common.utils.FileUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 商品表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-08-22 14:44:10
 */
@RestController
@RequestMapping("sys/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:product:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:product:info")
    public R info(@PathVariable("id") Integer id){
        ProductEntity product = productService.getById(id);

        return R.ok().put("product", product);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:product:save")
    public R save(@RequestBody ProductEntity product){
    	product.setCreateDate(new Date());
        productService.save(product);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:product:update")
    public R update(@RequestBody ProductEntity product){
        ValidatorUtils.validateEntity(product);
        productService.updateById(product);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:product:delete")
    public R delete(@RequestBody Integer[] ids){
        productService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 图片上传
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile multipartFile) throws Exception{
    	String upload = FileUtils.upload(multipartFile);
            return R.ok().put("url", upload);
    }

}
