#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>


int main(void) {

    pid_t pid;

    pid = fork();
    if (pid == -1) return -1;
    if (pid == 0) {
        printf("\nSoy el [HIJO1] mi PID es: %d, mi [PADRE] es: %d", getpid(), getppid());
        exit(0);
    }

    wait(NULL);

    pid = fork();
    if (pid == -1) return -1;
    if (pid == 0) {
        printf("\nSoy el [HIJO2] mi PID es: %d, mi [PADRE] es: %d", getpid(), getppid());
        exit(0);
    }

    wait(NULL);

    pid = fork();
    if (pid == -1) return -1;
    if (pid == 0) {
        printf("\nSoy el [HIJO3] mi PID es: %d, mi [PADRE] es: %d", getpid(), getppid());
        exit(0);
    }

    wait(NULL);

    printf("\nSoy el [PADRE] mi PID es: %d, he terminado.", getpid());

    return 0;

}

/*==================
/*   OTRA OPCION
 *==================

/*pid_t  hijo1_pid, hijo2_pid, hijo3_pid;

hijo1_pid = fork();

if (hijo1_pid == -1) return -1;
if (hijo1_pid == 0) {
    printf("\nSoy el proceso [HIJO1] mi PID es: %d, mi [PADRE] es: %d\n", getpid(), getppid());
} else {
    hijo2_pid = fork();

    if (hijo2_pid == -1) return -1;
    if (hijo2_pid == 0) {
        printf("\nSoy el proceso [HIJO2] mi PID es: %d, mi [PADRE] es: %d\n", getpid(), getppid());
    } else {
        hijo3_pid = fork();
        if (hijo3_pid == -1) return -1;
        if (hijo3_pid == 0) {
            printf("\nSoy el proceso [HIJO3] mi PID es: %d, mi [PADRE] es: %d\n", getpid(), getppid());
        } else {
            wait(NULL);
            printf("\nSoy el proceso [PADRE] mi PID es: %d he terminado.\n", getpid());
        }
    }
}*/
