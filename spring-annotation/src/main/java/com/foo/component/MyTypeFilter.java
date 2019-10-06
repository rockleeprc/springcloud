package com.foo.component;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {
    /**
     *
     * @param metadataReader：类源数据信息
     * @param metadataReaderFactory：类元数据工厂
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类的资源信息，如路径
        Resource resource = metadataReader.getResource();
        // 获取当前注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        if(classMetadata.getClassName().contains("Service")|| classMetadata.getClassName().contains("Dao")){
            return true;
        }
        return false;
    }
}
