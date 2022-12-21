package com.example.reto.service.impl;

import com.example.reto.commons.GenericServiceImpl;
import com.example.reto.dto.ClienteDTO;
import com.example.reto.model.Cliente;
import com.example.reto.service.api.ClienteServiceAPI;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, ClienteDTO> implements ClienteServiceAPI {

    @Autowired
    private Firestore firestore;

    @Override
    public CollectionReference getCollection() {
        return firestore.collection("clients");
    }
}
