package com.example.swapy.services;


import com.example.swapy.models.Chat;
import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> findAll(){
        return chatRepository.findAll();
    }

    public Chat findById(Integer id){
        return chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun chat con ese id: " +id));
    }

    public Chat save(Chat chat){
        return chatRepository.save(chat);
    }

    public void  delete(Chat chat){
        chatRepository.delete(chat);
    }

//   public List<Chat> findBySenderAndReceiverId(Usuarios sender, Usuarios receiver){
//       return chatRepository.findBySenderAndReceiverOrderBySentAt(receiver, sender);
//   }

}
