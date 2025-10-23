#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

int main(void) {

    pid_t pid_hijo;

    int pipe1[2];
    int pipe2[2];

    char mensaje_hijo [] = "Saludos del hijo.";
    char mensaje_padre [] = "Saludos del padre.";

    char buffer[80]="";

    if (pipe(pipe1) == -1 || pipe(pipe2) == -1) {
        perror("Error");
        exit(1);
    }

    pid_hijo = fork();

    if (pid_hijo < 0) {
        perror("Error");
        exit(1);
    }

    if(pid_hijo == 0) {

        close(pipe1[1]);
        close(pipe2[0]);

        read(pipe1[0], buffer, sizeof(buffer));
        printf("[HIJO] Ha leído el mensaje del PADRE: %s\n", buffer);

        write(pipe2[1], mensaje_hijo, strlen(mensaje_hijo));
        printf("[HIJO] Enviamos el mensaje al padre...\n");

        close(pipe1[0]);
        close(pipe2[1]);

        exit(0);

    } else {

        close(pipe1[0]);
        close(pipe2[1]);

        write(pipe1[1], mensaje_padre, strlen(mensaje_padre));
        printf("[PADRE] Ha enviado mensaje al hijo...\n");

        close(pipe1[1]);

        waitpid(pid_hijo, NULL, 0);

        close(pipe2[1]);

        read(pipe2[0], buffer, sizeof(buffer));
        printf("[PADRE] Ha leído el mensaje del hijo: %s\n", buffer);

        close(pipe2[0]);
    }
    return 0;

}