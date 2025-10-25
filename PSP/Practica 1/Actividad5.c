#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int main(void) {

    int pipe1[2], pipe2[2];

    char saludo_abuelo [] = "Saludos del ABUELO...\n";
    char saludo_padre [] = "Saludos del PADRE...\n";
    char saludo_hijo [] = "Saludos del HIJO...\n";

    char buffer[100]="";

    pid_t pid_padre, pid_hijo;

    if (pipe(pipe1) == -1 || pipe(pipe2) == -1) {
        perror("Error en el pipe()");
        exit(1);
    }

    pid_padre = fork();

    if (pid_padre == -1) return -1;

    if (pid_padre == 0) {

        pid_hijo = fork();

        if (pid_hijo == -1) return -1;

        if (pid_hijo == 0) {

            close(pipe1[0]);
            close(pipe2[1]);

            read(pipe2[0], buffer, sizeof(buffer));
            printf("[HIJO] HIJO RECIBE mensaje de su padre: %s\n", buffer);

            close(pipe2[0]);

            write(pipe1[1], saludo_hijo, strlen(saludo_hijo));
            printf("[HIJO] HIJO ENVIA MENSAJE a su padre...\n");

            close(pipe1[1]);

        }else {

            close(pipe1[1]);
            close(pipe2[0]);

            read(pipe1[0], buffer, sizeof(buffer));
            printf("[PADRE] PADRE recibe mensaje de ABUELO: %s\n", buffer);

            close(pipe1[0]);

            write(pipe2[1], saludo_padre, strlen(saludo_padre));
            // AQUI ENVIA EL HIJO AL NIETO
            close(pipe2[1]);

            wait(NULL);

            close(pipe1[1]);
            close(pipe2[0]);

            read(pipe1[0], buffer, sizeof(buffer));
            printf("[PADRE] PADRE RECIBE mensaje de su hijo: %s\n", buffer);
            //EL PADRE RECIBE EL MENSAJE DEL HIJO

            close(pipe1[0]);

            write(pipe2[1], saludo_hijo, strlen(saludo_hijo));
            printf("[PADRE] PADRE ENVIA MENSAJE a su ABUELO...\n");

            // AQUI EL PADRE ENVIA EL MENSAJE AL ABUELO

            close(pipe2[1]);

        }

    } else {

        close(pipe1[0]);
        close(pipe2[1]);

        write(pipe1[1], saludo_abuelo, strlen(saludo_abuelo));
        printf("[ABUELO] ABUELO ENVIA MENSAJE AL HIJO...\n");

        close(pipe1[1]);

        wait(NULL);

        close(pipe2[1]);

        read(pipe1[0], buffer, sizeof(buffer));
        printf("[ABUELO] EL ABUELO RECIBE MENSAJE del HIJO: %s\n", buffer);

        close(pipe1[0]);

    }

    return 0;

}
