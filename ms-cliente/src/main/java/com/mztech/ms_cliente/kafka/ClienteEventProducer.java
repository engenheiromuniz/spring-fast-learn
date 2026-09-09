package com.mztech.ms_cliente.kafka;

import com.mztech.ms_cliente.event.ClienteCriadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClienteEventProducer {

    private static final String TOPICO = "cliente.criado";

    @Autowired
    private KafkaTemplate<String, ClienteCriadoEvent> kafkaTemplate;

    public void publicarClienteCriado(ClienteCriadoEvent evento) {
        kafkaTemplate.send(TOPICO, evento.getId().toString(), evento);
    }
}