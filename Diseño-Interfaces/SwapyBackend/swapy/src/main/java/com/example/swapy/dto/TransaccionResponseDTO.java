package com.example.swapy.dto;

import com.example.swapy.models.EstadoTransaccion;
import com.example.swapy.models.TipoTransaccion;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TransaccionResponseDTO {
    private Integer id;
    private Integer solicitanteId;
    private Integer propietarioId;
    private TipoTransaccion tipoTransaccion;
    private EstadoTransaccion estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFinReal;
}