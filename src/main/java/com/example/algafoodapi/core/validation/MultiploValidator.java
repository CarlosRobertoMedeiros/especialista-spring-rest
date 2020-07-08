package com.example.algafoodapi.core.validation;
/*
 *  @criado em: 08/07/2020 - {06:45}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

    private int NumeroMultiplo;

    @Override
    public void initialize(Multiplo constraintAnnotation) {
        this.NumeroMultiplo = constraintAnnotation.numero();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        boolean valido=true;

        if (value!=null){
            BigDecimal valorDecimal = BigDecimal.valueOf(value.doubleValue());
            BigDecimal multiploDecimal = BigDecimal.valueOf(this.NumeroMultiplo);
            BigDecimal resto = valorDecimal.remainder(multiploDecimal);

            valido = BigDecimal.ZERO.compareTo(resto) ==0;
        }

        return valido;
    }
}
