package com.example.algafoodapi.core.validation;
/*
 *  @criado em: 08/07/2020 - {06:33}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MultiploValidator.class })
public @interface Multiplo {

    String message() default "múltiplo inválido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int numero();
}
