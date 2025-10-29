#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

//Proceso Entrenador1
void gestion_entrenador1 (int signal ) {
    printf("\nEntrenador 1 recibiendo señal...%d\n", signal);
}

//Proceso Entrenador2
void gestion_entrenador2 (int signal ) {
    printf("\nEntrenador 2 recibiendo señal...%d\n", signal);
}

int main(void) {

    int pid_entrenador1, pid_entrenador2;

    pid_entrenador1 = getpid();

    pid_entrenador2 = fork();

    if(pid_entrenador2 == -1) return 0;

    if (pid_entrenador2 == 0) {
        signal(SIGUSR1, gestion_entrenador2);
        while(1) {
            sleep(1);
            kill(pid_entrenador1, SIGUSR2);
            pause();
        }
    } else {
        signal(SIGUSR2, gestion_entrenador1);
        while(1) {
            pause();
            sleep(1);
            kill(pid_entrenador2, SIGUSR1);
        }
    }

    return 0;

}