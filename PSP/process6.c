#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

/*----------------------------------------------*/
/* gestión de señales en proceso padre */
void gestion_padre(int senal)
{
    printf("Padre recibe señal..%d\n", senal);
}

/* gestión de señales en proceso hijo */
void gestion_hijo(int senal)
{
    printf("Hijo recibe señal..%d\n", senal);
}
/*----------------------------------------------*/

int main()
{
    int pid_padre, pid_hijo;

    pid_padre = getpid();
    pid_hijo = fork(); // creamos hijo

    if (pid_hijo == -1) {
        printf("Error al crear el proceso hijo...\n");
        exit(-1);
    }
    else if (pid_hijo == 0) {
        // HIJO
        signal(SIGUSR2, gestion_hijo); // tratamiento de la señal en proceso hijo
        while (1) { // bucle infinito
            sleep(1);
            kill(pid_padre, SIGUSR1); // envía señal al padre
            pause(); // hijo espera hasta que llegue una señal de respuesta
        }
    }
    else {
        // PADRE
        signal(SIGUSR1, gestion_padre); // tratamiento de la señal en proceso padre
        while (1) {
            pause(); // padre espera hasta recibir una señal del hijo
            sleep(1);
            kill(pid_hijo, SIGUSR2); // envía señal al hijo
        }
    }

    return 0;
}
