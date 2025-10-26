#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

/* PROCESO GESTION PADRE */

void gestionar_padre(int signal) {
    printf("Padre recibe señal de hijo con PID: %d\n", getpid());
}

//Proceso gestion hijo

void gestionar_hijo(int signal) {
    printf("Hijo recibe señal de padre con PID: %d\n", getpid());
}

int main(void) {

    int pid_padre, pid_hijo;

    pid_padre = getpid();

    pid_hijo = fork();

    if (pid_hijo == -1) return -1;

    if (pid_hijo == 0) {
        // Hijo
        signal(SIGUSR2, gestionar_hijo);
        sleep(1);
        kill(pid_padre, SIGUSR1);
        pause();
        kill(pid_padre, SIGKILL);
        exit(0);
    } else {
        // Padre
        signal(SIGUSR1, gestionar_padre);
        pause();
        sleep(1);
        kill(pid_hijo, SIGUSR2);
        pause();
    }

    return 0;
}
