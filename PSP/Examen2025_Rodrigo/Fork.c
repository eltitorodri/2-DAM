#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(void) {

    pid_t pid;

    int pipe1[2], pipe2[2];

    char buffer[100]="";

    int numeros [] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    if (pipe(pipe1) == -1 || pipe(pipe2) == -1) return -1;

    pid = fork();

    if (pid == -1) return -1;

    if (pid == 0) {
        // hijo

        close(pipe1[1]);
        close(pipe2[0]);

        read(pipe1[0], buffer, sizeof(buffer));
        printf("\n[HIJO] HE RECIBIDO LOS NUMEROS...");

        close(pipe1[0]);

        write(pipe2[1], buffer, strlen(buffer));
        printf("\n[HIJO] HE ENVIADO LOS NUMEROS AL PADRE...");

        close(pipe2[1]);

    } else {
        // padre
        close(pipe1[0]);
        close(pipe2[1]);

        write(pipe1[1], numeros, strlen(numeros));
        printf("\n[PADRE] ENVIO NUMEROS A HIJO...");

        close(pipe1[1]);

        read(pipe2[0], buffer, sizeof(buffer));
        printf("\n[PADRE] HE RECIDIDO LOS NUMEROS...");

        close(pipe2[0]);
    }


    return 0;

}
