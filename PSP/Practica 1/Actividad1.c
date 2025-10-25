#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main (void) {

    pid_t pid_hijo, pid_nieto;

    pid_hijo = fork();

    if (pid_hijo == 0) {
        pid_nieto = fork();

        if (pid_nieto == -1 ) {
            printf("Error in fork\n");
            exit(1);
        }

        if (pid_nieto == 0) {
            printf("\nSoy el proceso [NIETO] mi PID es: %d, mi [PADRE] es: %d", getpid(), getppid());
        } else {
            wait(NULL);
            printf("\nSoy el proceso [HIJO] mi PID es: %d, mi [PADRE] es: %d", getpid(), getppid());
        }
    } else {
        wait(NULL);
        printf("\nSoy el proceso [PADRE] es: %d, mi [HIJO] es: %d", getpid(), pid_hijo);
    }

    return 0;
}
