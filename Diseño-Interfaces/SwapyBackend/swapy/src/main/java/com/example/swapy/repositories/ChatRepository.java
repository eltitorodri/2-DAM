package com.example.swapy.repositories;

import com.example.swapy.models.Chat;
import com.example.swapy.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

//    List<Chat> findBySenderAndReceiverOrderBySentAt(Usuarios sender, Usuarios receiver);

}
