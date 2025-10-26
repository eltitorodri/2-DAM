#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>

int main(void) {

    int pipe1[1], pipe2[2];

    pid_t pid;

    char mensaje_padre [] = "hola hijo, soy tu padre.";

    char buffer[100]="";

    if (pipe(pipe1) == -1 || pipe(pipe2) == -1) return -1;

    pid = fork();

    if (pid < 0) return -1;

    if (pid == 0) {

        //HIJO
        close(pipe1[0]);
        close(pipe2[1]);

        read(pipe2[0], buffer, sizeof(buffer));
        printf("\n[HIJO] EL HIJO recibe del pipe: %s\n", buffer);

        close(pipe2[0]);

    } else {
        // PADRE
        close(pipe1[1]);
        close(pipe2[0]);

        write(pipe2[1], mensaje_padre, strlen(mensaje_padre));
        printf("\n[PADRE] EL PADRE ENVIA MENSAJE AL HIJO...");

        close(pipe2[1]);

    }

    return 0;

}