//
// Created by rodrii on 21/10/2025.
//

#ifndef PSP_ACTIVIDAD3_H
#define PSP_ACTIVIDAD3_H


#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>

int main(void) {

    int fd1[2];
    int fd2[2];
    pid_t pid;

    if (pipe(fd) == -1 || pipe(fd2) == -1) {
        perror("Error in pipe");
        exit(1);
    }

    pid = fork();

    if (pid < 0) {
        perror("Error in fork");
        exit(1);
    }

    if (pid == 0) {
        //============
        //PROCESO HIJO
        //============

        close(fd1[1]); // Cierra el extremo de lectura de la pipe padre->hijo
        close(fd2[0]); // Cierra el extremo de lectura de la pipe hijo->padre

        char buffer[100];

        //Leemos el mensaje del padre
        read(fd1[0], buffer, sizeof(buffer));
        printf("HIJO RECIBE MENSAJE DE PADRE: %s\n", buffer);

        //Enviamos la respuesta al padre
        char mensajeHijo[]= "Saludos del Hijo...";
        printf("HIJO ENVIA MENSAJE A SU PADRE.\n");
        write(fd2[1], mensajeHijo, sizeof(mensajeHijo) + 1);

        close(fd1[0]);
        close(fd2[1]);
        exit(0);
    } else {
        //====== PROCESO PADRE =======

        close(fd1[0]);
        close(fd2[1])

        char mensajePadre[] = "Saludos del Padre...";
        printf("PADRE ENVIA MENSAJE.\n");
        write(fd1[1], mensajePadre, sizeof(mensajePadre) + 1);

        //Leemos respuesta del hijo
        char buffer[100];
        read(fd2[0], buffer,sizeof(buffer));
        printf("PADRE RECIBE MENSAJE DEL HIJO %s\n", buffer);

        close(fd1[1]);
        close(fd2[0]);
        wait(NULL);
    }

    return 0;

}


#endif //PSP_ACTIVIDAD3_H