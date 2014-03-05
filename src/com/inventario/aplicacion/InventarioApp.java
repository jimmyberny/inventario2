package com.inventario.aplicacion;

import com.inventario.util.Option;
import com.inventario.util.OptionGroup;

/**
 *
 * @author None
 */
public class InventarioApp {

    // Claves para los accesos a datos
    public static final String AD_GENERAL = "com.inventario.datas.DatosGeneral";

    // Claves para las pantallas
    public static final String VISTA_ERROR = "com.inventario.error.VistaError";

    public static final String LOGIN = "com.inventario.aplicacion.gui.Login";
    public static final String INICIO = "com.inventario.aplicacion.gui.Inicio";
    public static final String AREAS = "com.inventario.aplicacion.catalogos.AreaPanel";
    public static final String EMPLEADOS = "com.inventario.aplicacion.catalogos.EmpleadoPanel";
    public static final String USUARIOS = "com.inventario.aplicacion.catalogos.UsuarioPanel";
    public static final String PROGRAMAS = "com.inventario.aplicacion.catalogos.ProgramaPanel";
    public static final String TIPOS_EQUIPO = "com.inventario.aplicacion.catalogos.TipoEquipoPanel";
    public static final String EQUIPOS = "com.inventario.aplicacion.catalogos.EquipoPanel";
    public static final String PREFERENCIAS = "com.inventario.aplicacion.gui.Preferencias";
    public static final String EVENTOS = "com.inventario.aplicacion.catalogos.EventoPanel";
    public static final String REPORTE = "com.inventario.aplicacion.gui.ReporteEvento";
    public static final String CONSULTA = "com.inventario.aplicacion.gui.ConsultarEventos";

    // Claves de configuracion
    public static final String KC_DIAS_ALERTA = "aplicacion.diasalerta";
    public static final String KC_LOOK_AND_FEEL = "aplicacion.lookandfeel";
    
    // Bus
    public static final String BUS_EVENTO = "bus.evento";
    

    //
    public static final Option<String> LIMPIEZA = new Option<>("limpieza", "Limpieza del equipo");
    public static final Option<String> MAN_PREVENTIVO = new Option<>("preventivo", "Mant. preventivo");
    public static final Option<String> MAN_CORRECTIVO = new Option<>("correctivo", "Mant. correctivo");
    public static final Option<String> EXT_LICENCIA = new Option<>("licencia", "Extender licencia");
    public static final OptionGroup<String> MAN_TIPOS = new OptionGroup<>(LIMPIEZA, MAN_PREVENTIVO, MAN_CORRECTIVO, EXT_LICENCIA);
}
