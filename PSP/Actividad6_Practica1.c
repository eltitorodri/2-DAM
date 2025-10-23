#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

/*------------------------------------------*/
/* gestion de señales en proceso padre */
void gestion_padre( int signal) {
    printf("Padre recibe señal... %d\n", signal);
}
void gestion_hijo( int signal) {
    printf("Hijo recibe señal...%d\n", signal);
}
/*------------------------------------------*/

int main() {
    int pid_padre, pid_hijo;

    pid_padre = getpid();

    pid_hijo = fork(); // creamos el hijo

    if(pid_hijo == -1) {
        printf("Error forking\n");
        exit(1);
    } else if (pid_hijo == 0) {
        //CREAMOS EL HIJO
        signal(SIGUSR2, gestion_hijo); //TRATAMIENTO DE LA SEÑAL EN PROCESO HIJO
        while (1) { //creamos el BUCLE infinito
            sleep(1);
            kill(pid_padre, SIGUSR1); //envia la señal al padre
            pause(); //hijo se espera hasta que llegue una señal de respuesta
        }
    } else {
        //PADRE
        signal(SIGUSR1, gestion_padre); //TRATAMIENTO DE LA SEÑAL EN PROCESO PADRE
        while (1) {
            pause(); // padre espera hasta recibir una señal del hijo
            sleep(1);
            kill(pid_hijo, SIGUSR2); // envia señal al hijo
        }
    }
    return 0;
}