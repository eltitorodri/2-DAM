#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

// Función para calcular factorial
unsigned long long factorial(int n) {
    unsigned long long fact = 1;
    for (int i = 1; i <= n; i++)
        fact *= i;
    return fact;
}

int main() {
    int pipe1[2]; // Para comunicación Hijo1 -> Hijo2
    int pipe2[2]; // Para comunicación Hijo2 -> Hijo3

    if (pipe(pipe1) == -1 || pipe(pipe2) == -1) {
        perror("Error creando pipes");
        exit(1);
    }

    pid_t hijo1, hijo2, hijo3;

    // Crear Hijo1
    hijo1 = fork();
    if (hijo1 < 0) { perror("fork"); exit(1); }

    if (hijo1 == 0) {
        // --- Hijo 1 ---
        close(pipe1[0]); // Cerrar lectura del pipe1
        close(pipe2[0]);
        close(pipe2[1]);

        int fib[10];
        fib[0] = 0; fib[1] = 1;
        for (int i = 2; i < 10; i++)
            fib[i] = fib[i-1] + fib[i-2];

        // Enviar Fibonacci a Hijo2
        write(pipe1[1], fib, sizeof(fib));
        close(pipe1[1]); // cerrar escritura
        exit(0);
    }

    // Crear Hijo2
    hijo2 = fork();
    if (hijo2 < 0) { perror("fork"); exit(1); }

    if (hijo2 == 0) {
        // --- Hijo 2 ---
        close(pipe1[1]); // Cerrar escritura de pipe1
        close(pipe2[0]); // Cerrar lectura de pipe2

        int fib[10];
        read(pipe1[0], fib, sizeof(fib));
        close(pipe1[0]); // cerrar lectura

        int dobles[10];
        for (int i = 0; i < 10; i++)
            dobles[i] = fib[i] * 2;

        // Enviar al Hijo3
        write(pipe2[1], dobles, sizeof(dobles));
        close(pipe2[1]); // cerrar escritura
        exit(0);
    }

    // Crear Hijo3
    hijo3 = fork();
    if (hijo3 < 0) { perror("fork"); exit(1); }

    if (hijo3 == 0) {
        // --- Hijo 3 ---
        close(pipe1[0]);
        close(pipe1[1]);
        close(pipe2[1]); // cerrar escritura de pipe2

        int dobles[10];
        read(pipe2[0], dobles, sizeof(dobles));
        close(pipe2[0]); // cerrar lectura

        // Imprimir resultados con factorial
        for (int i = 0; i < 10; i++) {
            printf("Número: %d, Factorial: %llu\n", dobles[i], factorial(dobles[i]));
        }
        exit(0);
    }

    // --- Proceso padre ---
    close(pipe1[0]); close(pipe1[1]);
    close(pipe2[0]); close(pipe2[1]);

    // Esperar a todos los hijos
    wait(NULL);
    wait(NULL);
    wait(NULL);

    printf("Todos los procesos hijos han terminado.\n");
    return 0;
}
