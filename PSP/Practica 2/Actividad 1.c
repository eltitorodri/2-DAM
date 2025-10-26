#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

int main (void) {

    pid_t hijo1_pid, hijo2_pid, hijo3_pid, nieto1_pid, nieto2_pid;

    hijo1_pid=fork();

    if (hijo1_pid == -1) return -1;

    if (hijo1_pid == 0) {

        nieto1_pid=fork();

        if (nieto1_pid == -1) return -1;

        if (nieto1_pid == 0) {
            printf("\n[NIETO1] Soy el NIETO1 mi es PID es: %d y mi padre es: %d\n", getpid(), getppid());
        } else {
            printf("\n[HIJO1] Soy el HIJO1 mi es PID es: %d y mi padre es: %d\n", getpid(), getppid());
        }
        exit(0);
    }

    wait(NULL);

    hijo2_pid=fork();

    if (hijo2_pid == -1) return -1;

    if (hijo2_pid == 0) {
        nieto2_pid=fork();

        if (nieto2_pid == -1) return -1;

        if (nieto2_pid == 0) {
            printf("\n[NIETO2] Soy el NIETO2 mi es PID es: %d y mi padre es: %d\n", getpid(), getppid());
        } else {
            printf("\n[HIJO2] Soy el HIJO2 mi es PID es: %d y mi padre es: %d\n", getpid(), getppid());
        }
        exit(0);
    }

    wait(NULL);

    hijo3_pid=fork();

    if (hijo3_pid == -1) return -1;

    if (hijo3_pid == 0) {
        printf("\n[HIJO3] Soy el HIJO3 mi es PID es: %d y mi padre es: %d\n", getpid(), getppid());
    } else {
        printf("\n[PADRE] Soy el PADRE mi es PID es: %d y he terminado", getpid());
    }

    return 0;

}