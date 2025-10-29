#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

int main(void) {
    pid_t gerente1_pid, gerente2_pid, empleado1_pid, empleado2_pid, empleado3_pid, empleado4_pid;

    gerente1_pid = fork();
    if (gerente1_pid == -1) return -1;

    if (gerente1_pid == 0) {
        // GERENTE 1
        empleado1_pid = fork();
        if (empleado1_pid == -1) return -1;
        if (empleado1_pid == 0) {
            printf("[EMPLEADO1] PID=%d, PADRE=%d\n", getpid(), getppid());
            exit(0);
        }

        empleado2_pid = fork();
        if (empleado2_pid == -1) return -1;
        if (empleado2_pid == 0) {
            printf("[EMPLEADO2] PID=%d, PADRE=%d\n", getpid(), getppid());
            exit(0);
        }

        wait(NULL);
        wait(NULL);
        printf("[GERENTE1] PID=%d, PADRE=%d\n", getpid(), getppid());
        exit(0);
    }

    wait(NULL);

    gerente2_pid = fork();
    if (gerente2_pid == -1) return -1;

    if (gerente2_pid == 0) {
        // GERENTE 2
        empleado3_pid = fork();
        if (empleado3_pid == -1) return -1;
        if (empleado3_pid == 0) {
            printf("[EMPLEADO3] PID=%d, PADRE=%d\n", getpid(), getppid());
            exit(0);
        }

        empleado4_pid = fork();
        if (empleado4_pid == -1) return -1;
        if (empleado4_pid == 0) {
            printf("[EMPLEADO4] PID=%d, PADRE=%d\n", getpid(), getppid());
            exit(0);
        }

        wait(NULL);
        wait(NULL);
        printf("[GERENTE2] PID=%d, PADRE=%d\n", getpid(), getppid());
        exit(0);
    }

    wait(NULL);

    printf("[DIRECTOR] PID=%d, GERENTE1=%d, GERENTE2=%d\n", getpid(), gerente1_pid, gerente2_pid);
    return 0;
}