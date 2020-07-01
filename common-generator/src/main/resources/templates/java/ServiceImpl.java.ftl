package ${basePackageName}.${projectName}.${moduleName}.service.impl;

import ${basePackageName}.${projectName}.${moduleName}.domain.${className};
import ${basePackageName}.${projectName}.${moduleName}.mapper.${className}Mapper;
import ${basePackageName}.${projectName}.${moduleName}.service.I${className}Service;
import ${basePackageName}.core.framework.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * ${tableComment} 服务层实现
 * @author ${author}
 * @date ${datetime}
 */
@Service("${classname}Service")
public class ${className}Service extends BaseService<Long, ${className}, ${className}Mapper> implements I${className}Service {

    private ${className}Mapper ${classname}Mapper;

    public ${className}Service(${className}Mapper ${classname}Mapper) {
        this.${classname}Mapper = ${classname}Mapper;
    }

    @Override
    protected ${className}Mapper getMapper() {
        return this.${classname}Mapper;
    }

}
