package com.example.algafoodapi.core.validation;
/*
 *  @criado em: 08/07/2020 - {06:45}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private DataSize maxSize;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        this.maxSize = DataSize.parse(constraintAnnotation.max());

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return multipartFile==null  || multipartFile.getSize()<=this.maxSize.toBytes();
    }
}
