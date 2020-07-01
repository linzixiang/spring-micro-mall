package ${basePackageName}.${projectName}.${moduleName}.service;

/**
 * ${tableComment} 服务层接口
 * @author ${author}
 * @date  ${datetime}
 */
public interface I${className}Service {

	Optional<${className}> getById(Long id);

    Optional<List<${className}>> selectByIds(Long[] ids);

    Optional<List<${className}>> selectList(${className} ${classname});

    Integer insert(${className} ${classname});

    Integer insertBatch(List<${className}> ${classname}List);

    Integer update(${className} ${classname});

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);
	
}
