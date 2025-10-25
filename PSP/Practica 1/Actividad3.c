#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(void) {

    int pipe1[2]; //padre -> hijo
    int pipe2[2]; //hijo -> padre

    char mensaje_padre [] = "Saludos del Padre...\n";
    char mensaje_hijo [] = "Saludos del Hijo...\n";

    char buffer[100]="";

    pid_t pid_hijo;

    if (pipe(pipe1) == -1 || pipe(pipe2) == -1) {
        perror("Error en el proceso");
        exit(1);
    }

    pid_hijo = fork();

    if (pid_hijo == -1) return -1;

    if (pid_hijo == 0) {
        close(pipe1[1]);
        close(pipe2[0]);

        read(pipe1[0], buffer, sizeof(buffer));
        printf("[HIJO] HIJO RECIBE MENSAJE de PADRE: %s\n", buffer);

        close(pipe1[0]);

        write(pipe2[1], mensaje_hijo, sizeof(mensaje_hijo));
        printf("[HIJO] HIJO ENVIA MENSAJE a su PADRE.\n");

        close(pipe2[1]);

    } else {
        close(pipe1[0]);
        close(pipe2[1]);

        write(pipe1[1], mensaje_padre, strlen(mensaje_padre));
        printf("[PADRE] PADRE ENVIA MENSAJE a su HIJO.\n");

        close(pipe1[1]);

        read(pipe2[0], buffer, sizeof(buffer));
        printf("[PADRE] PADRE RECIBE MENSAJE del HIJO: %s\n", buffer);

        close(pipe2[0]);

    }

    return 0;

}