#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

int main(){

    pid_t hijo_pid, nieto_pid;

    int pipe1[2];
    int pipe2[2];

    char saludoAbuelo[] = "Saludo del Abuelo";
    char saludoHijo[] = "Saludo del Hijo";
    char saludoNieto[] = "Saludo del Nieto";

    char buffer[80];

    if(pipe(pipe1) == -1 || pipe(pipe2) == -1){
        printf("ERROR");
        exit(1);
    }

    hijo_pid = fork();
    if(hijo_pid == 0){

        nieto_pid = fork();
        if(nieto_pid == 0){

            close(pipe2[1]);

            printf("[NIETO] recibe mensaje del HIJO...\n");
            read(pipe2[0], buffer, sizeof(buffer));
            printf("\tMensaje del HIJO: %s\n", buffer);

            close(pipe2[0]);
            close(pipe1[0]);

            printf("[NIETO] envia mensaje al HIJO...\n");
            write(pipe1[1], saludoNieto, strlen(saludoNieto));

            close(pipe1[1]);
            exit(0);

        }

        close(pipe1[1]);

        printf("[HIJO] recibe mensaje del ABUELO...\n");
        read(pipe1[0], buffer, sizeof(buffer));
        printf("\tMensaje del Abuelo: %s\n", buffer);

        close(pipe1[0]);

        close(pipe2[0]);

        printf("[HIJO] envia mensaje al NIETO...\n");
        write(pipe2[1], saludoHijo, strlen(saludoHijo));

        close(pipe2[1]);

        waitpid(nieto_pid, NULL, 0);

        close(pipe1[1]);

        printf("[HIJO] recibe mensaje del NIETO...\n");
        read(pipe1[0], buffer, sizeof(buffer));
        printf("\tMensaje del NIETO: %s\n", buffer);

        close(pipe1[0]);
        close(pipe2[0]);

        printf("[HIJO] envia mensaje al ABUELO...\n");
        write(pipe2[1], saludoHijo, strlen(saludoHijo));

        close(pipe2[1]);

        exit(0);


    }

    close(pipe1[0]);

    printf("[ABUELO] enviando mensaje a HIJO...\n");
    write(pipe1[1], saludoAbuelo, strlen(saludoAbuelo));
    close(pipe1[1]);

    waitpid(hijo_pid, NULL, 0);
    waitpid(nieto_pid, NULL, 0);

    close(pipe2[1]);


    printf("[ABUELO] recibe mensaje del HIJO...\n");
    read(pipe2[0], buffer, sizeof(buffer));
    printf("\tMensaje del HIJO: %s\n", buffer);

    return 0;

}