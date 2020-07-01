package ${basePackageName}.${projectName}.${moduleName}.convert;

import ${basePackageName}.${projectName}.${moduleName}.domain.${className};
import ${basePackageName}.${projectName}.${moduleName}.dto.${classname}.request.${className}AddReq;
import ${basePackageName}.${projectName}.${moduleName}.dto.${classname}.request.${className}EditReq;
import ${basePackageName}.${projectName}.${moduleName}.dto.${classname}.request.${className}ListReq;
import ${basePackageName}.${projectName}.${moduleName}.dto.${classname}.response.${className}EditRes;
import ${basePackageName}.${projectName}.${moduleName}.dto.${classname}.response.${className}ListRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * ${className}对象转换
 */
@Mapper(componentModel="spring")
public interface ${className}Convert {

    /**
     * 列表查询参数
     */
    @Mappings({})
    ${className} ${classname}ListRes2${className}(${className}ListReq ${classname}ListReq);

    /**
     * 列表查询结果
     */
    @Mappings({})
    ${className}ListRes ${classname}2${className}ListRes(${className} ${classname});

    /**
     * 新增保存
     */
    @Mappings({})
    ${className} ${classname}AddReq2${className}(${className}AddReq ${classname}AddReq);

    /**
     * 编辑查询
     */
    @Mappings({})
    ${className}EditRes ${classname}2${className}EditRes(${className} ${classname});

    /**
     * 编辑保存
     */
    @Mappings({})
    ${className} ${classname}EditReq2${className}(${className}EditReq ${classname}EditReq);
    
}
