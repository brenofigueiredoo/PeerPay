package br.com.breno.learningspring.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CreateDepositDto {

    @NotNull(message = "Value cannot be null")
    @DecimalMin(value = "0.01", message = "Value must be higher then 0.01")
    private float value;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
