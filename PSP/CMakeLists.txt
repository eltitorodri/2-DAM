cmake_minimum_required(VERSION 3.28)
project(PSP C)

set(CMAKE_C_STANDARD 11)

add_executable(PSP main.c
        pipe_actividad5.c
        examen6.c
        examen4.c
        actividad1practica2.c
        Actividad6_Practica1.c
        "Practica 1/Actividad1.c"
        "Practica 1/Actividad2.c"
        "Practica 1/Actividad3.c"
        "Practica 1/Actividad5.c"
        "Practica 1/Actividad6.c"
        "Practica 2/Actividad 1.c"
        "Practica 2/Actividad 2.c"
        "Practica 2/Actividad 2.c"
        "Practica 2/Actividad 4.c"
        "Examen/Actvidad 4.c"
        "Examen/Actividad 6.c"
        "Examen/Actividad 7.c")
add_executable(PSP1 process6.c)
