#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>

// Funcion para gestionar al padre
void gestionar_padre( int signal ) {
    printf("Padre recibe señal...%d\n", signal);
}

void gestionar_hijo( int signal ) {
    printf("Hijo recibe señal...%d\n", signal);
}

int main(void) {

    pid_t pid_hijo, pid_padre;

    pid_padre = getpid();

    pid_hijo = fork();

    if(pid_hijo == -1) return 0;

    if (pid_hijo == 0) {
        signal(SIGUSR1, gestionar_hijo);
        while(1) {
            sleep(1);
            kill(pid_padre, SIGUSR2);
            pause();
        }
    } else {
        signal(SIGUSR2, gestionar_padre);
        while(1) {
            pause();
            sleep(1);
            kill(pid_hijo, SIGUSR1);
        }
    }

    return 0;

}