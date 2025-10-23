#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(void) {
    pid_t hijo1_pid, hijo2_pid, hijo3_pid, nieto1_pid, nieto2_pid;

    // Crear hijo1
    hijo1_pid = fork();
    if (hijo1_pid < 0) {
        perror("fork");
        exit(1);
    }
    if (hijo1_pid == 0) {
        // Soy hijo1
        printf("\nSoy el proceso [HIJO1]: %d, MI PADRE es: %d\n", getpid(), getppid());

        // Crear nieto1
        nieto1_pid = fork();
        if (nieto1_pid < 0) {
            perror("fork");
            exit(1);
        }
        if (nieto1_pid == 0) {
            // Soy nieto1
            printf("Soy el proceso [NIETO1]: %d, MI PADRE es: %d\n", getpid(), getppid());
            exit(0);
        } else {
            wait(NULL); // Espera a nieto1
            exit(0);
        }
    }

    // Crear hijo2
    hijo2_pid = fork();
    if (hijo2_pid < 0) {
        perror("fork");
        exit(1);
    }
    if (hijo2_pid == 0) {
        // Soy hijo2
        printf("\nSoy el proceso [HIJO2]: %d, MI PADRE es: %d\n", getpid(), getppid());

        // Crear nieto2
        nieto2_pid = fork();
        if (nieto2_pid < 0) {
            perror("fork");
            exit(1);
        }
        if (nieto2_pid == 0) {
            // Soy nieto2
            printf("Soy el proceso [NIETO2]: %d, MI PADRE es: %d\n", getpid(), getppid());
            exit(0);
        } else {
            wait(NULL); // Espera a nieto2
            exit(0);
        }
    }

    // Crear hijo3
    hijo3_pid = fork();
    if (hijo3_pid < 0) {
        perror("fork");
        exit(1);
    }
    if (hijo3_pid == 0) {
        // Soy hijo3
        printf("\nSoy el proceso [HIJO3]: %d, MI PADRE es: %d\n", getpid(), getppid());
        exit(0);
    }

    // Padre espera a todos los hijos
    waitpid(hijo1_pid, NULL, 0);
    waitpid(hijo2_pid, NULL, 0);
    waitpid(hijo3_pid, NULL, 0);

    printf("\nSoy el proceso [PADRE]: %d, todos mis hijos han terminado.\n", getpid());
    return 0;
}