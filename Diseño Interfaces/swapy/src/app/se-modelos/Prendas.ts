import { Categorias } from "./Categorias";
import { Colores } from "./Colores";
import { Imagenes } from "./Imagenes";
import { Marcas } from "./Marcas";
import { PrendasTipo } from "./PrendasTipo";
import { Usuario } from "./Usuario";

export interface Prendas {
    titulo: string;
    descripcion: string;
    fechaAgregado: Date;
    estado: string;
    tipoGuardado: string;
    categorias: Categorias;
    marcas: Marcas;
    prendasTipo: PrendasTipo;
    usuario: Usuario;
    imagen: Imagenes;
    colores: Colores[];
}

