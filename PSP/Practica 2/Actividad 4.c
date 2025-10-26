#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <stdbool.h>

bool esPrimo(int x) {
    if (x < 2) return false;
    for (int i = 2; i * i <= x; i++) {
        if (x % i == 0) return false;
    }
    return true;
}

int main(void) {
    int pipe1[2], pipe2[2];
    pid_t pid;

    int numeros[10] = {5, 12, 7, 3, 18, 21, 9, 4, 15, 8};

    if (pipe(pipe1) == -1 || pipe(pipe2) == -1) {
        perror("Error creando pipes");
        exit(1);
    }

    pid = fork();
    if (pid < 0) { perror("fork"); exit(1); }

    if (pid == 0) {
        // --- HIJO ---
        close(pipe1[1]); // cerrar escritura pipe1
        close(pipe2[0]); // cerrar lectura pipe2

        int numeros_recibidos[10];
        read(pipe1[0], numeros_recibidos, sizeof(numeros_recibidos));
        close(pipe1[0]);

        int primos[10];
        int count = 0;
        int suma = 0;

        for (int i = 0; i < 10; i++) {
            if (esPrimo(numeros_recibidos[i])) {
                primos[count++] = numeros_recibidos[i];
                suma += numeros_recibidos[i];
            }
        }

        // Enviar primos y suma al padre
        write(pipe2[1], &count, sizeof(int));      // enviar cantidad de primos
        write(pipe2[1], primos, sizeof(int) * count); // enviar primos
        write(pipe2[1], &suma, sizeof(int));       // enviar suma
        close(pipe2[1]);

        exit(0);
    }

    // --- PADRE ---
    close(pipe1[0]); // cerrar lectura pipe1
    close(pipe2[1]); // cerrar escritura pipe2

    write(pipe1[1], numeros, sizeof(numeros));
    close(pipe1[1]);

    int count;
    read(pipe2[0], &count, sizeof(int));

    int primos[count];
    read(pipe2[0], primos, sizeof(int) * count);

    int suma;
    read(pipe2[0], &suma, sizeof(int));
    close(pipe2[0]);

    wait(NULL);

    printf("[PADRE] Números primos recibidos del hijo: ");
    for (int i = 0; i < count; i++) {
        printf("%d ", primos[i]);
    }
    printf("\n[PADRE] Suma de primos: %d\n", suma);

    return 0;
}
