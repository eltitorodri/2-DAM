import { Categorias } from "./Categorias";
import { Colores } from "./Colores";
import { Imagenes } from "./Imagenes";
import { Marcas } from "./Marcas";
import { Prendas } from "./Prendas";
import { PrendasTipo } from "./PrendasTipo";
import { Usuario } from "./Usuario";

export interface PrendasItem {
  id?: number;
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
  
  // Campos extra que tengas para la UI
  imagenUrl?: string;
  nombreMarca?: string;
  UsuarioNombre?: string;
}
