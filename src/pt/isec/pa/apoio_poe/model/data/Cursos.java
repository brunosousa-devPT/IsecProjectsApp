package pt.isec.pa.apoio_poe.model.data;

public enum Cursos {

    LEI("LEI"), LEIPL("LEI-PL");

    final String strCur;
    Cursos(String strCur) {this.strCur = strCur;}

    public static boolean getCur(String str) {
        if (str == null) return false;
        for (Cursos curso: Cursos.values()) {
            if (curso.strCur.equalsIgnoreCase(str)) return true;
        }
        return false;
    }
}
