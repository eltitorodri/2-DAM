#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(void) {

    pid_t hijo_pid, nieto_pid, hijo2_pid;

    hijo_pid = fork();

    if (hijo_pid == 0) {

        nieto_pid = fork();

        if (nieto_pid == -1) {
            perror("Fork");
            exit(1);
        }

        if (nieto_pid == 0) {
            printf("\nSoy el proceso NIETO: %d, MI PADRE es : %d", getpid(), getppid());
        } else {
            wait(NULL);
            printf("\nSoy el proceso HIJO : %d, MI PADRE es : %d", getpid(), getppid());
        }
    } else {

        wait(NULL);

        hijo2_pid = fork();
        if (hijo2_pid == 0) {
            printf("\nSoy el proceso HIJO2: %d, MI PADRE es : %d", getpid(), getppid());
        } else {
            wait(NULL);
            printf("\nSoy el proceso ABUELO: %d, MI HIJO : %d termino, MI HIJO2: %d termino\n", getpid(), hijo_pid, hijo2_pid);
        }

    }
    exit(0);
}