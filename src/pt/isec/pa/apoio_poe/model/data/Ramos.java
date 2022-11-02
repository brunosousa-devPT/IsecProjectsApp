package pt.isec.pa.apoio_poe.model.data;




public enum Ramos {
    DA("DA"),RAS("RAS"), SI("SI");

    final String strRa;
    Ramos(String strRa) {this.strRa = strRa;}

    public static boolean getRa(String str) {
        if (str == null) return false;
        for (Ramos ramo: Ramos.values()) {
            if (ramo.strRa.equalsIgnoreCase(str)) return true;
        }
        return false;
    }

}

