package com.mztech.ms_cliente.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCriadoEvent {

    private Long id;
    private String nome;
    private String email;
}