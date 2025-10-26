#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(void) {

    pid_t hijo1_pid, nieto_pid, hijo2_pid;

    hijo1_pid = fork();

    if (hijo1_pid == -1) return -1;

    if (hijo1_pid == 0) {
        nieto_pid = fork();

        if (nieto_pid == -1) return -1;
        if (nieto_pid == 0) {
            printf("\n[NIETO] Soy el NIETO mi PID es: %d y mi PADRE es: %d", getpid(),getppid());
        }else {
            printf("\n[HIJO1] Soy el HIJO1 mi PID es: %d y mi PADRE es: %d\n", getpid(),getppid());
        }
        exit(0);
    }

    wait (NULL);

    hijo2_pid = fork();

    if (hijo2_pid == -1) return -1;

    if (hijo2_pid == 0) {
        printf("\n[HIJO2] Soy el HIJO2 mi PID es: %d y mi PADRE es: %d", getpid(),getppid());
    } else {
        wait(NULL);
        printf("\n[ABUELO] Soy el ABUELO mi PID es: %d y mi HIJO1 es: %d y mi HIJO2 es: %d", getpid(),hijo1_pid, hijo2_pid);
    }

    return 0;

}